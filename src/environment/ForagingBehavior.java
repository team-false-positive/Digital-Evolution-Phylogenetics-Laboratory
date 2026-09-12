package environment;

import core.Organism;

public class ForagingBehavior implements Behavior {
    @Override
    public void act(Organism self) {
        System.out.println(self + " is foraging.");
    }
}