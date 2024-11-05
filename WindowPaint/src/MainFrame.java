import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.FontMetrics;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;


import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame implements ActionListener {
	
	private final String MENU_FILE_NEW = "새로 만들기(N)";
	private final String MENU_FILE_OPEN = "열기(O)";
	private final String MENU_FILE_SAVE = "저장(S)";
	private final String MENU_FILE_SAVEAS = "다른 이름으로 저장(A)";
	private final String MENU_FILE_CLOSE = "끝내기(E)";
	
	private final String MENU_TOOL_DOT = "점";
    private final String MENU_TOOL_LINE = "선";
    private final String MENU_TOOL_CIRCLE = "원";
    private final String MENU_TOOL_SQUARE = "네모";
    private final String MENU_TOOL_TRIANGLE = "세모";
    private final String MENU_TOOL_DIAMOND = "마름모";
    private final String MENU_TOOL_PENTAGON = "오각형";
    private final String MENU_TOOL_HEXAGON = "육각형";
	
	private JLabel statusBar = null;
	private Screen screen;
	private JButton[] colorButtons;
	private JButton selectColorButton;
	private JButton [] toolButtons;
	private JSlider thicknessSlider;
	
	
	
	public MainFrame() {
		// TODO Auto-generated constructor stub
		
		//메뉴바
		setJMenuBar(createMenuBar());
		
		//준비(하단에)
		statusBar = createStatusBar();
		add(statusBar, BorderLayout.SOUTH);
		
		//툴바
		add(createToolBar(), BorderLayout.NORTH);
		
		//Screen
		screen = new Screen();
        add(screen, BorderLayout.CENTER);
		
		setSize(1000,800);
		setTitle("그림판");
		setVisible(true);
		// 가운데로 설정하는 방법
		setLocationRelativeTo(null);
		
		buttonSelect();
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
	}
	
	private void saveAsWithFormat(String format) {
	    FileDialog fd = new FileDialog(this, format.toUpperCase() + "로 저장", FileDialog.SAVE);
	    fd.setVisible(true);
	    if (fd.getFile() != null) {
	        String filePath = fd.getDirectory() + fd.getFile();
	        screen.saveAs(filePath, format);
	    }
	}
	//뭐로 그릴지 선택하는 버튼
	private void buttonSelect() {
        for (int i = 0; i < toolButtons.length; i++) {
            final int mode = i;
            toolButtons[i].addActionListener(e -> {
                switch (mode) {
                    case 0:
                        screen.setDrawMode(Screen.POINT);
                        break;
                        
                    case 1:
                        screen.setDrawMode(Screen.LINE);
                        break;
                        
                    case 2:
                        screen.setDrawMode(Screen.CIRCLE);
                        break;
                        
                    case 3:
                        screen.setDrawMode(Screen.RECTANGLE);
                        break;
                        
                    case 4:
                    	screen.setDrawMode(Screen.TRIANGLE);
                    	break;
                    	
                    case 5:
                    	screen.setDrawMode(Screen.DIAMOND);
                    	break;
                    	
                    case 6:
                    	screen.setDrawMode(Screen.PENTAGON);
                    	break;
                    	
                    case 7:
                    	screen.setDrawMode(Screen.HEXAGON);
                    	break;
                }
            });
        }
    }
	
	//상단에 툴바
	private JToolBar createToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel groupDrawBox = new JPanel();
		groupDrawBox.setLayout(new GridLayout(3,4));
		
		//굵기
		thicknessSlider = new JSlider(1, 10, 3); // 최소 1, 최대 15, 초기값 3
        thicknessSlider.setMajorTickSpacing(1); //눈금 간격
        thicknessSlider.setPaintTicks(false); //눈금 표시
        thicknessSlider.setPaintLabels(true); //눈금 숫자 표시
        
        thicknessSlider.addChangeListener(e -> {
            int thickness = thicknessSlider.getValue();
            screen.setLineThickness(thickness);
        });
		
        //도구들의 아이콘
		ImageIcon[] icons = {
			    new ImageIcon("res/dot.png"),
			    new ImageIcon("res/line.png"),
			    new ImageIcon("res/circle.png"),
			    new ImageIcon("res/square.png"),
			    new ImageIcon("res/triangle.png"),
			    new ImageIcon("res/diamond.png"),
			    new ImageIcon("res/pentagon.png"),
			    new ImageIcon("res/hexagon.png")
		};
		
		for (int i = 0; i < icons.length; i++) {
		    icons[i] = new ImageIcon(icons[i].getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		}
		
		toolButtons = new JButton[12];
		// 툴버튼
		 for (int i = 0; i < 8; i++) {
			 toolButtons[i] = new JButton(icons[i]);
			 toolButtons[i].setPreferredSize(new Dimension(20, 20));
			 toolButtons[i].addActionListener(this);

		        groupDrawBox.add(toolButtons[i]);
		    }
		
		//빈버튼 만들기
		for (int i = 8; i < toolButtons.length; i++) {
			toolButtons[i] = new JButton();
			toolButtons[i].setPreferredSize(new Dimension(20, 20));
		    groupDrawBox.add(toolButtons[i]);
		}
		
		toolButtons[0].setToolTipText("점");
		toolButtons[1].setToolTipText("선");
		toolButtons[2].setToolTipText("원");
		toolButtons[3].setToolTipText("네모");
		toolButtons[4].setToolTipText("세모");
		toolButtons[5].setToolTipText("마름모");
		toolButtons[6].setToolTipText("오각형");
		toolButtons[7].setToolTipText("육각형");
		
		
		
		//선택한 색상 보이게 하는 패널
		JPanel groupSelectColor = new JPanel();
		selectColorButton = new JButton();
		selectColorButton.setBackground(new Color(0,0,0));
		selectColorButton.setPreferredSize(new Dimension(30,30));
		groupSelectColor.add(selectColorButton);
		
		//색상 20개
		JPanel groupColors = new JPanel();
		groupColors.setLayout(new GridLayout(2,10));
		colorButtons = new JButton[20];
		Color[] colors = new Color[20];

		colors[0] = new Color(0, 0, 0); // 검정색
		colors[1] = new Color(128, 128, 128); // 회색
		colors[2] = new Color(155, 17, 30); // 진한 빨강색
		colors[3] = new Color(255, 0, 0); // 빨강색
		colors[4] = new Color(255, 165, 0); // 주황색
		colors[5] = new Color(255, 255, 0); // 노랑색
		colors[6] = new Color(0, 128, 0); // 초록색
		colors[7] = new Color(131, 220, 183); // 옥색
		colors[8] = new Color(0, 0, 128); // 남색
		colors[9] = new Color(108, 1, 64); // 자주색
		colors[10] = new Color(255, 255, 255); // 흰색
		colors[11] = new Color(211, 211, 211); // 연한 회색
		colors[12] = new Color(125, 79, 69); // 밤색
		colors[13] = new Color(255, 169, 181); // 다홍색
		colors[14] = new Color(255, 215, 0); // 황금색
		colors[15] = new Color(229, 211, 77); // 연한 노랑
		colors[16] = new Color(191, 255, 0); // 라임 색
		colors[17] = new Color(207, 255, 229); // 연한 옥색
		colors[18] = new Color(57, 95, 114); // 청회색
		colors[19] = new Color(169, 123, 245); // 연한 보라색
		
		String[] colorNames = {
			    "검정", "회색", "진한 빨강",
			    "빨강", "주황", "노랑",
			    "녹색", "옥색", "남색",
			    "자주", "흰색", "연한 회색",
			    "밤색", "다홍", "황금색",
			    "연한 노랑", "라임", "연한 옥색",
			    "청회색", "연한 보라"
			};
		
		
		
		//선택된 색상을 표시
		for(int i=0; i<colorButtons.length; i++) {
			colorButtons[i] = new JButton();
			colorButtons[i].setBackground(colors[i]);
			colorButtons[i].setPreferredSize(new Dimension(20,20));
			colorButtons[i].setToolTipText(colorNames[i]);
			
			int index = i;
            colorButtons[i].addActionListener(e -> {
                screen.setCurrentColor(colors[index]);
                selectColorButton.setBackground(colors[index]);
                
                for (int j = 0; j < colorButtons.length; j++) {
                    if (j == index) {
                        colorButtons[j].setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));
                    } else {
                        colorButtons[j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                    }
                }
                
            });
            
            
            
            
			groupColors.add(colorButtons[i]);
		}
		
		//텍스트
		ImageIcon textIcon = new ImageIcon("res/text.png");

		JButton textButton = new JButton(textIcon);
		textButton.setToolTipText("텍스트");
	    textButton.addActionListener(e -> screen.setDrawMode(Screen.TEXT));
	    
	    //실행취소, 다시실행
	    ImageIcon undoIcon = new ImageIcon("res/undo.png");
	    ImageIcon redoIcon = new ImageIcon("res/redo.png");
	    
	    JButton undoButton = new JButton(undoIcon);
	    undoButton.setToolTipText("실행취소");
	    undoButton.addActionListener(e -> screen.undo());
	    toolBar.add(undoButton);
	    
	    undoButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control Z"), "undoAction");
	    undoButton.getActionMap().put("undoAction", new AbstractAction() {
	    	
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            screen.undo();
	        }
	    });


	    JButton redoButton = new JButton(redoIcon);
	    redoButton.setToolTipText("다시실행");
	    redoButton.addActionListener(e -> screen.redo());
	    toolBar.add(redoButton);
	    
	    redoButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control Y"), "redoAction");
	    redoButton.getActionMap().put("redoAction", new AbstractAction() {
	    	
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            screen.redo();
	        }
	    });
	    
	    ImageIcon eraserIcon = new ImageIcon("res/eraser.png");
	    JButton eraserButton = new JButton(eraserIcon);
	    eraserButton.setToolTipText("지우개");
	    eraserButton.addActionListener(e -> screen.setDrawMode(Screen.ERASER));

	    

        toolBar.add(textButton);
	    toolBar.add(eraserButton);
        
		toolBar.add(groupDrawBox);
		
		toolBar.add(groupSelectColor);
		toolBar.add(groupColors);
		
		toolBar.add(new JLabel("선 굵기:"));
        toolBar.add(thicknessSlider);

		
		
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
		ImageIcon iconOpen = new ImageIcon("res/open.png");
		ImageIcon iconSave = new ImageIcon("res/save.png");
		ImageIcon iconSaveAs = new ImageIcon("res/saveas.png");
		ImageIcon iconClose = new ImageIcon("res/close.png");
		
		//JMenuBar
		JMenuBar menuBar = new JMenuBar();
		
		//JMenu 파일
		JMenu fileMenu = new JMenu("파일(F)");
		menuBar.add(fileMenu);
		fileMenu.setMnemonic(KeyEvent.VK_F);

		//JMenuItem 새로 만들기(파일 하위메뉴)
		JMenuItem newMenuItem = new JMenuItem(MENU_FILE_NEW, iconNew);
		fileMenu.add(newMenuItem);
		newMenuItem.addActionListener(this);
		
		
		newMenuItem.setMnemonic(KeyEvent.VK_N); //shift+n 누르면 사용 가능
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK)); //ctrl+n 누르면 사용 가능
		newMenuItem.setToolTipText("파일을 새로 만듭니다."); //툴팁(열기 메뉴를 누르면 설명서 같은거 뜨는 거)
		
		//새로 만들기 누를 때 저장 여부창
		newMenuItem.addActionListener(e -> {
		    // 저장 여부를 묻는 확인 창 띄우기
		    int option = JOptionPane.showOptionDialog(
		        this,
		        "작업을 저장하시겠습니까?",
		        "새로 만들기",
		        JOptionPane.YES_NO_CANCEL_OPTION,
		        JOptionPane.QUESTION_MESSAGE,
		        null,
		        new Object[]{"저장", "저장하지 않음", "취소"},
		        "저장"
		    );

		    if (option == JOptionPane.YES_OPTION) {
		        // 파일 저장
		        FileDialog fd = new FileDialog(this, "파일 저장", FileDialog.SAVE);
		        fd.setVisible(true);
		        if (fd.getFile() != null) {
		            String savePath = fd.getDirectory() + fd.getFile();
		            screen.save(savePath);
		        }
		        screen.clear();
		    } else if (option == JOptionPane.NO_OPTION) {
		        // 저장하지 않고 새로 만들기
		        screen.clear();
		    }
		});
		
		
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
		
		
		//Jmenu 다른이름으로 저장(파일 하위메뉴)
		JMenu saveAsMenu = new JMenu(MENU_FILE_SAVEAS);
		saveAsMenu.setIcon(new ImageIcon("res/saveas.png"));
		fileMenu.add(saveAsMenu);
		
		saveAsMenu.setMnemonic(KeyEvent.VK_A);
//		saveAsMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK)); menu에서는 사용못함 item에서만 사용가능
		saveAsMenu.setToolTipText("다른 이름으로 저장합니다.");
		saveAsMenu.addActionListener(this);

		// 다른이름으로 저장 하위 메뉴들
		JMenuItem saveAsPng = new JMenuItem("PNG로 저장");
		saveAsPng.addActionListener(e -> saveAsWithFormat("png"));
		saveAsPng.setToolTipText("PNG로 저장합니다.");
		saveAsMenu.add(saveAsPng);

		JMenuItem saveAsJpeg = new JMenuItem("JPEG로 저장");
		saveAsJpeg.addActionListener(e -> saveAsWithFormat("jpg"));
		saveAsJpeg.setToolTipText("JPEG로 저장합니다.");
		saveAsMenu.add(saveAsJpeg);

		JMenuItem saveAsBmp = new JMenuItem("BMP로 저장");
		saveAsBmp.addActionListener(e -> saveAsWithFormat("bmp"));
		saveAsBmp.setToolTipText("BMP로 저장합니다.");
		saveAsMenu.add(saveAsBmp);

		JMenuItem saveAsGif = new JMenuItem("GIF로 저장");
		saveAsGif.addActionListener(e -> saveAsWithFormat("gif"));
		saveAsGif.setToolTipText("GIF로 저장합니다.");
		saveAsMenu.add(saveAsGif);
		
		
		
		//JMenuItem 끝내기(파일 하위메뉴)
		JMenuItem closeMenuItem = new JMenuItem(MENU_FILE_CLOSE, iconClose);
		fileMenu.add(closeMenuItem);
		
		
		closeMenuItem.setMnemonic(KeyEvent.VK_E);
		closeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		closeMenuItem.setToolTipText("프로그램을 종료 합니다.");
		closeMenuItem.addActionListener(this);
		

		
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
		dotMenuItem.addActionListener(e -> toolButtons[0].doClick());
		
		//JMenuItem 선(도구 하위메뉴)
		JMenuItem lineMenuItem = new JMenuItem(MENU_TOOL_LINE);
		toolMenu.add(lineMenuItem);
		lineMenuItem.addActionListener(e -> toolButtons[1].doClick());
		
		//JMenuItem 원(도구 하위메뉴)
		JMenuItem circleMenuItem = new JMenuItem(MENU_TOOL_CIRCLE);
		toolMenu.add(circleMenuItem);
		circleMenuItem.addActionListener(e -> toolButtons[2].doClick());
		
		//JMenuItem 네모(도구 하위메뉴)
		JMenuItem rectMenuItem = new JMenuItem(MENU_TOOL_SQUARE);
		toolMenu.add(rectMenuItem);
		rectMenuItem.addActionListener(e -> toolButtons[3].doClick());
		
		//JMenuItem 세모(도구 하위메뉴)
		JMenuItem triMenuItem = new JMenuItem(MENU_TOOL_TRIANGLE);
		toolMenu.add(triMenuItem);
		triMenuItem.addActionListener(e -> toolButtons[4].doClick());
		
		//JMenuItem 마름모(도구 하위메뉴)
		JMenuItem diaMenuItem = new JMenuItem(MENU_TOOL_DIAMOND);
		toolMenu.add(diaMenuItem);
		diaMenuItem.addActionListener(e -> toolButtons[5].doClick());
		
		//JMenuItem 오각형(도구 하위메뉴)
		JMenuItem pentMenuItem = new JMenuItem(MENU_TOOL_PENTAGON);
		toolMenu.add(pentMenuItem);
		pentMenuItem.addActionListener(e -> toolButtons[6].doClick());
		
		//JMenuItem 육각형(도구 하위메뉴)
		JMenuItem hexMenuItem = new JMenuItem(MENU_TOOL_HEXAGON);
		toolMenu.add(hexMenuItem);
		hexMenuItem.addActionListener(e -> toolButtons[7].doClick());
		
				
		
		return menuBar;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(command.equals(MENU_FILE_NEW)) {
			
			
		}
		else if(command.equals(MENU_FILE_OPEN)) {
			FileDialog fd = new FileDialog(this, "파일열기", FileDialog.LOAD);
			fd.setVisible(true);
			if(fd.getFile() != null) {
				screen.open(fd.getDirectory()+fd.getFile());

		}
		}
		else if(command.equals(MENU_FILE_SAVE)) {
			FileDialog fd = new FileDialog(this, "파일저장", FileDialog.SAVE);
			fd.setVisible(true);
			if(fd.getFile() != null) {
				screen.save(fd.getDirectory()+fd.getFile());
			}
		}
		else if(command.equals(MENU_FILE_SAVEAS)) {
			
		}
		
		else if(command.equals(MENU_FILE_CLOSE)) {
			this.dispose();
			System.exit(0);
		}

		
		else {
			for(int i=0; i<colorButtons.length; i++) {
				//이거로 선택된 색을 옆에 있는 색으로 변경
				if(colorButtons[i].equals(e.getSource())) {
					selectColorButton.setBackground(
							colorButtons[i].getBackground());
					break;
				}
			}
		}
	}
	}

