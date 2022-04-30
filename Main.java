import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Main{
  public static final int WIDTH = 500;
  public static final int HEIGHT = 400;
	public static void main(String[] args){
		JFrame frame = new JFrame("My Spring Final Project");
    Grid g = new Grid();
    frame.setSize(WIDTH, HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(g);
    frame.pack();
    frame.setVisible(true);
    int[][] test = new int[][]{
      new int[]{0, 1, 0},
      new int[]{0, 1, 1},
      new int[]{0, 1, 0}
    };
    for(int[] row : test){
      for(int k : row){
        System.out.print(k);
      }
      System.out.println();
    }
    for(int[] row : new Piece().rotate(test)){
      for(int k : row){
        System.out.print(k);
      }
      System.out.println();
    }
    new Timer(16, new ActionListener(){
      public void actionPerformed(ActionEvent e){
        //in main event loop.
        //Every non loop where graphics don't update, 
      }
    }).start();
  }
}