package core;

import environment.LifeStage;
import environment.Behavior;
import genetics.Genotype;

public class Organism {
    private final Genotype genotype;
    private LifeStage lifeStage;
    private Behavior behavior;

    public Organism(Genotype genotype) {
        this.genotype = genotype;
        this.lifeStage = LifeStage.JUVENILE; // everyone starts as a baby
    }

    public void ageUp() {
        this.lifeStage = this.lifeStage.next();
    }

    public boolean canReproduce() {
        return lifeStage.canReproduce();
    }

    public Genotype getGenotype() {
        return genotype;
    }

    public LifeStage getLifeStage() {
        return lifeStage;
    }

    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    public Behavior getBehavior() {
        return behavior;
    }
}