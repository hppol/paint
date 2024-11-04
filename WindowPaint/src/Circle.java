import java.awt.Color;
import java.awt.Point;

public class Circle {
    private Point topLeft;
    private int width;
    private int height;
    private Color color;
    private int thickness;

    public Circle(Point topLeft, int width, int height, Color color, int thickness) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
        this.thickness = thickness;
    }

	public Point getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(Point topLeft) {
		this.topLeft = topLeft;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
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
