import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class FivePointStar {
	
	 private Point startPoint;
	    private Point endPoint;
	    private Color color;
	    private int thickness;
	    
	    public FivePointStar(Point startPoint, Point endPoint, Color color, int thickness) {
	        this.startPoint = startPoint;
	        this.endPoint = endPoint;
	        this.color = color;
	        this.thickness = thickness;
	    }
	    
	    public Polygon getShape() {
	        int centerX = (startPoint.x + endPoint.x) / 2;
	        int centerY = (startPoint.y + endPoint.y) / 2;
	        int radius = Math.min(Math.abs(endPoint.x - startPoint.x), Math.abs(endPoint.y - startPoint.y)) / 2;

	        Polygon star = new Polygon();
	        for (int i = 0; i < 5; i++) {
	            double angle = Math.toRadians(72 * i - 90);
	            double outerX = centerX + radius * Math.cos(angle);
	            double outerY = centerY + radius * Math.sin(angle);
	            star.addPoint((int) outerX, (int) outerY);

	            angle = Math.toRadians(72 * i + 36 - 90);
	            double innerX = centerX + (radius / 2.5) * Math.cos(angle);
	            double innerY = centerY + (radius / 2.5) * Math.sin(angle);
	            star.addPoint((int) innerX, (int) innerY);
	        }
	        return star;
	    }

		public Point getStartPoint() {
			return startPoint;
		}

		public void setStartPoint(Point startPoint) {
			this.startPoint = startPoint;
		}

		public Point getEndPoint() {
			return endPoint;
		}

		public void setEndPoint(Point endPoint) {
			this.endPoint = endPoint;
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
