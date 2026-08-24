package environment;

import core.Organism;

public class FleeingBehavior implements Behavior {
    @Override
    public void act(Organism self) {
        System.out.println(self + " is fleeing.");
    }
}