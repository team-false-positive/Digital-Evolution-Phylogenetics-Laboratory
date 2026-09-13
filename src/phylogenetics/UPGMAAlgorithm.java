/**
 * Module C — Phylogenetics + Analysis
 *
 * Week 2 milestone: STUB implementation. Just needs to compile, implement the
 * Strategy interface, and return a plausible (even if not fully correct) tree
 * so the rest of the pipeline (evolve -> record -> reconstruct -> RF distance)
 * can be integration-tested end-to-end by Week 3.
 *
 * Full UPGMA logic (below, commented) is the real Week 3+ implementation:
 *   1. Start with each organism as its own cluster/leaf
 *   2. Find the two closest clusters in the distance matrix
 *   3. Merge them into a new node; new branch lengths = distance / 2
 *   4. Recompute distances from the new cluster to all others (average linkage)
 *   5. Repeat until one cluster (the root) remains
 */
public class UPGMAAlgorithm implements TreeBuildingAlgorithm {

    @Override
    public PhylogeneticTree buildTree(DistanceMatrix distanceMatrix) {
        // --- STUB for Week 2 ---
        // TODO (Week 3): implement real UPGMA clustering described above.
        // For now, return an empty/placeholder tree so integration tests
        // can run without a NullPointerException further down the pipeline.
        return new PhylogeneticTree();
    }
}
