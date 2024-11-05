import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class Pentagon {
	
	private Point[] points;
    private Color color;
    private int thickness;
    
    public Pentagon(Point[] points, Color color, int thickness) {
        this.points = points;
        this.color = color;
        this.thickness = thickness;
    }
    
    public Polygon getShape() {
        int[] xPoints = {points[0].x, points[1].x, points[2].x, points[3].x, points[4].x};
        int[] yPoints = {points[0].y, points[1].y, points[2].y, points[3].y, points[4].y};
        return new Polygon(xPoints, yPoints, 5);
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