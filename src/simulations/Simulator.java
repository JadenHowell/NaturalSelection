public class Simulator {

    boolean isPaused = true;
    int time = 0;

    int numStartEnt;
    int entStartSpeed;
    int entStartSize;

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
        time = 0;
        populationGraph.reset();
    }

    public void setEntStartSize(int entStartSize) {
        this.entStartSize = entStartSize;
        System.out.println("entStartSize = " + entStartSize);
    }

    public void setEntStartSpeed(int entStartSpeed) {
        this.entStartSpeed = entStartSpeed;
        System.out.println("entStartSpeed = " + entStartSpeed);
    }

    public void setNumStartEnt(int numStartEnt) {
        this.numStartEnt = numStartEnt;
        System.out.println("numStartEnt = " + numStartEnt);
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
