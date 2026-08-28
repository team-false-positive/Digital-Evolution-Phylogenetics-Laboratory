package visualization;

public interface SimulationListener {

    /**
     * Called every simulation tick.
     * @param generation current generation number
     * @param tickData placeholder payload — will be refined once
     *                  Module B (Evolution Engine) defines what a "tick" contains.
     */
    void onTick(int generation, Object tickData);

    /**
     * Called when a full generation cycle completes.
     * @param generation the generation number that just completed
     */
    void onGenerationComplete(int generation);
}