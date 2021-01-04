import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    JButton start;
    JButton pause;
    JButton cont;
    JButton restart;

    Simulator simulator;

    ButtonPanel(){
        simulator = Simulator.getInstance();

        setupListeners();

        add(start);
        add(pause);
        add(cont);
        add(restart);
    }

    private void setupListeners(){
        start = new JButton("Start");
        pause = new JButton("Pause");
        cont = new JButton("Resume");
        restart = new JButton("Reset");

        cont.setEnabled(false);
        pause.setEnabled(false);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause.setEnabled(true);
                cont.setEnabled(false);
                start.setEnabled(false);
                simulator.unpause();
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

        restart.addActionListener(new ActionListener() {
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

}
