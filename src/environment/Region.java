package environment;

import java.util.ArrayList;
import java.util.List;

public class Region {
    private final String name;
    private final String terrainColor;
    private final double resourceLevel;
    private final List<Region> subRegions = new ArrayList<>();

    public Region(String name, String terrainColor, double resourceLevel) {
        this.name = name;
        this.terrainColor = terrainColor;
        this.resourceLevel = resourceLevel;
    }

    public void addSubRegion(Region region) {
        subRegions.add(region);
    }

    public void printStructure(String indent) {
        System.out.println(indent + name + " [terrain=" + terrainColor
            + ", resource=" + resourceLevel + "]");
        for (Region r : subRegions) {
            r.printStructure(indent + "  ");
        }
    }
}