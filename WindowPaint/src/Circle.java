import java.awt.Color;
import java.awt.Point;

public class Circle {
	
	private Point start;
    private Point end;
    private Color color;
    private int thickness;
    
    public Circle(Point start, Point end, Color color, int thickness) {
        this.start = start;
        this.end = end;
        this.color = color;
        this.thickness = thickness;
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
    


    public int getWidth() {
        return Math.abs(end.x - start.x);
    }

    public int getHeight() {
        return Math.abs(end.y - start.y);
    }

    public Point getTopLeft() {
        return new Point(Math.min(start.x, end.x), Math.min(start.y, end.y));
    }
	
    
    
}
