import java.util.ArrayList;
public class Bag{
    //pieces are drawn from a bag, guarunteeing that all 7 pieces will show up once before there is a repeat.
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
	Piece toReturn = pieces.remove((int) (Math.random() * pieces.size())); //in java arrayList.remove() returns the removed object
	if(pieces.isEmpty()) refill();
	return toReturn;
    }
}
