import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Toolkit;
public class DisplayBox extends Canvas{
    private String name;
    private String display;
    public DisplayBox(String display){
	setSize(150, 40);
	this.display = display;
	this.setFont(new Font("Serif", Font.BOLD, 20));
    }
    public DisplayBox(String name, Object o){
	setSize(150, 40);
	this.name = name;
	this.setFont(new Font("Serif", Font.BOLD, 20));
    }
    public void update(Object o){
	display = name + ": " + o;
	this.repaint();
    }
    public void paint(Graphics g){
	g.drawString(display, 0, 20);
	Toolkit.getDefaultToolkit().sync();
    }
}
