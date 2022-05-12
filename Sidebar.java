import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
public class Sidebar extends JPanel{
	private Holder hold;
	private Holder next;
	private JLabel hText;
	private JLabel nText;
	private Log log;
	public Sidebar(Log log){
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		hold = new Holder();
		next = new Holder(new Bag());
		hText = new JLabel("HOLD");
		hText.setFont(new Font("Serif", Font.BOLD, 20));
		this.add(hText);
		this.add(hold);
		nText = new JLabel("NEXT");
		nText.setFont(new Font("Serif", Font.BOLD, 20));
		this.add(nText);
		this.add(next);
		this.log = log;
		this.add(log);
	}
	public void paint(Graphics g){
		super.paint(g);
		hold.repaint(); 
		next.repaint();
		log.repaint();
		//hText.repaint();
		//nText.repaint();
		Toolkit.getDefaultToolkit().sync();
	}
	public Holder getHold(){
		return hold;
	}
	public Holder getNext(){
		return next;
	}

}