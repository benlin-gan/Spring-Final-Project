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
    public void spawn(){
	piece = bag.draw().cleaned(1);
    }
}
