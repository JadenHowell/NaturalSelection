import java.util.HashMap;

public class Entity {
    private HashMap<String, Gene> genes;
    private double replicationChance;
    private double survivalChance;


    Entity(double survivalChance, double replicationChance){
        genes = new HashMap<>();
        this.replicationChance = replicationChance;
        this.survivalChance = survivalChance;
    }

    public Entity replicateExact(){
        Entity child = new Entity(survivalChance, replicationChance);
        child.addGene(new SizeGene(this.genes.get("Size").getValue()));
        child.addGene(new SpeedGene(this.genes.get("Speed").getValue()));
        return child;
    }

    public Entity replicate(){
        Entity child = replicateExact();
        for(String geneName : child.genes.keySet()){
            child.genes.get(geneName).mutateValue();
        }
        child.updateChances();
        return child;
    }

    private void updateChances(){
        if(genes.get("Speed").getValue() <= 0 || genes.get("Size").getValue() <= 0){
            survivalChance = 0;
            replicationChance = 0;
            return;
        }
        survivalChance = survivalChance + (genes.get("Speed").getValue()*.001)-(genes.get("Size").getValue()*.001);
        replicationChance = replicationChance + (genes.get("Size").getValue()*.001)-(genes.get("Speed").getValue()*.001);
    }

    public boolean addGene(Gene gene) {
        if (genes.containsKey(gene.getName())) {
            System.out.println("Trying to add a gene already present in this entity: " + gene.getName());
            return false;
        }
        genes.put(gene.getName(), gene);
        return true;
    }

    public double getGeneVal(String g){
        if(!genes.containsKey(g)){
            return 0;
        }
        return genes.get(g).getValue();
    }

    public double getReplicationChance() {
        return replicationChance;
    }

    public double getSurvivalChance() {
        return survivalChance;
    }

    public void setReplicationChance(double replicationChance) {
        this.replicationChance = replicationChance;
    }

    public void setSurvivalChance(double survivalChance) {
        this.survivalChance = survivalChance;
    }

    @Override
    public String toString(){
        String out = "Entity, genes: { ";
        for(String key : genes.keySet()){
            out += genes.get(key).toString()+ " ";
        }
        out += "}";
        return out;
    }
}
