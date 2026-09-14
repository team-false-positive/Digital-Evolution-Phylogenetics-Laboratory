package evolab.phylogenetics;

import evolab.genetics.Genotype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Tracks allele frequencies across generations and computes Hardy-Weinberg
 * expected genotype ratios for comparison against observed ratios.
 *
 * Hardy-Weinberg equilibrium (2-allele case, per locus):
 *   Let p = frequency of allele 1, q = frequency of allele 2, p + q = 1.
 *   Expected genotype frequencies at equilibrium:
 *     homozygous allele-1 (1/1) : p^2
 *     heterozygous        (1/2) : 2pq
 *     homozygous allele-2 (2/2) : q^2
 *
 * This tracker is deliberately scoped to the classic 2-allele model per locus,
 * since that's what the EvoLab diploid model uses. If Module A ever
 * introduces >2 alleles per locus (multi-allelic loci), this needs a
 * multinomial generalization — flagged in the Week-5 report as a known
 * simplification, not a bug.
 */
public class PopulationGeneticsTracker {

    /** Snapshot of allele/genotype frequencies for a single locus at a single generation. */
    public static class GenerationSnapshot {
        public final int generation;
        public final int locusIndex;
        public final Map<Integer, Double> alleleFrequencies;   // alleleId -> frequency
        public final Map<String, Double> observedGenotypeRatios; // "1/1","1/2","2/2" style keys -> observed frequency
        public final Map<String, Double> expectedGenotypeRatios; // Hardy-Weinberg expected

        GenerationSnapshot(int generation, int locusIndex,
                           Map<Integer, Double> alleleFrequencies,
                           Map<String, Double> observedGenotypeRatios,
                           Map<String, Double> expectedGenotypeRatios) {
            this.generation = generation;
            this.locusIndex = locusIndex;
            this.alleleFrequencies = alleleFrequencies;
            this.observedGenotypeRatios = observedGenotypeRatios;
            this.expectedGenotypeRatios = expectedGenotypeRatios;
        }
    }

    // Recorded history: generation number -> locusIndex -> snapshot
    private final Map<Integer, Map<Integer, GenerationSnapshot>> history = new LinkedHashMap<>();

    /**
     * Records allele + Hardy-Weinberg genotype frequencies for every locus of
     * a population at a given generation.
     *
     * @param generation      generation number (0-indexed, ascending)
     * @param population      all genotypes alive in that generation
     */
    public void recordGeneration(int generation, List<Genotype> population) {
        if (population == null || population.isEmpty()) {
            throw new IllegalArgumentException("Cannot record an empty/null population for generation " + generation);
        }

        int locusCount = population.get(0).getLocusCount();
        Map<Integer, GenerationSnapshot> perLocus = new LinkedHashMap<>();

        for (int locus = 0; locus < locusCount; locus++) {
            Map<Integer, Double> alleleFreq = computeAlleleFrequencies(population, locus);
            Map<String, Double> observed = computeObservedGenotypeRatios(population, locus);
            Map<String, Double> expected = computeHardyWeinbergExpected(alleleFreq);

            perLocus.put(locus, new GenerationSnapshot(generation, locus, alleleFreq, observed, expected));
        }

        history.put(generation, perLocus);
    }

    /** Allele frequency = (count of that allele across all individuals) / (2 * population size). */
    private Map<Integer, Double> computeAlleleFrequencies(List<Genotype> population, int locus) {
        Map<Integer, Integer> counts = new HashMap<>();
        int totalAlleles = 0;

        for (Genotype g : population) {
            int[] alleles = g.getAllelesAt(locus);
            for (int allele : alleles) {
                counts.merge(allele, 1, Integer::sum);
                totalAlleles++;
            }
        }

        Map<Integer, Double> freqs = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            freqs.put(entry.getKey(), entry.getValue() / (double) totalAlleles);
        }
        return freqs;
    }

    /** Observed genotype ratios, keyed by a canonical "allele1/allele2" string (sorted, unordered). */
    private Map<String, Double> computeObservedGenotypeRatios(List<Genotype> population, int locus) {
        Map<String, Integer> counts = new LinkedHashMap<>();

        for (Genotype g : population) {
            int[] alleles = g.getAllelesAt(locus);
            String key = canonicalGenotypeKey(alleles[0], alleles[1]);
            counts.merge(key, 1, Integer::sum);
        }

        Map<String, Double> ratios = new LinkedHashMap<>();
        int n = population.size();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            ratios.put(entry.getKey(), entry.getValue() / (double) n);
        }
        return ratios;
    }

    /**
     * Hardy-Weinberg expected genotype ratios from allele frequencies.
     *
     * For the classic 2-allele case: p^2, 2pq, q^2.
     * Generalized here to N alleles per locus (in case a locus has more than
     * two segregating alleles across the population, even though each
     * individual only carries two): for alleles i, j with frequencies p_i, p_j,
     *   expected(i/i) = p_i^2
     *   expected(i/j) = 2 * p_i * p_j   (i != j)
     */
    private Map<String, Double> computeHardyWeinbergExpected(Map<Integer, Double> alleleFrequencies) {
        Map<String, Double> expected = new LinkedHashMap<>();
        List<Integer> alleleIds = new ArrayList<>(alleleFrequencies.keySet());

        for (int i = 0; i < alleleIds.size(); i++) {
            int alleleI = alleleIds.get(i);
            double pI = alleleFrequencies.get(alleleI);

            // Homozygous case
            String homoKey = canonicalGenotypeKey(alleleI, alleleI);
            expected.merge(homoKey, pI * pI, Double::sum);

            for (int j = i + 1; j < alleleIds.size(); j++) {
                int alleleJ = alleleIds.get(j);
                double pJ = alleleFrequencies.get(alleleJ);
                String heteroKey = canonicalGenotypeKey(alleleI, alleleJ);
                expected.merge(heteroKey, 2 * pI * pJ, Double::sum);
            }
        }
        return expected;
    }

    private String canonicalGenotypeKey(int a, int b) {
        int lo = Math.min(a, b);
        int hi = Math.max(a, b);
        return lo + "/" + hi;
    }

    public GenerationSnapshot getSnapshot(int generation, int locus) {
        Map<Integer, GenerationSnapshot> perLocus = history.get(generation);
        if (perLocus == null) {
            throw new IllegalArgumentException("No data recorded for generation " + generation);
        }
        GenerationSnapshot snapshot = perLocus.get(locus);
        if (snapshot == null) {
            throw new IllegalArgumentException("No data recorded for locus " + locus + " at generation " + generation);
        }
        return snapshot;
    }

    public List<Integer> getRecordedGenerations() {
        return new ArrayList<>(history.keySet());
    }

    /**
     * Sum of |observed - expected| across all genotype classes at a locus/generation.
     * A quick scalar "how far from Hardy-Weinberg equilibrium is this population"
     * signal, useful for sanity-checking selection pressure effects generation over generation.
     */
    public double computeDeviationFromEquilibrium(int generation, int locus) {
        GenerationSnapshot snapshot = getSnapshot(generation, locus);
        double totalDeviation = 0.0;

        for (String genotypeKey : snapshot.expectedGenotypeRatios.keySet()) {
            double expected = snapshot.expectedGenotypeRatios.getOrDefault(genotypeKey, 0.0);
            double observed = snapshot.observedGenotypeRatios.getOrDefault(genotypeKey, 0.0);
            totalDeviation += Math.abs(observed - expected);
        }
        return totalDeviation;
    }
}
