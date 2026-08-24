package genetics;

import java.util.HashMap;
import java.util.Map;

public class Genotype {
    private final Map<Gene, Allele[]> alleleMap = new HashMap<>();

    public void setAllelePair(Gene gene, Allele allele1, Allele allele2) {
        alleleMap.put(gene, new Allele[]{allele1, allele2});
    }

    public double getPhenotypeValue(Gene gene) {
        Allele[] pair = alleleMap.get(gene);
        if (pair == null) {
            throw new IllegalArgumentException("Gene not present in genotype: " + gene.getName());
        }
        return gene.computePhenotype(pair[0], pair[1]);
    }

    public boolean hasGene(Gene gene) {
        return alleleMap.containsKey(gene);
    }
}