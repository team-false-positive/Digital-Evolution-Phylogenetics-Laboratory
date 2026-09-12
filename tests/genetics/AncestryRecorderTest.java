package genetics;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AncestryRecorderTest {

    @Test
    void recordBirth_thenRetrieveParents() {
        AncestryRecorder recorder = new AncestryRecorder();
        recorder.recordBirth(3, 1, 2, 1);

        assertEquals(List.of(1, 2), recorder.getParentsOf(3));
    }

    @Test
    void unknownChild_returnsEmptyList() {
        AncestryRecorder recorder = new AncestryRecorder();
        assertTrue(recorder.getParentsOf(99).isEmpty());
    }
}