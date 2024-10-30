import java.awt.Color;
import java.awt.Point;

public class Line {
	private Point start;
	private Point end;
	private Color color;
	
	public Line(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
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
	
}
