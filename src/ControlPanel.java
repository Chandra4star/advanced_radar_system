import javax.swing.*;

public class ControlPanel extends JPanel {
    public ControlPanel(RadarSimulator simulator) {
        JButton startButton = new JButton("Start Radar");
        JButton stopButton = new JButton("Stop Radar");
        JButton addRandomButton = new JButton("Add Random Aircraft");

        startButton.addActionListener(e -> simulator.startSimulation());
        stopButton.addActionListener(e -> simulator.stopSimulation());
        addRandomButton.addActionListener(e -> simulator.addRandomAircraft());

        add(startButton);
        add(stopButton);
        add(addRandomButton);
    }
}
