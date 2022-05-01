import javax.swing.JLabel;
public class DisplayBox extends JLabel{
    private String name;
    public DisplayBox(String name, Object o){
	super(name + ": " + o);
	this.name = name;
    }
    public void update(Object o){
	super.setText(name + ": " + o);
    }
}
