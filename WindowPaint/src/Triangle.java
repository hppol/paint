import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class Triangle {
	
	private Point[] points;
    private Color color;
    private int thickness;
    
    public Triangle(Point p1, Point p2, Point p3, Color color, int thickness) {
        this.points = new Point[]{p1, p2, p3};
        this.color = color;
        this.thickness = thickness;
    }
    
    public Polygon getShape() {
        int[] xPoints = {points[0].x, points[1].x, points[2].x};
        int[] yPoints = {points[0].y, points[1].y, points[2].y};
        return new Polygon(xPoints, yPoints, 3);
    }

	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
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
