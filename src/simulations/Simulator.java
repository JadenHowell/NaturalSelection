import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Simulator implements ActionListener {

    private final int tickDelay = 100;

    boolean isPaused = true;

    private int time = 0, numStartEnt, entStartSpeed, entStartSize;
    private double survivalChance = .99, replicationChance = .01;

    static private Timer timer;
    static private Simulator instance;
    static private PopulationGraph populationGraph;
    static private TraitGraph traitGraph;

    private List<Entity> entities;

    private Simulator(){}

    public static Simulator getInstance(){
        if(instance == null){
            instance = new Simulator();
        }
        return instance;
    }

    public void start(){
        entities = SimHelp.createEntities(numStartEnt, survivalChance,replicationChance, entStartSpeed, entStartSize);
        //System.out.println(entities.toString());

        timer = new Timer(tickDelay, this);
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

    public void setSurvivalChance(double survivalChance) { this.survivalChance = survivalChance; }

    public void setReplicationChance(double replicationChance) { this.replicationChance = replicationChance; }

    public void tick(){
        if (isPaused) {
            return;
        }
        populationGraph.dataReceived(time, entities.size());
        traitGraph.dataReceived("Speed", time, SimHelp.getAverageGeneValue(entities, "Speed"));
        traitGraph.dataReceived("Size", time, SimHelp.getAverageGeneValue(entities, "Size"));
        time++;
        updatePopSize();
    }

    private void updatePopSize(){
        List<Entity> newEnts = new ArrayList<>();
        for(Entity ent : entities){
            if(SimHelp.entityLivesOn(ent)){
                newEnts.add(ent);
            }
            if(SimHelp.entityReproduces(ent)){
                newEnts.add(ent.replicate());
            }
        }
        entities = newEnts;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tick();
    }
}
