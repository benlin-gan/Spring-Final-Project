import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main{
  public static final int WIDTH = 500;
  public static final int HEIGHT = 400;
	public static void main(String[] args){
		JFrame frame = new JFrame("My Spring Final Project");
    JPanel panel = new JPanel();
    frame.setSize(WIDTH, HEIGHT);
    Displayer score = new Displayer("score", 0);
    Displayer power = new Displayer("power", 1);
    Displayer cost = new Displayer("cost", 30);
    class IncListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
        score.update(score.getState() + power.getState());
      }
    }
    class BuyListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
        if(score.getState() >= cost.getState()){
          power.update(power.getState() + 1);
          score.update(score.getState() - cost.getState());
          cost.update((int)(cost.getState() * 1.15));
        }
      }
    }
    JButton increment = new JButton("Increment"); 
    JButton powerIncrement = new JButton("Power Up");
    increment.addActionListener(new IncListener());
    powerIncrement.addActionListener(new BuyListener());
    panel.add(cost);
    panel.add(power);
    panel.add(powerIncrement);
    panel.add(increment);
    panel.add(score);
    frame.add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
	}
}