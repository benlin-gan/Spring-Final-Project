import javax.swing.JLabel;
public class Displayer extends JLabel{
  //wrapper around a variable;
  private String label;
  private int state;
  public Displayer(String label, int state){
    super(label + ": " + state);
    this.label = label;
    this.state = state;
  }
  public void update(int state){
    this.state = state;
    super.setText(label + ": " + state);
  }
  public int getState(){
    return state;
  }
}