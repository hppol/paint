import java.awt.Color;
import java.awt.Point;

public class Dot {
    private Point position;
    private Color color;
    private int thickness;
    
    public Dot(Point position, Color color, int thickness) {
    	this.position = position;
    	this.color = color;
    	this.thickness = thickness;
  }
    
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point position) {
		this.position = position;
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