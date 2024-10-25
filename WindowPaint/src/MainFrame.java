import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.image.ImagingOpException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;

public class MainFrame extends JFrame implements ActionListener {
	
	private final String MENU_FILE_NEW = "새로만들기(N)";
	private final String MENU_FILE_CLOSE = "끝내기(E)";
	private final String MENU_FILE_OPEN = "열기(O)";
	private final String MENU_FILE_SAVE = "저장(S)";
	private final String MENU_FILE_SAVEAS = "다른 이름으로 저장(A)";
	private final String MENU_TOOL_DOT = "점";
    private final String MENU_TOOL_LINE = "선";
    private final String MENU_TOOL_CIRCLE = "원";
    private final String MENU_TOOL_SQUARE = "네모";
	
	private JLabel statusBar = null;
	private Screen screen;
	private JButton[] colorButtons;
	private JButton selectColorButton;
	
	public MainFrame() {
		// TODO Auto-generated constructor stub
		
		//메뉴바
		setJMenuBar(createMenuBar());
		
		//준비 
		statusBar = createStatusBar();
		add(statusBar, BorderLayout.SOUTH);
		
		//툴바
		add(createToolBar(), BorderLayout.NORTH);
		
		//Screen
		screen = new Screen();
        add(screen, BorderLayout.CENTER);
		
		setSize(800,600);
		setTitle("그림판");
		setVisible(true);
		// 가운데로 설정하는 방법
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	//상단에 툴바
	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel groupDrawBox = new JPanel();
		groupDrawBox.setLayout(new GridLayout(3,4));
		ImageIcon iconClose = new ImageIcon("res/close.png");
		iconClose = new ImageIcon(
				iconClose.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		JButton [] buttons = new JButton[12];
		for(int i=0; i<12; i++) {
			buttons[i] = new JButton(iconClose);
			JButton toolButton = buttons[i];
			toolButton.setPreferredSize(new Dimension(20,20));
			groupDrawBox.add(toolButton);
		}
		
		JPanel groupSelectColor = new JPanel();
		selectColorButton = new JButton();
		selectColorButton.setBackground(new Color(255,255,255));
		selectColorButton.setPreferredSize(new Dimension(60,60));
		groupSelectColor.add(selectColorButton);
		
		JPanel groupColors = new JPanel();
		groupColors.setLayout(new GridLayout(3,5));
		colorButtons = new JButton[15];
		Color[] colors = new Color[15];
		for(int i=0; i<colors.length; i++) {
			colors[i] = new Color(0,0,0);
		}
		colors[0] = new Color(255,255,255);
		colors[1] = new Color(0,0,0);
		colors[2] = new Color(255,0,0);
		colors[3] = new Color(0,255,0);
		colors[4] = new Color(0,0,255);
		for(int i=0; i<colorButtons.length; i++) {
			colorButtons[i] = new JButton();
			colorButtons[i].setBackground(colors[i]);
			colorButtons[i].setPreferredSize(new Dimension(20,20));
			colorButtons[i].addActionListener(this);
			groupColors.add(colorButtons[i]);
		}
		
		toolBar.add(groupDrawBox);
		toolBar.add(groupSelectColor);
		toolBar.add(groupColors);
		
		ImageIcon iconDot = new ImageIcon("res/dot.png");
		ImageIcon iconLine = new ImageIcon("res/line.png");
		ImageIcon iconCircle = new ImageIcon("res/circle.png");
		ImageIcon iconSquare = new ImageIcon("res/square.png");
		
		//툴바 점
		JButton dotButton = new JButton(iconDot);
		toolBar.add(dotButton);
		
		//툴바 선
		JButton lineButton = new JButton(iconLine);
		toolBar.add(lineButton);
		
		//툴바 원
		JButton circleButton = new JButton(iconCircle);
		toolBar.add(circleButton);
		
		//툴바 네모
		JButton rectButton = new JButton(iconSquare);
		toolBar.add(rectButton);
		
		
		return toolBar;
	}
	
	//하단에 준비 레이블
	private JLabel createStatusBar() {
		JLabel statusBar = new JLabel("준비");
		//테두리 설정 방법
		statusBar.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		return statusBar;
	}
	
	// 메뉴바 생성 코드
	private JMenuBar createMenuBar() {
		
		ImageIcon iconNew = new ImageIcon("res/new.png");
		ImageIcon iconClose = new ImageIcon("res/close.png");
		ImageIcon iconOpen = new ImageIcon("res/open.png");
		ImageIcon iconSave = new ImageIcon("res/save.png");
		ImageIcon iconSaveAs = new ImageIcon("res/saveas.png");
		
		//JMenuBar 선언
		JMenuBar menuBar = new JMenuBar();
		
		//JMenu 파일
		JMenu fileMenu = new JMenu("파일(F)");
		menuBar.add(fileMenu);
		fileMenu.setMnemonic(KeyEvent.VK_F);

		//JMenuItem 새로 만들기(파일 하위메뉴)
		JMenuItem newMenuItem = new JMenuItem(MENU_FILE_NEW, iconNew);
		fileMenu.add(newMenuItem);
		newMenuItem.addActionListener(this);
		
		//shift+n 누르면 사용 가능
		newMenuItem.setMnemonic(KeyEvent.VK_N);
		//ctrl+n 누르면 사용 가능
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		//툴팁(열기 메뉴를 누르면 설명서 같은거 뜨는 거)
		newMenuItem.setToolTipText("파일을 새로 만듭니다.");
		
		//JMenuItem 열기(파일 하위메뉴)
		JMenuItem openMenuItem = new JMenuItem(MENU_FILE_OPEN, iconOpen);
		fileMenu.add(openMenuItem);
		
		openMenuItem.setMnemonic(KeyEvent.VK_O);
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		openMenuItem.setToolTipText("파일을 엽니다.");
		openMenuItem.addActionListener(this);
		
		//JMenuItem 저장(파일 하위메뉴)
		JMenuItem saveMenuItem = new JMenuItem(MENU_FILE_SAVE, iconSave);
		fileMenu.add(saveMenuItem);

		
		
		saveMenuItem.setMnemonic(KeyEvent.VK_S);
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		saveMenuItem.setToolTipText("파일을 저장합니다.");
		saveMenuItem.addActionListener(this);
		
		//JmenuItem 다른이름으로 저장(파일 하위메뉴)
		JMenuItem saveasMenuItem = new JMenuItem(MENU_FILE_SAVEAS, iconSaveAs);
		fileMenu.add(saveasMenuItem);
		
		saveasMenuItem.setMnemonic(KeyEvent.VK_A);
		saveasMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		saveasMenuItem.setToolTipText("다른 이름으로 저장합니다.");
		
		//JMenuItem 끝내기(파일 하위메뉴)
		JMenuItem closeMenuItem = new JMenuItem(MENU_FILE_CLOSE, iconClose);
		fileMenu.add(closeMenuItem);
		closeMenuItem.addActionListener(this);
		closeMenuItem.setToolTipText("프로그램을 종료 합니다.");
		
		closeMenuItem.setMnemonic(KeyEvent.VK_E);
		closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));

		//JMenu 보기
		JMenu viewMenu = new JMenu("보기(V)");
		menuBar.add(viewMenu);
		
		//JCheckBoxMenuItem 상태바 보기(보기 하위메뉴)
		JCheckBoxMenuItem showMenuItem = new JCheckBoxMenuItem("상태바 보기(S)");
		viewMenu.add(showMenuItem);
		showMenuItem.setSelected(true);
		
		//상태바 체크 여부로 statusBar 보이기 사라지기
		showMenuItem.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					statusBar.setVisible(true);
				}
				else {
					statusBar.setVisible(false);
				}
			}
		});
		
		//JMenu 도구
		JMenu toolMenu = new JMenu("도구(T)");
		menuBar.add(toolMenu);
		
		//JMenuItem 점(도구 하위메뉴)
		JMenuItem dotMenuItem = new JMenuItem(MENU_TOOL_DOT);
		toolMenu.add(dotMenuItem);
		
		//JMenuItem 선(도구 하위메뉴)
		JMenuItem lineMenuItem = new JMenuItem(MENU_TOOL_LINE);
		toolMenu.add(lineMenuItem);
		
		//JMenuItem 원(도구 하위메뉴)
		JMenuItem circleMenuItem = new JMenuItem(MENU_TOOL_CIRCLE);
		toolMenu.add(circleMenuItem);
		
		//JMenuItem 네모(도구 하위메뉴)
		JMenuItem rectMenuItem = new JMenuItem(MENU_TOOL_SQUARE);
		toolMenu.add(rectMenuItem);
		
		return menuBar;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(command.equals(MENU_FILE_NEW)) {
			
		}
		else if(command.equals(MENU_FILE_CLOSE)) {
			this.dispose();
			System.exit(0);
		}
		else if(command.equals(MENU_FILE_SAVE)) {
			FileDialog fd = new FileDialog(this, "파일저장", FileDialog.SAVE);
			fd.setVisible(true);
			if(fd.getFile() != null) {
				screen.save(fd.getDirectory()+fd.getFile());
			}
		}
		else if(command.equals(MENU_FILE_OPEN)) {
			FileDialog fd = new FileDialog(this, "파일열기", FileDialog.LOAD);
			fd.setVisible(true);
			if(fd.getFile() != null) {
				screen.open(fd.getDirectory()+fd.getFile());

		}
		}
		else {
			for(int i=0; i<colorButtons.length; i++) {
				if(colorButtons[i].equals(e.getSource())) {
					selectColorButton.setBackground(
							colorButtons[i].getBackground());
					break;
				}
			}
		}
	}

}