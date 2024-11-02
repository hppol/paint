import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class Diamond {
    private Polygon shape;
    private Color color;
    private int thickness;

    public Diamond(Point top, Point right, Point bottom, Point left, Color color, int thickness) {
        this.color = color;
        this.thickness = thickness;
        int[] xPoints = {top.x, right.x, bottom.x, left.x};
        int[] yPoints = {top.y, right.y, bottom.y, left.y};
        this.shape = new Polygon(xPoints, yPoints, 4);
    }

    public Polygon getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getThickness() {
        return thickness;
    }
}