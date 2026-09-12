package genetics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MeiosisReproductionTest {

    @Test
    void noMutation_offspringInheritsExactParentAlleles() {
        Gene size = new Gene("Size", new AdditiveRule());
        Allele largeAllele = new Allele("Large", 1.0, false);
        Allele smallAllele = new Allele("Small", 0.4, false);

        Genotype parent1 = new Genotype();
        parent1.setAllelePair(size, largeAllele, largeAllele);

        Genotype parent2 = new Genotype();
        parent2.setAllelePair(size, smallAllele, smallAllele);

        ReproductionStrategy strategy = new MeiosisReproduction(0.0, 0.0);
        Genotype offspring = strategy.reproduce(parent1, parent2);

        assertEquals(0.7, offspring.getPhenotypeValue(size), 0.0001);
    }

    @Test
    void highMutationRate_altersAlleleEffectValue() {
        Gene size = new Gene("Size", new AdditiveRule());
        Allele allele = new Allele("Medium", 0.5, false);

        Genotype parent1 = new Genotype();
        parent1.setAllelePair(size, allele, allele);
        Genotype parent2 = new Genotype();
        parent2.setAllelePair(size, allele, allele);

        ReproductionStrategy strategy = new MeiosisReproduction(1.0, 0.3);
        Genotype offspring = strategy.reproduce(parent1, parent2);

        assertNotEquals(0.5, offspring.getPhenotypeValue(size), 0.0001);
    }
}