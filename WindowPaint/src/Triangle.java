import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class Triangle {
    private Polygon shape;
    private Color color;
    private int thickness;

    public Triangle(Point p1, Point p2, Point p3, Color color, int thickness) {
        this.color = color;
        this.thickness = thickness;
        int[] xPoints = {p1.x, p2.x, p3.x};
        int[] yPoints = {p1.y, p2.y, p3.y};
        this.shape = new Polygon(xPoints, yPoints, 3);
    }

	public Polygon getShape() {
		return shape;
	}

	public void setShape(Polygon shape) {
		this.shape = shape;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
    
    
}
