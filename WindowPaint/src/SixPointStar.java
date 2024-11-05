import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class SixPointStar {
	
    private Point start;
    private Point end;
    private Color color;
    private int thickness;
    
    public SixPointStar(Point start, Point end, Color color, int thickness) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.thickness = thickness;
    }
    
    public Polygon getShape() {
        Polygon star = new Polygon();
        
        int centerX = (start.x + end.x) / 2;
        int centerY = (start.y + end.y) / 2;
        int radiusOuter = Math.min(Math.abs(end.x - start.x), Math.abs(end.y - start.y)) / 2;
        int radiusInner = radiusOuter / 2;

        for (int i = 0; i < 6; i++) {
            double outerAngle = Math.toRadians(60 * i - 30);
            double innerAngle = Math.toRadians(60 * i + 30);

            int outerX = centerX + (int) (radiusOuter * Math.cos(outerAngle));
            int outerY = centerY + (int) (radiusOuter * Math.sin(outerAngle));
            int innerX = centerX + (int) (radiusInner * Math.cos(innerAngle));
            int innerY = centerY + (int) (radiusInner * Math.sin(innerAngle));

            star.addPoint(outerX, outerY);
            star.addPoint(innerX, innerY);
        }
        return star;
    }

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
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
