import java.util.ArrayList;
public class Bag{
    private ArrayList<Piece> pieces;
    public Bag(){
	refill();
    }
    private void refill(){
	pieces = new ArrayList<Piece>();
	pieces.add(new J());
	pieces.add(new L());
	pieces.add(new S());
	pieces.add(new T());
	pieces.add(new Z());
	pieces.add(new I());
	pieces.add(new O());
    }
    public Piece draw(){
	Piece toReturn = pieces.remove((int) (Math.random() * pieces.size()));
	if(pieces.isEmpty()) refill();
	return toReturn;
    }
}
