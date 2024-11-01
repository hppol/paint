import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Screen extends Canvas implements ComponentListener, MouseListener, MouseMotionListener {
    private Graphics bufferGraphics;
	private Image offscreen;
    private Dimension dim;
    private LinkedList<Point> pointList = new LinkedList<Point>();
    private List<Dot> dotList = new ArrayList<>();
    private LinkedList<Line> lineList = new LinkedList<Line>();
    private LinkedList<Circle> circleList = new LinkedList<Circle>();
    private LinkedList<Rectangle> rectangleList = new LinkedList<Rectangle>();
    private int drawMode;
    public static final int SELECT = 0;
    public static final int POINT = 1;
    public static final int LINE = 2;
    public static final int CIRCLE = 3;
    public static final int RECTANGLE = 4;
    private Point startPoint = new Point();
    private Point endPoint = new Point();
    private Point oldPoint = new Point();
    private Color currentColor = Color.BLACK;
    private int lineThickness = 3;
    
    public Screen() {
		// TODO Auto-generated constructor stub
    	addComponentListener(this);
    	addMouseListener(this);
    	addMouseMotionListener(this);
    	setDrawMode(POINT);
    	this.currentColor = Color.BLACK; // 기본 색상
	}
    
    //굵기 선택
    public void setLineThickness(int thickness) {
        this.lineThickness = thickness;
        repaint(); // 굵기 변경 시 화면 갱신
    }
    
    //색 선택
    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }
    
    //열기
    public void open(String filename) {
    	File file = new File(filename);
    	FileInputStream fis;
    	try {
    		pointList.clear();
			fis = new FileInputStream(file);
	    	DataInputStream dis = new DataInputStream(fis);
	    	int size = dis.readInt();
	    	for(int i=0;i<size; i++) {
	    		int x = dis.readInt();
	    		int y = dis.readInt();
	    		}
	    	repaint();
		}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
    	
    }
    
    //저장
    public void save(String filename) {
    	File file = new File(filename);
    	FileOutputStream fos;
    	try {
			fos = new FileOutputStream(file);
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeInt(pointList.size());
			for(Point p: pointList) {
				dos.writeInt(p.x);
				dos.writeInt(p.y);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //그리기모드
    public void setDrawMode(int mode) {
    	this.drawMode = mode;
    	
    }
    
    public void initBuffered() {
        dim = getSize();
        setBackground(Color.white);
        offscreen = createImage(dim.width, dim.height);
        bufferGraphics = offscreen.getGraphics();
    }

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		bufferGraphics.clearRect(0, 0, dim.width, dim.height);
		bufferGraphics.setColor(currentColor);
		
		Graphics2D g2 = (Graphics2D) bufferGraphics;
		
		g2.setStroke(new BasicStroke(lineThickness));
		
		//모든 점 그리기
		for (Dot dot : dotList) {
	        bufferGraphics.setColor(dot.getColor());
	        int diameter = (int)(dot.getThickness() * 2); 
	        Point p = dot.getPosition();
	        bufferGraphics.fillOval(p.x - diameter / 2, p.y - diameter / 2, diameter, diameter);
	    }
		//모든 라인 그리기
		for(Line l: lineList) {
			g2.setStroke(new BasicStroke(l.getThickness()));
			bufferGraphics.setColor(l.getColor());
			bufferGraphics.drawLine(l.getStart().x, l.getStart().y, l.getEnd().x, l.getEnd().y);
		}
		//모든 원 그리기
		for (Circle c : circleList) {
			g2.setStroke(new BasicStroke(c.getThickness()));
			bufferGraphics.setColor(c.getColor());
	        Point topLeft = c.getTopLeft();
	        bufferGraphics.drawOval(topLeft.x, topLeft.y, c.getWidth(), c.getHeight());
	    }

	    // 모든 네모 그리기
	    for (Rectangle r : rectangleList) {
	    	g2.setStroke(new BasicStroke(r.getThickness()));
	    	bufferGraphics.setColor(r.getColor());
	    	Point topLeft = r.getTopLeft();
	    	bufferGraphics.drawRect(r.getTopLeft().x, r.getTopLeft().y, r.getWidth(), r.getHeight());
	    }

	    g.drawImage(offscreen, 0, 0, this);
	}

	@Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		paint(g);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		initBuffered();
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		startPoint.setLocation(e.getX(),e.getY());
		if(drawMode == LINE) {
			oldPoint = startPoint;
			
		}
		else if (drawMode == CIRCLE) {
	    } 
		else if (drawMode == RECTANGLE) {
	    }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		endPoint.setLocation(e.getX(),e.getY());
		if(drawMode == LINE) {
			Line line = new Line(new Point(startPoint), new Point(endPoint), currentColor, lineThickness);
			lineList.add(line);
			repaint();
		}
		else if (drawMode == CIRCLE) {
			
	        // 원 그리기
			int width = Math.abs(endPoint.x - startPoint.x);
		    int height = Math.abs(endPoint.y - startPoint.y);
		    int x = Math.min(startPoint.x, endPoint.x);
		    int y = Math.min(startPoint.y, endPoint.y);
		    Circle ellipse = new Circle(new Point(x, y), width, height, currentColor, lineThickness);
		    circleList.add(ellipse);
	        repaint();
	    } 
		
		else if (drawMode == RECTANGLE) {
	        // 네모 그리기
	        int width = Math.abs(endPoint.x - startPoint.x);
	        int height = Math.abs(endPoint.y - startPoint.y);
	        Point topLeft = new Point(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y));
	        Rectangle rect = new Rectangle(topLeft, width, height, currentColor, lineThickness);
	        rectangleList.add(rect);
	        repaint();
	    }
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Graphics g = getGraphics();
		g.setXORMode(Color.white);

	    if (drawMode == POINT) {
	    	Dot dot = new Dot(new Point(e.getX(), e.getY()), currentColor, lineThickness);
	        dotList.add(dot);
	        repaint();
	    } 
	    else if(drawMode == LINE) {
			endPoint = e.getPoint();
			g.setXORMode(Color.white);
			g.drawLine(startPoint.x, startPoint.y, oldPoint.x, oldPoint.y);
			g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
			this.oldPoint = endPoint;
			
		}
	    else if (drawMode == CIRCLE) {
	        endPoint = e.getPoint();
	        int width = Math.abs(oldPoint.x - startPoint.x);
	        int height = Math.abs(oldPoint.y - startPoint.y);
	        int x = Math.min(startPoint.x, oldPoint.x);
	        int y = Math.min(startPoint.y, oldPoint.y);
	        bufferGraphics.drawOval(x, y, width, height);	
	    } 
	    
	    else if (drawMode == RECTANGLE) {
	        endPoint = e.getPoint();
	        int width = Math.abs(endPoint.x - startPoint.x);
	        int height = Math.abs(endPoint.y - startPoint.y);
	        int x = Math.min(startPoint.x, endPoint.x);
	        int y = Math.min(startPoint.y, endPoint.y);
	    }
	    g.setPaintMode();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getDrawMode() {
		return drawMode;
	}
    

}
