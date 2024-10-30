import java.awt.Color;
import java.awt.Point;

public class Circle {
    Point topLeft;
    int width;
    int height;
    private Color color;

    public Circle(Point topLeft, int width, int height, Color color) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
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
}
