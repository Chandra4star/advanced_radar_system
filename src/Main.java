import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Advanced Radar Detection System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 1000);
            frame.setLayout(new BorderLayout());

            RadarPanel radarPanel = new RadarPanel();
            RadarSimulator simulator = new RadarSimulator(radarPanel);
            ControlPanel controlPanel = new ControlPanel(simulator);

            frame.add(radarPanel, BorderLayout.CENTER);
            frame.add(controlPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}
