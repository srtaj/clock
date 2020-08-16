import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;

class Stopwatch extends Thread {
	
	private int milliseconds;
	private Label label, millisecondLabel;
	private boolean cycle;
	private JButton button2;
	
	public Stopwatch(Label label,Label millisecondLabel ,JButton button2) {
		milliseconds=0;
		cycle=false;
		this.label=label;
		this.button2=button2;
		this.millisecondLabel=millisecondLabel;
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
	
	public void reset() {
		milliseconds=0;
		cycle=false;
		label.setText("00:00");
		millisecondLabel.setText(".00");
	}
	public void run() {
		while(true) {
			if(cycle) {
				int seconds=milliseconds/100;
				int minute=seconds/60;
				int hour=minute/60;
				int absSeconds=seconds%60;
				String absmilliseconds=Integer.toString(milliseconds%100);
				if(absmilliseconds.length()==1) {
					absmilliseconds="0"+absmilliseconds;
				}
				
				minute%=60;
				seconds%=10;
				if(milliseconds%100==00) {
					if(hour==0) {
						if(minute<10) {
							if(absSeconds<10) {
								label.setText("0"+minute+":0"+absSeconds);
							}
							else {
								label.setText("0"+minute+":"+absSeconds);
							}
						}
						else {
							if(absSeconds<10) {
								label.setText(minute+":0"+absSeconds);
							}
							else {
								label.setText(minute+":"+absSeconds);
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
				}
				
				millisecondLabel.setText("."+absmilliseconds);
				
				milliseconds++;
			}
			try{
				Thread.sleep(10);
			}
			catch(Exception e) {
			
			}
		}
	}
	
}
