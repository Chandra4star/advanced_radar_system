import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RadarPanel extends JPanel {
    private List<Aircraft> aircraftList;
    private double sweepAngle = 0;

    public void setAircraftList(List<Aircraft> aircraftList) {
        this.aircraftList = aircraftList;
    }

    public RadarPanel() {
        Timer sweepTimer = new Timer(50, e -> {
            sweepAngle += 2;
            if (sweepAngle >= 360) sweepAngle = 0;
            repaint();
        });
        sweepTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawRadar(g);
    }

    private void drawRadar(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        int centerX = w / 2;
        int centerY = h / 2;
        int radius = 400;

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, w, h);

        g2d.setColor(Color.GREEN);
        g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        g2d.drawLine(centerX, centerY,
                centerX + (int)(radius * Math.cos(Math.toRadians(sweepAngle))),
                centerY - (int)(radius * Math.sin(Math.toRadians(sweepAngle))));

        if (aircraftList != null) {
            for (Aircraft ac : aircraftList) {
                double scale = radius / RadarSimulator.RADAR_RANGE;
                int screenX = centerX + (int)(ac.getX() * scale);
                int screenY = centerY - (int)(ac.getY() * scale);

                switch (ac.getType()) {
                    case FIGHTER: g2d.setColor(Color.BLUE); break;
                    case BOMBER:  g2d.setColor(Color.RED); break;
                    case DRONE:   g2d.setColor(Color.YELLOW); break;
                }

                int blipSize = (int)(10 * (1.0 - ac.getStealthFactor()));
                if (blipSize < 3) blipSize = 3;
                g2d.fillOval(screenX - blipSize / 2, screenY - blipSize / 2, blipSize, blipSize);
            }
        }
    }
}
