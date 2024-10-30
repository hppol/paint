import java.awt.Color;
import java.awt.Point;

public class Dot {
    private Point position;
    private Color color;

    public Dot(Point position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Point getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }
}