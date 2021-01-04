public class Simulator {

    boolean isPaused = true;
    boolean needsReset = false;

    static Simulator instance;

    private Simulator(){}

    public static Simulator getInstance(){
        if(instance == null){
            instance = new Simulator();
        }
        return instance;
    }

    public void pause(){
        isPaused = true;
    }

    public void unpause(){
        isPaused = false;
    }

    public void reset(){
        needsReset = true;
    }

    public void doneReset(){
        needsReset = false;
    }

}
