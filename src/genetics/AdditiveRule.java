package genetics;

public class AdditiveRule implements ExpressionRule {
    @Override
    public double express(Allele allele1, Allele allele2) {
        return (allele1.getEffectValue() + allele2.getEffectValue()) / 2.0;
    }
}