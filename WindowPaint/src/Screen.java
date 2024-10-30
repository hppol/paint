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
    
    public Screen() {
		// TODO Auto-generated constructor stub
    	addComponentListener(this);
    	addMouseListener(this);
    	addMouseMotionListener(this);
    	setDrawMode(POINT);
    	this.currentColor = Color.BLACK; // 기본 색상
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
		
		//모든 점 그리기
//		for(Point p: pointList) {
//			bufferGraphics.setColor(currentColor);
//			bufferGraphics.fillOval(p.x, p.y, 10, 10);
//		}
		for (Dot dot : dotList) {
	        bufferGraphics.setColor(dot.getColor()); // 각 점의 색상으로 설정
	        Point p = dot.getPosition();
	        bufferGraphics.fillOval(p.x, p.y, 10, 10); // 점 그리기
	    }
		//모든 라인 그리기
		for(Line l: lineList) {
			bufferGraphics.setColor(l.getColor());
			bufferGraphics.drawLine(l.getStart().x, l.getStart().y, l.getEnd().x, l.getEnd().y);
		}
		//모든 원 그리기
		for (Circle c : circleList) {
			bufferGraphics.setColor(c.getColor());
	        Point topLeft = c.getTopLeft();
	        bufferGraphics.drawOval(topLeft.x, topLeft.y, c.getWidth(), c.getHeight());
	    }

	    // 모든 네모 그리기
	    for (Rectangle r : rectangleList) {
	    	bufferGraphics.setColor(r.getColor());
	        bufferGraphics.drawRect(r.topLeft.x, r.topLeft.y, r.width, r.height);
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
			Line line = new Line(new Point(startPoint), new Point(endPoint), currentColor);
			lineList.add(line);
			repaint();
		}
		else if (drawMode == CIRCLE) {
			
	        // 원 그리기
			int width = Math.abs(endPoint.x - startPoint.x);
		    int height = Math.abs(endPoint.y - startPoint.y);
		    int x = Math.min(startPoint.x, endPoint.x);
		    int y = Math.min(startPoint.y, endPoint.y);
		    Circle ellipse = new Circle(new Point(x, y), width, height, currentColor); // 수정된 Circle 객체 생성
		    circleList.add(ellipse); // 리스트에 추가
	        repaint();
	    } 
		
		else if (drawMode == RECTANGLE) {
	        // 네모 그리기
	        int width = Math.abs(endPoint.x - startPoint.x);
	        int height = Math.abs(endPoint.y - startPoint.y);
	        Point topLeft = new Point(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y)); // 왼쪽 위 좌표 계산
	        Rectangle rect = new Rectangle(topLeft, width, height, currentColor);
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
//	        Point p = new Point(e.getX(), e.getY());
//	        pointList.add(p);
//	        repaint();
	    	Dot dot = new Dot(new Point(e.getX(), e.getY()), currentColor);
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
	        // 원 그리기 미리보기
	        endPoint = e.getPoint();
	        int width = Math.abs(oldPoint.x - startPoint.x); // 가로 길이
	        int height = Math.abs(oldPoint.y - startPoint.y); // 세로 길이
	        int x = Math.min(startPoint.x, oldPoint.x);
	        int y = Math.min(startPoint.y, oldPoint.y);
	        bufferGraphics.drawOval(x, y, width, height); // 이전 타원 지우기	
	    } 
	    
	    else if (drawMode == RECTANGLE) {
	        // 네모 그리기 미리보기
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
