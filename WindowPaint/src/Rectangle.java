import java.awt.Color;
import java.awt.Point;

public class Rectangle {
	Point topLeft;
    int width;
    int height;
    private Color color;

    public Rectangle(Point topLeft, int width, int height, Color color) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    
    public Color getColor() {
        return color;
    }

}
