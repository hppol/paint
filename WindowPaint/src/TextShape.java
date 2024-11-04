import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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
}