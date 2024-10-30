import java.awt.Color;
import java.awt.Point;

public class Circle {
    private Point topLeft;
    private int width;
    private int height;
    private Color color;
    private int thickness;

    public Circle(Point topLeft, int width, int height, Color color, int thickness) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.thickness = thickness;
    }
    
    public Color getColor() {
        return color;
    }
    
    public Point getTopLeft() {
        return topLeft;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public int getThickness() {
        return thickness;
    }
}
