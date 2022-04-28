import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main{
  public static final int WIDTH = 500;
  public static final int HEIGHT = 400;
	public static void main(String[] args){
		JFrame frame = new JFrame("My Spring Final Project");
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    frame.setSize(WIDTH, HEIGHT);
    Displayer score = new Displayer("score", 0);
    Displayer power = new Displayer("power", 1);
    Displayer cost = new Displayer("cost", 30);
    Displayer autoClickers = new Displayer("autos", 0);
    Timer timer = new Timer(1000, new ActionListener(){
      public void actionPerformed(ActionEvent e){
        score.update(score.getState() + autoClickers.getState() * power.getState());
      }
    });
    JButton increment = new JButton("Increment"); 
    JButton powerIncrement = new JButton("Power Up");
    JButton buyAuto = new JButton("Buy Auto-Clicker");
    increment.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        score.update(score.getState() + power.getState());
      }
    });
    powerIncrement.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(score.getState() >= cost.getState()){
          power.update(power.getState() + 1);
          score.update(score.getState() - cost.getState());
          cost.update((int)(cost.getState() * 1.15));
        }
      }
    });
    buyAuto.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        if(score.getState() >= cost.getState()){
          autoClickers.update(autoClickers.getState() + 1);
          score.update(score.getState() - cost.getState());
          cost.update((int)(cost.getState() * 1.15));
        }
      }
    });
    panel.add(cost);
    panel.add(power);
    panel.add(powerIncrement);
    panel.add(increment);
    panel.add(score);
    panel.add(buyAuto);
    panel.add(autoClickers);
    frame.getContentPane().add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    timer.start();
	}
}