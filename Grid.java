import java.awt.*;
import javax.swing.*;
public abstract class Grid extends Canvas{
    private static final Color[] colors = {Color.WHITE, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.PINK.darker(), Color.BLACK};
    protected int[][] fill;
    protected int[][] border;
    public Grid(int rows, int columns){
	setSize(32 * columns, 32 * rows);
	fill = new int[rows][columns];
	border = new int[rows][columns];
	for(int i = 0; i < rows; i++){
	    for(int j = 0; j < columns; j++){
		if(i == 0 || j == 0 || i == rows-1 || j == columns-1){
		    fill[i][j] = 8;
		}
		border[i][j] = 0;
	    }
	}
    }
    public void paint(Graphics g){
	Graphics2D g2 = (Graphics2D) g;
	for(int i = 0; i < fill.length; i++){
	    for(int j = 0; j < fill[0].length; j++){
		Shape box = new Rectangle(j*30+1, i*30+1, 28, 28);
		g2.setPaint(colors[fill[i][j]]);
		g2.fill(box);
		g2.setPaint(colors[border[i][j]].brighter());
		g2.draw(box);
	    }
	}
	Toolkit.getDefaultToolkit().sync();
    }
    protected boolean checkState(Piece piece){
	//returns true if piece is in a valid position;
	for(Pixel p : piece){
	    if(fill[p.Y][p.X] != 0) return false;
	}
	return true;
    }
    protected boolean checkState(Piece piece, int y){
	//returns true if piece is in a valid position;
	for(Pixel p : piece){
	    if(fill[p.Y + y][p.X] != 0) return false;
	}
	return true;
    }
    protected void fillPiece(Piece piece){
	for(Pixel p : piece){
	    fill[p.Y][p.X] = p.STATE;
	}
    }
    protected void outlinePiece(Piece piece){
	for(Pixel p : piece){
	    border[p.Y][p.X] = p.STATE;
	}
    }
    protected void removePiece(Piece piece){
	for(Pixel p: piece){
	    fill[p.Y][p.X] = 0;
	    border[p.Y][p.X] = 0;
	}
    }
    protected abstract Piece getPiece(); //is polymorphic because grids should  have the choice of which piece to send;
}
