import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
import java.util.LinkedList;

public class Screen extends Canvas implements ComponentListener, MouseListener, MouseMotionListener {
    private Graphics bufferGraphics;
	private Image offscreen;
    private Dimension dim;
    private LinkedList<Point> pointList = new LinkedList<Point>();
    private LinkedList<Line> lineList = new LinkedList<Line>();
    private int drawMode;
    public static final int SELECT = 0;
    public static final int POINT = 1;
    public static final int LINE = 2;
    private Point startPoint = new Point();
    private Point endPoint = new Point();
    private Point oldPoint = new Point();
    
    
    public Screen() {
		// TODO Auto-generated constructor stub
    	addComponentListener(this);
    	addMouseListener(this);
    	addMouseMotionListener(this);
    	setDrawMode(LINE);
	}
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
		
		//draw figure
		for(Point p: pointList) {
			bufferGraphics.fillOval(p.x, p.y, 10, 10);
		}
		
		for(Line l: lineList) {
			bufferGraphics.drawLine(l.getStart().x, l.getStart().y, l.getEnd().x, l.getEnd().y);
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
		if(drawMode == LINE) {
			startPoint.setLocation(e.getX(),e.getY());
			oldPoint = startPoint;
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(drawMode == LINE) {
			endPoint.setLocation(e.getX(),e.getY());
//			Graphics g = getGraphics();
//			g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
			Line line = new Line();
			line.setStart(new Point(startPoint));
			line.setEnd(new Point(endPoint));
			lineList.add(line);
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
		if(this.drawMode == this.SELECT) {

		}
		else if(this.drawMode == POINT) {
			Point p = new Point(e.getX(),e.getY());
			pointList.add(p);
			repaint();
		}
		else if(this.drawMode == LINE) {
			Graphics g = getGraphics();
			this.endPoint = e.getPoint();
			g.setXORMode(Color.white);
			g.drawLine(startPoint.x, startPoint.y, oldPoint.x, oldPoint.y);
			g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
			this.oldPoint = endPoint;
			g.setColor(Color.black);	
			
		}



		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
    

}
