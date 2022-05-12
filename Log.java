import java.awt.Canvas;
import java.awt.Graphics;
public class Log extends Canvas{
	private String message;
	private Clock clock;
	private double timestamp;
	public Log(Clock clock){
		setSize(100, 100);
		this.clock = clock;
	}
	public void setMessage(String message){
		this.message = message;
		timestamp = clock.getTime();
	}
	public void paint(Graphics g){
		if(clock.getTime()  - timestamp > 2.0){
			message = null;
		}
		if(message != null){
			g.drawString(message, 0, 10);
		}

	}
}