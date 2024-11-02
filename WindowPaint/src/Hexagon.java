import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class Hexagon {
    private Polygon shape;
    private Color color;
    private int thickness;

    public Hexagon(Point[] points, Color color, int thickness) {
        this.color = color;
        this.thickness = thickness;
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];
        for (int i = 0; i < 6; i++) {
            xPoints[i] = points[i].x;
            yPoints[i] = points[i].y;
        }
        this.shape = new Polygon(xPoints, yPoints, 6);
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