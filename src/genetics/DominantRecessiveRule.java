package genetics;

public class DominantRecessiveRule implements ExpressionRule {
    @Override
    public double express(Allele allele1, Allele allele2) {
        boolean d1 = allele1.isDominant();
        boolean d2 = allele2.isDominant();

        if (d1 && !d2) return allele1.getEffectValue();
        if (d2 && !d1) return allele2.getEffectValue();

        // homozygous (both dominant or both recessive) — same allele expressed twice,
        // so just return one copy's value, not an average of two identical states
        return allele1.getEffectValue();
    }
}