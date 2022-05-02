import java.awt.*;
import javax.swing.*;
public class Holder extends Grid{
    private Bag bag;
    private Piece piece;
    public Holder(){	
	super(5, 8);
	bag = new Bag();
	piece = new T().cleaned(1);
    }
    public void paint(Graphics g){
	fillPiece(piece);
	super.paint(g);
	removePiece(piece);
    }
    protected Piece getPiece(){
	return piece;
    }
    public void takePiece(Piece piece){
	this.piece = piece.cleaned(1);
    }
    private void spawn(){
	piece = bag.draw();
    }
}
