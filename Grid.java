import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Grid extends Canvas{
  private static final Color[] colors = new Color[]{Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK };
  private int[][] state = new int[14][14];
  public Grid(){
    setSize(500, 500);
  }
  public void paint(Graphics g){
    for(int i = 0; i < state.length; i++){
      for(int j = 0; j < state[0].length; j++){
        g.setColor(colors[state[i][j]]);
        g.fillRect(i*30, j*30, 30, 30);
      }
    }
  }
  Timer timer = new Timer(16, new ActionListener(){
    public void actionPerformed(ActionEvent e){
      
    }
  });
}