package genetics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GenotypeTest {

    @Test
    void dominantRecessive_heterozygous_expressesDominant() {
        Gene coatColor = new Gene("CoatColor", new DominantRecessiveRule());
        Allele dominantAllele = new Allele("Dark", 1.0, true);
        Allele recessiveAllele = new Allele("Light", 0.2, false);

        Genotype genotype = new Genotype();
        genotype.setAllelePair(coatColor, dominantAllele, recessiveAllele);

        assertEquals(1.0, genotype.getPhenotypeValue(coatColor));
    }

    @Test
    void dominantRecessive_homozygousRecessive_expressesRecessive() {
        Gene coatColor = new Gene("CoatColor", new DominantRecessiveRule());
        Allele recessive1 = new Allele("Light", 0.2, false);
        Allele recessive2 = new Allele("Light", 0.2, false);

        Genotype genotype = new Genotype();
        genotype.setAllelePair(coatColor, recessive1, recessive2);

        assertEquals(0.2, genotype.getPhenotypeValue(coatColor));
    }

    @Test
    void additiveRule_blendsBothAlleles() {
        Gene size = new Gene("Size", new AdditiveRule());
        Allele large = new Allele("Large", 1.0, false);
        Allele small = new Allele("Small", 0.4, false);

        Genotype genotype = new Genotype();
        genotype.setAllelePair(size, large, small);

        assertEquals(0.7, genotype.getPhenotypeValue(size), 0.0001);
    }

    @Test
    void missingGene_throwsException() {
        Gene size = new Gene("Size", new AdditiveRule());
        Genotype genotype = new Genotype();

        assertThrows(IllegalArgumentException.class, () -> genotype.getPhenotypeValue(size));
    }
}