import java.awt.*;
public class Holder extends Grid{
    private Bag bag;
    private Piece piece;
    public Holder(Bag bag){	
	super(5, 8);
	this.bag = bag;
	spawn();
    }
    public Holder(){
	super(5, 8);
    }
    public void paint(Graphics g){
	Graphics2D g2 = (Graphics2D) g;
	if(piece != null){
		fillPiece(piece);
	}
	super.paint(g2);
	if(piece != null){
		removePiece(piece);
	}	
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
