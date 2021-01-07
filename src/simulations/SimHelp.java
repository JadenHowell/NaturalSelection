import java.util.ArrayList;
import java.util.List;

public class SimHelp {

    public static List<Entity> createEntities(int numEntities, double survivalChance, double replicationChance, int startSpeed, int startSize){
        List<Entity> allEnts = new ArrayList<>();

        SizeGene size = new SizeGene(startSize);
        SpeedGene speed = new SpeedGene(startSpeed);

        for(int i = 0; i < numEntities; i ++){
            Entity newEnt = new Entity(survivalChance, replicationChance);
            newEnt.addGene(speed);
            newEnt.addGene(size);
            allEnts.add(newEnt);
        }

        return allEnts;
    }

    public static boolean entityLivesOn(Entity entity){
        return Math.random() < entity.getSurvivalChance();
    }

    public static boolean entityReproduces(Entity entity){
        return  Math.random() < entity.getReplicationChance();
    }

    public static double getAverageGeneValue(List<Entity> allEnts, String gene){
        double total = 0;
        for(Entity ent : allEnts){
            total += ent.getGeneVal(gene);
        }
        return total / allEnts.size();
    }

}
