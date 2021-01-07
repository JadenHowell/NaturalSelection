

public class SizeGene extends Gene {
    SizeGene(double value){
        name = "Size";
        this.value = value;
    }

    //TODO: Make rules for size mutations
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
