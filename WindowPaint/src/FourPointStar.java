import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class FourPointStar {
	private Point start;
    private Point end;
    private int thickness;
    private Color color;
    
    public FourPointStar(Point start, Point end, Color color, int thickness) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.thickness = thickness;
    }
    
    public Polygon getShape() {
        Polygon star = new Polygon();
        Point center = new Point((start.x + end.x) / 2, (start.y + end.y) / 2);
        int outerRadius = (int) start.distance(end) / 2;
        int innerRadius = outerRadius / 2;

        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(45 * i);
            int radius = (i % 2 == 0) ? outerRadius : innerRadius;
            int x = center.x + (int) (radius * Math.cos(angle));
            int y = center.y + (int) (radius * Math.sin(angle));
            star.addPoint(x, y);
        }
        return star;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        ((Graphics2D) g).setStroke(new BasicStroke(thickness));
        g.drawPolygon(getShape());
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

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
