package genetics;

public interface ReproductionStrategy {
    Genotype reproduce(Genotype parent1, Genotype parent2);
}