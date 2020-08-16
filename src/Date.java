import java.awt.Label;

public class Date extends Thread {
	public volatile boolean bExit=false;
	private Label label;
	public Date(Label label) {
		this.label=label;
	}
	
	
	public void run() {
		while(!bExit) {
			String tempDate=java.time.LocalDate.now().toString();
			label.setText("Date : "+tempDate);
			try{
				Thread.sleep(1000);
			}
			catch(Exception e) {
			}
			
		}
	}
	
}
