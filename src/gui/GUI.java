import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class GUI extends JFrame implements ActionListener {

    PopulationGraph populationGraph;
    int time;
    Simulator simulator = Simulator.getInstance();

    public GUI(){
        super("Natural Selection Simulator");

        populationGraph = new PopulationGraph();
        time = 0;

        initGUI();
        setupIcon();
        setLocationRelativeTo(null); //This centers it in the screen for some reason
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        new Timer(100, this).start();
    }


    private void setupIcon(){
        URL iconURL = getClass().getResource("res/dna.png");
        ImageIcon image = new ImageIcon(iconURL);
        setIconImage(image.getImage());
    }

    private void initGUI(){
        add(populationGraph.getChart(), BorderLayout.CENTER);
        JPanel buttonPanel = new ButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//      System.out.println("ActionPerformed event triggered at time = " + time);
        if(!simulator.isPaused) {
            populationGraph.dataReceived(time, Math.random());
            time++;
        }
        if(simulator.needsReset){
            populationGraph.reset();
            simulator.doneReset();
            time = 0;
        }
    }
}
