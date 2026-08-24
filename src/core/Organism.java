package core;

import environment.LifeStage;
import environment.Behavior;

public class Organism {
    private final Genotype genotype;
    private Phenotype phenotype;
    private LifeStage lifeStage;
    private Behavior behavior;

    public Organism(Genotype genotype, Phenotype phenotype) {
        this.genotype = genotype;
        this.phenotype = phenotype;
        this.lifeStage = LifeStage.JUVENILE;
    }

    public void ageUp() {
        this.lifeStage = this.lifeStage.next();
    }

    public boolean canReproduce() {
        return lifeStage.canReproduce();
    }

    public Genotype getGenotype() { return genotype; }
    public Phenotype getPhenotype() { return phenotype; }
    public LifeStage getLifeStage() { return lifeStage; }
}