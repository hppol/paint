import java.awt.Color;
import java.awt.Point;

public class Dot {
    private Point position;
    private Color color;
    private int thickness; 

    public Dot(Point position, Color color, int thickness) {
        this.position = position;
        this.color = color;
        this.thickness = thickness;
    }

    public Point getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }
    
    public int getThickness() {
        return thickness;
    }
}