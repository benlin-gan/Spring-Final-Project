public class JLSTZ extends Piece{
    public JLSTZ(int[][] raw){
	super(raw,
	    new Pair[][]{
		new Pair[]{new Pair(0,0), new Pair(0,0), new Pair(0,0), new Pair(0,0), new Pair(0,0)},
		new Pair[]{new Pair(0,0), new Pair(1,0), new Pair(1,-1), new Pair(0,2), new Pair(1,2)},
		new Pair[]{new Pair(0,0), new Pair(0,0), new Pair(0,0), new Pair(0,0), new Pair(0,0)},
		new Pair[]{new Pair(0,0), new Pair(-1,0), new Pair(-1,-1), new Pair(0,2), new Pair(-1,2)}
	    }
	    );
    }
}
    
