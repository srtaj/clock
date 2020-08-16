import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

class Timer extends Thread {
	
	private int seconds;
	private int total;
	private Label label;
	private boolean cycle;
	private JButton button2;
	private JProgressBar progressBar;
	
	public Timer(Label label,JButton button2, int seconds, JProgressBar progressbar) {
		cycle=false;
		this.seconds=seconds;
		this.label=label;
		this.button2=button2;
		this.progressBar=progressbar;
	}
	
	public boolean getCycleValue() {
		return cycle;
	}
	
	public Timer(Label label, JButton button2,JProgressBar progressBar) {
		this(label,button2,0,progressBar);
	}
	
	public void doCycle() {
		cycle=!cycle;
		if(cycle) {
			button2.setIcon(new ImageIcon(getClass().getResource("resources/Pause.png")));
		}
		else {
			button2.setIcon(new ImageIcon(getClass().getResource("resources/Play.png")));
		}
	}
	
	public void setTime(int seconds) {
		this.seconds=seconds;
		total=seconds;
		seconds--;
	}
	
	public void reset() {
		seconds=0;
		total=0;
		cycle=false;
		label.setText("00:00:00");
		progressBar.setValue(0);
		button2.setIcon(new ImageIcon(getClass().getResource("resources/Play.png")));
	}
	public void run() {
		while(true) {
			
			if(cycle&&seconds>=0) {
				try {
					progressBar.setValue((int)(((double)(seconds)/total)*100.0));
				}
				catch(ArithmeticException e) {
					
				}
				int minute=seconds/60;
				int hour=minute/60;
				int absSeconds=seconds%60;
				minute%=60;
					if(hour<10) {
						if(minute<10) {
							if(absSeconds<10) {
								label.setText("0"+hour+":0"+minute+":0"+absSeconds);
							}
							else {
								label.setText("0"+hour+":0"+minute+":"+absSeconds);
							}
						}
						else {
							if(absSeconds<10) {
								label.setText("0"+hour+":"+minute+":0"+absSeconds);
							}
							else {
								label.setText("0"+hour+":"+minute+":"+absSeconds);
							}
						}
					}
					else {
						if(minute<10) {
							if(absSeconds<10) {
								label.setText(hour+":0"+minute+":0"+absSeconds);
							}
							else {
								label.setText(hour+":0"+minute+":"+absSeconds);
							}
						}
						else {
							if(absSeconds<10) {
								label.setText(hour+":"+minute+":0"+absSeconds);
							}
							else {
								label.setText(hour+":"+minute+":"+absSeconds);
							}
						}
					}
				
				
				
				
				if(seconds==0) {
					cycle=false;
					JOptionPane.showMessageDialog(null, "Time has ended", null, 0);
				}
				seconds--;
			}
			
			try{
				Thread.sleep(1000);
			}
			catch(Exception e) {
			
			}
		}
	}
	
}
