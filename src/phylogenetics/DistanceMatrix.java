import java.util.List;

/**
 * Module C — Phylogenetics + Analysis
 *
 * Computes and stores pairwise genetic distances between a set of organisms/genotypes.
 * Distance metric: Hamming distance across allele sequences (adjust if your team's
 * Genotype representation differs).
 */
public class DistanceMatrix {

    private final double[][] matrix;
    private final int size;
    private final List<Genotype> genotypes;

    public DistanceMatrix(List<Genotype> genotypes) {
        this.genotypes = genotypes;
        this.size = genotypes.size();
        this.matrix = new double[size][size];
        computeMatrix();
    }

    private void computeMatrix() {
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                double d = computeDistance(genotypes.get(i), genotypes.get(j));
                matrix[i][j] = d;
                matrix[j][i] = d; // symmetric
            }
            matrix[i][i] = 0.0; // diagonal
        }
    }

    /**
     * TODO: replace with your team's actual Genotype structure.
     * Placeholder assumes Genotype exposes getAlleles() -> comparable sequence.
     */
    private double computeDistance(Genotype g1, Genotype g2) {
        // Hamming distance placeholder — count differing positions
        // Adjust once Genotype contract is finalized
        throw new UnsupportedOperationException(
            "Implement once Genotype data contract is locked"
        );
    }

    public double getDistance(int i, int j) {
        if (i < 0 || j < 0 || i >= size || j >= size) {
            throw new IndexOutOfBoundsException("Invalid genotype index");
        }
        return matrix[i][j];
    }

    public int size() {
        return size;
    }

    public double[][] getRawMatrix() {
        return matrix;
    }
}

/**
 * Placeholder — replace with the actual shared Genotype class once locked.
 */
class Genotype {
    // to be defined by data-contract owner
}
