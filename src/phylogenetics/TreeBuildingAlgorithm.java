/**
 * Module C — Phylogenetics + Analysis
 *
 * Strategy interface for tree-building algorithms. This lets you swap in
 * different clustering/tree-reconstruction strategies (UPGMA now, others later
 * if the team wants to compare methods) without changing calling code.
 */
public interface TreeBuildingAlgorithm {

    /**
     * Builds a phylogenetic tree from a precomputed distance matrix.
     *
     * @param distanceMatrix pairwise distances between organisms/genotypes
     * @return a reconstructed phylogenetic tree
     */
    PhylogeneticTree buildTree(DistanceMatrix distanceMatrix);
}

/**
 * Placeholder — replace with the team's shared tree representation once agreed.
 * Likely needs: root node, child links, leaf labels (organism IDs), branch lengths.
 */
class PhylogeneticTree {
    // to be defined — coordinate with team on shared structure
}
