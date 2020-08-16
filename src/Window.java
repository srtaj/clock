import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Button;
import javax.swing.JSeparator;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent; 
 

public class Window {

	private JFrame frame;
	private Button button,button_1,button_2;
	private JButton button_3,button_4,button_5;
	private int select;
	private JProgressBar progressBar;
	private Label label,label_1,stopwatchLabel,millisecondLabel;
	private JTextPane errorLabel;
	private TextArea textArea, textArea_1 ;
	private Times times;
	private Stopwatch stopwatch;
	private Timer timer;
	private Thread thread,thread1,stopwatchThread,timerThread;
	private Date date;
	private TextField textField;
	private int laps;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void setTimerSeconds() {
		String temp=textArea_1.getText();
		int seconds=Integer.parseInt(""+temp.charAt(6)+temp.charAt(7));
		int minute=Integer.parseInt(""+temp.charAt(3)+temp.charAt(4));
		int hour=Integer.parseInt(""+temp.charAt(0)+temp.charAt(1));
		seconds=seconds+minute*60+hour*3600;
		timer.setTime(seconds);
	}
	
	private void checkTime() {
		String temp=textArea_1.getText();
		int seconds=Integer.parseInt(""+temp.charAt(6)+temp.charAt(7));
		int minute=Integer.parseInt(""+temp.charAt(3)+temp.charAt(4));
		

		if(seconds>60||minute>60) {
			errorLabel.setText("You have entered an invalid time, the time will be automatically adjusted to a valid format.");
			errorLabel.setVisible(true);
		}
		else {
			errorLabel.setText("");
			errorLabel.setVisible(false);
		}
		
	}
	
	private void timerLabelBackspace() {
		String temp=textArea_1.getText();
		textArea_1.setText("0"+temp.charAt(0)+":"+temp.charAt(1)+temp.charAt(3)+":"+temp.charAt(4)+temp.charAt(6));	
		
		checkTime();
	}
	private void timerLabelAction(int key) {
		String temp=textArea_1.getText();
		if(temp.charAt(0)=='0') {
			textArea_1.setText(""+temp.charAt(1)+temp.charAt(3)+":"+temp.charAt(4)+temp.charAt(6)+":"+temp.charAt(7)+key);
		}
		checkTime();
		
	}
	
	
	public void clear() {
		if(thread!=null) {
			times.bExit=true;
			thread=null;
		}
		if(thread1!=null) {
			date.bExit=true;
			thread1=null;
		}
		errorLabel.setText("");
		button_4.setIcon(new ImageIcon(getClass().getResource("resources/Play.png")));
		button_3.setVisible(false);
		button_4.setVisible(false);
		button_5.setVisible(false);
		textArea_1.setVisible(false);
		errorLabel.setVisible(false);
		stopwatchLabel.setVisible(false);
		stopwatchLabel.setAlignment(Label.RIGHT);
		millisecondLabel.setVisible(false);
		frame.getContentPane().remove(progressBar);
		frame.getContentPane().remove(label);
		frame.getContentPane().remove(label_1);
		frame.getContentPane().remove(textArea);
		frame.getContentPane().remove(textArea_1);
		frame.getContentPane().remove(textField);
		
	}


	void actionOnClock() {
		if(select==0) {
			return;
		}
		clear();
		
		
		frame.getContentPane().add(label);
		frame.getContentPane().add(label_1);
		select=0;
		times.bExit=false;
		date.bExit=false;
		thread=new Thread(times);
		thread1=new Thread(date);
		thread1.start();
		thread.start();
	}
	
	void actionOnStopwatch() {
		if(select==1) {
			return;
		}
		stopwatch.reset();
		clear();
		laps=1;
		stopwatchLabel.setFont(new Font("Sitka Display", Font.PLAIN, 50));
		button_3.setVisible(true);
		button_4.setVisible(true);
		button_5.setVisible(true);
		textArea.setVisible(false);
		frame.getContentPane().add(textArea);
		
		stopwatchLabel.setText("00:00");
		stopwatchLabel.setVisible(true);
		millisecondLabel.setText(".00");
		millisecondLabel.setVisible(true);
		textArea.setText("Laps-");
		select=1;
		
		
	}

	void actionOnTimer() {
		if(select==2) {
			return;
		}
		clear();
		select=2;
		stopwatchLabel.setAlignment(Label.CENTER);
		stopwatchLabel.setFont(new Font("Sitka Display", Font.PLAIN, 45));
		textArea_1.setText("00:00:00");
		textArea_1.setVisible(true);
		frame.getContentPane().add(textArea_1);
		button_3.setVisible(true);
		button_4.setVisible(true);
		frame.getContentPane().add(progressBar);
		progressBar.setValue(0);
	}

	/**
	 * Create the application.
	 */
	public Window() {
		select=0;
		laps=0;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setForeground(Color.DARK_GRAY);
		frame.setBackground(Color.DARK_GRAY);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(360, 150, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new TextArea("",0,0,3);
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Impact", Font.PLAIN, 15));
		textArea.setBounds(458, 76, 112, 144);
		textArea.setEditable(false);
		
		
		stopwatchLabel = new Label("00:00");
		stopwatchLabel.setFont(new Font("Sitka Display", Font.PLAIN, 50));
		stopwatchLabel.setAlignment(Label.RIGHT);
		stopwatchLabel.setForeground(new Color(255, 255, 255));
		stopwatchLabel.setBounds(10, 120, 460, 100);
		stopwatchLabel.setVisible(false);
		frame.getContentPane().add(stopwatchLabel);
		
		millisecondLabel = new Label("00:00");
		millisecondLabel.setFont(new Font("Sitka Display", Font.PLAIN, 50));
		millisecondLabel.setAlignment(Label.LEFT);
		millisecondLabel.setForeground(new Color(255, 255, 255));
		millisecondLabel.setBounds(470, 120, 100, 100);
		millisecondLabel.setVisible(false);
		
		errorLabel = new JTextPane();
		errorLabel.setFont(new Font("Sitka Display", Font.PLAIN, 12));
		errorLabel.setBackground(new Color(0,0,0));
		errorLabel.setForeground(new Color(255,255,255));
		errorLabel.setBounds(50, 120, 200, 50);
		errorLabel.setVisible(false);
		frame.getContentPane().add(errorLabel);
		
		textArea_1=new TextArea("",0,0,3);
		textArea_1.setEditable(false);
		textArea_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				int key=event.getKeyCode();
				if(key>=KeyEvent.VK_0&&key<=KeyEvent.VK_9) {
					key-=KeyEvent.VK_0;
					timerLabelAction(key);
				}
				else if(key>=KeyEvent.VK_NUMPAD0 &&key<=KeyEvent.VK_NUMPAD9) {
					key-=KeyEvent.VK_NUMPAD0;
					timerLabelAction(key);
				}
				else if(key==KeyEvent.VK_BACK_SPACE) {
					timerLabelBackspace();
				}
			}
		});
		textArea_1.setFont(new Font("Sitka Display", Font.PLAIN, 45));
		textArea_1.setForeground(new Color(255, 255, 255));
		textArea_1.setBackground(new Color(0,0,0));
		textArea_1.setBounds(403, 100, 171, 120);
		frame.getContentPane().add(millisecondLabel);
		
		
		button = new Button("Clock");
		button.setFont(new Font("Impact", Font.BOLD, 16));
		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
		button.setBounds(10, 10, 181, 25);
		frame.getContentPane().add(button);
		
		button_1 = new Button("Stopwatch");
		
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Impact", Font.BOLD, 16));
		button_1.setBackground(Color.BLACK);
		button_1.setBounds(197, 10, 198, 25);
		frame.getContentPane().add(button_1);
		
		button_2 = new Button("Timer");
		
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("Impact", Font.BOLD, 16));
		button_2.setBackground(Color.BLACK);
		button_2.setBounds(401, 10, 169, 25);
		frame.getContentPane().add(button_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 45, 584, 25);
		frame.getContentPane().add(separator);
		
		label = new Label("00:00");
		label.setFont(new Font("Sitka Display", Font.PLAIN, 50));
		label.setAlignment(Label.CENTER);
		label.setForeground(new Color(255, 255, 255));
		label.setBounds(10, 120, 560, 100);
		
		
		
		frame.getContentPane().add(label);
		
		times=new Times(label);
		
		label_1 = new Label();
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Sitka Display", Font.PLAIN, 40));
		label_1.setAlignment(Label.CENTER);
		label_1.setBounds(17, 215, 560, 66);
		frame.getContentPane().add(label_1);
		label_1.setText("Date : "+java.time.LocalDate.now().toString());

		
		date=new Date(label_1);
		
		textField = new TextField();
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Ailerons", Font.BOLD, 50));
		textField.setText("00:00");
		textField.setBackground(Color.BLACK);
		textField.setBounds(224, 139, 140, 66);
		
		thread=new Thread(times);
		thread.start();
		thread1=new Thread(date);
		thread1.start();
		button_3 = new JButton();
		button_3.setToolTipText("Reset");
		button_3.setBackground(Color.BLACK);
		button_3.setSelectedIcon(null);
		button_3.setBounds(195, 235, 50, 50);
		button_3.setIcon(new ImageIcon(getClass().getResource("resources/Reset.png")));
		button_3.setVisible(false);
		frame.getContentPane().add(button_3);
		
		button_4 = new JButton();
		button_4.setToolTipText("Play");
		button_4.setIcon(new ImageIcon(getClass().getResource("resources/Play.png")));
		button_4.setBackground(Color.BLACK);
		button_4.setBounds(271, 235, 50, 50);
		button_4.setVisible(false);
		frame.getContentPane().add(button_4);
		
		button_5 = new JButton();
		button_5.setToolTipText("Lap");
		button_5.setIcon(new ImageIcon(getClass().getResource("resources/Lap.png")));
		button_5.setBackground(Color.BLACK);
		button_5.setBounds(345, 235, 50, 50);
		button_5.setVisible(false);
		frame.getContentPane().add(button_5);
		
		
//		frame.getContentPane().add(button_3);
	//	frame.getContentPane().add(button_4);
//		frame.getContentPane().add(button_5);
		stopwatch=new Stopwatch(stopwatchLabel, millisecondLabel, button_4);
		stopwatchThread=new Thread(stopwatch);
		stopwatchThread.start();
		
		progressBar = new JProgressBar();
		progressBar.setBounds(225, 246, 146, 14);
		
		timer =new Timer(stopwatchLabel,button_4,progressBar);
		timerThread=new Thread(timer);
		timerThread.start();
		
		
		
		button_3.setBorderPainted(false);
		button_4.setBorderPainted(false);
		button_5.setBorderPainted(false);
		
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				actionOnStopwatch();
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOnTimer();
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionOnClock();
			}
		});
		
		button_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(select==1) {
					stopwatch.reset();
					textArea.setText("Laps-\n");
				}
				else {
					errorLabel.setVisible(false);
					textArea_1.setText("00:00:00");
					textArea_1.setVisible(true);
					stopwatchLabel.setVisible(false);
					timer.reset();
				}
			}
		});
		
		button_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(select==1) {
					stopwatch.doCycle();
				}
				else {
					errorLabel.setVisible(false);
					if(!timer.getCycleValue()) {
						setTimerSeconds();
						textArea_1.setVisible(false);
						stopwatchLabel.setVisible(true);
					}
					else {
						textArea_1.setVisible(true);
						stopwatchLabel.setVisible(false);
					}
					timer.doCycle();
				}
			}
		});
		button_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				textArea.setVisible(true);
				textArea.setText(textArea.getText()+"\n"+"#"+(laps++)+" - "+stopwatchLabel.getText()+millisecondLabel.getText());
			}
		} );
		Dimension dim=new Dimension(600,400);
		frame.setMinimumSize(dim);
		
		Thread temp=new Thread() {
			public void run() {
				while(true) {
					int height=frame.getHeight();
					int width=frame.getWidth();
					
					label.setBounds(width/2-280,height/2-80,560,100);
					if(select!=2) {
						stopwatchLabel.setBounds(width/2-170,height/2-80,200,100);
					}
					else {
						stopwatchLabel.setBounds(width/2-110,height/2-60,220,50);
					}
					progressBar.setBounds(width/2-73, height/2+5, 146, 14);
					millisecondLabel.setBounds(width/2+30, height/2-80, 100, 100);
					errorLabel.setBounds(width/2-290, height/2-40, 180, 90);
					textArea_1.setBounds(width/2-110, height/2-60, 220, 50);
					textArea.setBounds(width/2+130, height/2-60, 130, 150);
					label_1.setBounds(width/2-280, height/2+15, 560, 66);
					separator.setBounds(0, 45, width, 25);
					textField.setBounds(width/2-70, height/2-61, 140, 66);
					if(select!=2) {
						button_3.setBounds(width/2-100, height/2+35, 50, 50);
						button_4.setBounds(width/2-25, height/2+35, 50, 50);
					}
					else {
						button_3.setBounds(width/2-60, height/2+35, 50, 50);
						button_4.setBounds(width/2+5, height/2+35, 50, 50);
					}
					button_5.setBounds(width/2+50, height/2+35, 50, 50);
				}
			}
		};
		temp.start();
	}
}
