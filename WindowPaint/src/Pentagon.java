import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class Pentagon {
    private Polygon shape;
    private Color color;
    private int thickness;

    public Pentagon(Point[] points, Color color, int thickness) {
        this.color = color;
        this.thickness = thickness;
        int[] xPoints = new int[5];
        int[] yPoints = new int[5];
        for (int i = 0; i < 5; i++) {
            xPoints[i] = points[i].x;
            yPoints[i] = points[i].y;
        }
        this.shape = new Polygon(xPoints, yPoints, 5);
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