import javax.swing.JLabel;
import java.awt.Font;
public class DisplayBox extends JLabel{
    private String name;
    public DisplayBox(String name, Object o){
	super(name + ": " + o);
	this.name = name;
	this.setFont(new Font("Serif", Font.BOLD, 20));
    }
    public void update(Object o){
	super.setText(name + ": " + o);
    }
}
