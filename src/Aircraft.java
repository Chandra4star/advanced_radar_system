public class Aircraft {
    public enum TargetType { FIGHTER, BOMBER, DRONE }

    private double x, y;
    private double speed;
    private double heading;
    private double stealthFactor;
    private TargetType type;

    public Aircraft(double x, double y, double speed, double heading, double stealthFactor, TargetType type) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.heading = heading;
        this.stealthFactor = stealthFactor;
        this.type = type;
    }

    public void move(double deltaTime) {
        double radians = Math.toRadians(heading);
        x += speed * deltaTime * Math.cos(radians);
        y += speed * deltaTime * Math.sin(radians);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getStealthFactor() { return stealthFactor; }
    public TargetType getType() { return type; }
    public double getDistance() { return Math.sqrt(x * x + y * y); }
}
