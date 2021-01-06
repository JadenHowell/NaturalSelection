import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GUI extends JFrame {

    PopulationGraph populationGraph;
    TraitGraph traitGraph;
    Simulator simulator = Simulator.getInstance();

    public GUI(){
        super("Natural Selection Simulator");

        populationGraph = new PopulationGraph();
        traitGraph = new TraitGraph();
        simulator.setPopulationGraph(populationGraph);
        simulator.setTraitGraph(traitGraph);

        initGUI();
        setupIcon();
        setLocationRelativeTo(null); //This centers it in the screen for some reason
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        simulator.start();
    }


    private void setupIcon(){
        URL iconURL = getClass().getResource("res/dna.png");
        ImageIcon image = new ImageIcon(iconURL);
        setIconImage(image.getImage());
    }

    //TODO: What the fetch, how does gridbaglayout work? Figure that out
    private void initGUI(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH; //This means each component will expand to fill available space in given amount of cells

        //c.gridx/gridy determines the top left cell of the component being placed
        c.gridx = 0;
        c.gridy = 0;
        //c.gridheight/gridwieght determines how many cells the component should take up, default is 1
        //c.gridheight = 2;
        //c.gridwidth = 2;
        add(new JPanel().add(populationGraph.getChart()), c);


        c.gridx = 1;
        c.gridy = 0;
        //c.gridheight = 2;
        //c.gridwidth = 2;
        simulator.initTraits();
        add(traitGraph.getChart(), c);


        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        JPanel buttonPanel = new ButtonPanel();
        add(buttonPanel, c);

        pack();
        //gridbaglayouts shrink items to minimum size if trying to make something smaller than it's preferred size
        //      because of this, the frame cannot be shrunk below the size created
        //      setMinimumSize will allow frame and elements to grow, but cannot go smaller.
        setMinimumSize(getSize());
    }

}
