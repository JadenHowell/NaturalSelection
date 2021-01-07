

public abstract class Gene {
    protected String name;
    protected double value;
    protected double mutationChance = .50;

    Gene(){}

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public abstract void mutateValue();

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return name + ":" + value;
    }
}
