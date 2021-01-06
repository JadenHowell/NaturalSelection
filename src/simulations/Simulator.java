import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulator implements ActionListener {

    boolean isPaused = true;

    private int time = 0, numStartEnt, entStartSpeed, entStartSize;

    static private Timer timer;
    static private Simulator instance;
    static private PopulationGraph populationGraph;
    static private TraitGraph traitGraph;

    private Simulator(){}

    public static Simulator getInstance(){
        if(instance == null){
            instance = new Simulator();
        }
        return instance;
    }

    public void start(){
        timer = new Timer(100, this);
        timer.start();
    }

    public void pause(){ isPaused = true; }

    public void unpause(){
        isPaused = false;
    }

    public void reset(){
        time = 0;
        timer.stop();
        populationGraph.reset();
        traitGraph.reset();
    }

    public void initTraits(){
        traitGraph.addNewSeries("Speed");
        traitGraph.addNewSeries("Size");
    }

    public void setEntStartSize(int entStartSize) { this.entStartSize = entStartSize; }

    public void setEntStartSpeed(int entStartSpeed) { this.entStartSpeed = entStartSpeed; }

    public void setNumStartEnt(int numStartEnt) { this.numStartEnt = numStartEnt; }

    public void setPopulationGraph(PopulationGraph popGraph){ populationGraph = popGraph;  }

    public void setTraitGraph(TraitGraph traitGraph) { Simulator.traitGraph = traitGraph; }

    public void tick(){
        if (isPaused) {
            return;
        }
        populationGraph.dataReceived(time, Math.random());
        traitGraph.dataReceived("Speed", time, Math.random());
        traitGraph.dataReceived("Size", time, Math.random());
        time++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tick();
    }
}
