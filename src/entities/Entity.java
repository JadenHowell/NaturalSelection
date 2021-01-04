import java.util.HashMap;
import genes.Gene;

public class Entity {
    private HashMap<String, Gene> genes;
    private int replicationChance;
    private int survivalChance;

    Entity(int survivalChance, int replicationChance){
        genes = new HashMap<>();
        this.replicationChance = replicationChance;
        this.survivalChance = survivalChance;
    }

    public Entity replicateExact(){
        return new Entity(survivalChance, replicationChance);
    }

    public Entity replicate(){
        return replicateExact();
    }

    public boolean addGene(Gene gene) {
        if (genes.containsKey(gene.getName())) {
            System.out.println("Trying to add a gene already present in this entity: " + gene.getName());
            return false;
        }
        genes.put(gene.getName(), gene);
        return true;
    }

    public int getReplicationChance() {
        return replicationChance;
    }

    public int getSurvivalChance() {
        return survivalChance;
    }

    public void setReplicationChance(int replicationChance) {
        this.replicationChance = replicationChance;
    }

    public void setSurvivalChance(int survivalChance) {
        this.survivalChance = survivalChance;
    }
}
