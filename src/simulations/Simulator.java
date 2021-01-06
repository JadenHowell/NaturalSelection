public class Simulator {

    boolean isPaused = true;
    boolean needsReset = false;
    int time = 0;

    static Simulator instance;
    static PopulationGraph populationGraph;

    private Simulator(){}

    public static Simulator getInstance(){
        if(instance == null){
            instance = new Simulator();
        }
        return instance;
    }

    public void pause(){ isPaused = true; }

    public void unpause(){
        isPaused = false;
    }

    public void reset(){
        populationGraph.reset();
    }

    public void setPopulationGraph(PopulationGraph popGraph){  populationGraph = popGraph;  }

    public void tick(){
        if (isPaused) {
            return;
        }

        populationGraph.dataReceived(time, Math.random());
        time++;

    }

}
