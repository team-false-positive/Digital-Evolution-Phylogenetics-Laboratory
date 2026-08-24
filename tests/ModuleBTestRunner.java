public class ModuleBTestRunner {
    public static void main(String[] args) {
        // Test LifeStage transitions
        Organism o = new Organism(null, null); // pass real Genotype/Phenotype once Module A is ready
        System.out.println("Stage: " + o.getLifeStage() + ", canReproduce: " + o.canReproduce());
        o.ageUp();
        System.out.println("Stage: " + o.getLifeStage() + ", canReproduce: " + o.canReproduce());
        o.ageUp();
        System.out.println("Stage: " + o.getLifeStage() + ", canReproduce: " + o.canReproduce());

        // Test Region composite
        Region map = new Region("World", "MIXED", 0.5);
        Region forest = new Region("Forest", "GREEN", 0.8);
        Region desert = new Region("Desert", "BROWN", 0.2);
        map.addSubRegion(forest);
        map.addSubRegion(desert);
        map.printStructure("");
    }
}