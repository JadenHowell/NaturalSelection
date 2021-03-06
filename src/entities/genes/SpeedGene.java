

public class SpeedGene extends Gene {
    SpeedGene(double value){
        name = "Speed";
        this.value = value;
    }

    //TODO: Make rules for speed mutations
    @Override
    public void mutateValue(){
        if (Math.random() < mutationChance) { // This means there is a 5% chance of mutating
            if(Math.random() < .5){
                //50% chance to mutate up, 50% chance to mutate down
                value = value + Math.random() * 10;
            }else{
                value = value - Math.random() * 10;
            }
        }
    }
}
