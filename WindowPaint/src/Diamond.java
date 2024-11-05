import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class Diamond {

	private Point[] points;
    private Color color;
    private int thickness;

    public Diamond(Point top, Point right, Point bottom, Point left, Color color, int thickness) {
        this.points = new Point[]{top, right, bottom, left};
        this.color = color;
        this.thickness = thickness;
    }
    
    public Polygon getShape() {
        int[] xPoints = {points[0].x, points[1].x, points[2].x, points[3].x};
        int[] yPoints = {points[0].y, points[1].y, points[2].y, points[3].y};
        return new Polygon(xPoints, yPoints, 4);
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