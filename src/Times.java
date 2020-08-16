import java.awt.Label;

public class Times extends Thread {
	public volatile boolean bExit=false;
	private Label label;
	public Times(Label label) {
		this.label=label;
	}
	
	
	public void run() {
		while(!bExit) {
			String tempTime=java.time.LocalTime.now().toString().substring(0,8);
			if(Integer.parseInt(tempTime.substring(0, 2))<12) {
				label.setText("Time : "+tempTime+" AM");
			}
			else {
				label.setText("Time : "+tempTime+" PM");
			}
			try{
				Thread.sleep(1000);
			}
			catch(Exception e) {
			}
			
		}
	}
	
}
