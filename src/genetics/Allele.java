package genetics;

public class Allele {
    private final String variantName;
    private final double effectValue;
    private final boolean dominant;

    public Allele(String variantName, double effectValue, boolean dominant) {
        this.variantName = variantName;
        this.effectValue = effectValue;
        this.dominant = dominant;
    }

    public String getVariantName() { return variantName; }
    public double getEffectValue() { return effectValue; }
    public boolean isDominant() { return dominant; }
}