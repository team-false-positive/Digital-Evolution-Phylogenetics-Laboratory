package genetics;

import java.util.ArrayList;
import java.util.List;

public class AncestryRecorder {

    public static class BirthRecord {
        public final int childId;
        public final int parent1Id;
        public final int parent2Id;
        public final int generation;

        public BirthRecord(int childId, int parent1Id, int parent2Id, int generation) {
            this.childId = childId;
            this.parent1Id = parent1Id;
            this.parent2Id = parent2Id;
            this.generation = generation;
        }
    }

    private final List<BirthRecord> records = new ArrayList<>();

    public void recordBirth(int childId, int parent1Id, int parent2Id, int generation) {
        records.add(new BirthRecord(childId, parent1Id, parent2Id, generation));
    }

    public List<BirthRecord> getRecords() {
        return records;
    }

    public List<Integer> getParentsOf(int childId) {
        for (BirthRecord r : records) {
            if (r.childId == childId) {
                return List.of(r.parent1Id, r.parent2Id);
            }
        }
        return List.of();
    }
}