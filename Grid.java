import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Grid extends Canvas{
  //composite information about active piece and grid state.
  private static final Color[] colors = new Color[]{Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK };
  private int[][] state = new int[14][14];
  private Piece active;
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
  private void checkState(){
    for(int i = active.getX(); i < state.length; i++){
      for(int j = active.getY(); j< state[0].length; j++){
        if(active.getRaw()[i][j] != 0 && state[active.getX() + i][active.getY() + j] != 0){
          
        }
      }
    }
  }
}