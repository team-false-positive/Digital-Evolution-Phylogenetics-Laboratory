package genetics;

public class Gene {
    private final String name;
    private final ExpressionRule expressionRule;

    public Gene(String name, ExpressionRule expressionRule) {
        this.name = name;
        this.expressionRule = expressionRule;
    }

    public String getName() { return name; }

    public double computePhenotype(Allele allele1, Allele allele2) {
        return expressionRule.express(allele1, allele2);
    }
}