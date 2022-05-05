import java.awt.*;
import javax.swing.*;
public class Holder extends Grid{
    private Bag bag;
    private Piece piece;
    public Holder(){	
	super(5, 8);
	bag = new Bag();
	spawn();
    }
    public void paint(Graphics g){
	Graphics2D g2 = (Graphics2D) g;
	fillPiece(piece);
	super.paint(g2);
	removePiece(piece);
    }
    protected Piece getPiece(){
	return piece;
    }
    public void takePiece(Piece piece){
	this.piece = piece;
	this.piece.setLocation(new Pair(1, 1));
    }
    public void spawn(){
	piece = bag.draw();
	this.piece.setLocation(new Pair(1,1));
    }
}
