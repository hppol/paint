import java.awt.Point;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

class TextShape {
    private String text;
    private Point position;
    private Color color;
    private Font font;

    public TextShape(String text, Point position, Color color, Font font) {
        this.text = text;
        this.position = position;
        this.color = color;
        this.font = font;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(text, position.x, position.y);
    }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
}