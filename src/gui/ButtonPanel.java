import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    JButton start, pause, cont, reset;
    CustomTextField numEntitiesField, startingSpeedField, startingSizeField;

    Simulator simulator;

    ButtonPanel(){
        simulator = Simulator.getInstance();

        setupListeners();

        add(start);
        add(pause);
        add(cont);
        add(reset);
        add(numEntitiesField);
        add(startingSpeedField);
        add(startingSizeField);
    }

    private void setupListeners(){
        start = new JButton("Start");
        pause = new JButton("Pause");
        cont = new JButton("Resume");
        reset = new JButton("Reset");
        numEntitiesField = new CustomTextField();
        numEntitiesField.setPlaceholder("Number of entities (default 10)");
        startingSpeedField = new CustomTextField();
        startingSpeedField.setPlaceholder("Starting speed (default 10)");
        startingSizeField = new CustomTextField();
        startingSizeField.setPlaceholder("Starting size (default 10)");

        GridLayout layout = new GridLayout(2, 4);
        layout.setHgap(10);
        layout.setVgap(5);
        setLayout(layout);

        cont.setEnabled(false);
        pause.setEnabled(false);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(true);
                cont.setEnabled(false);
                start.setEnabled(false);

                onPressStart();
            }
        });

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(false);
                cont.setEnabled(true);
                simulator.pause();
            }
        });

        cont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(true);
                cont.setEnabled(false);
                simulator.unpause();
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start.setEnabled(true);
                pause.setEnabled(false);
                cont.setEnabled(false);
                simulator.pause();
                simulator.reset();
            }
        });

    }


    private void onPressStart(){
        String startSize = startingSizeField.getText();
        String startSpeed = startingSpeedField.getText();
        String startNum = numEntitiesField.getText();

        if(startNum.equals("") || !isNumeric(startNum)) {
            simulator.setNumStartEnt(10);
        }else{
            simulator.setNumStartEnt(Integer.parseInt(startNum));
        }

        if(startSpeed.equals("") || !isNumeric(startSpeed)) {
            simulator.setEntStartSpeed(10);
        }else{
            simulator.setEntStartSpeed(Integer.parseInt(startSpeed));
        }

        if(startSize.equals("") || !isNumeric(startSize)) {
            simulator.setEntStartSize(10);
        }else{
            simulator.setEntStartSize(Integer.parseInt(startSize));
        }

        simulator.unpause();
        simulator.start();
    }

    //This checks if a string can be parsed to an integer
    private static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

}
