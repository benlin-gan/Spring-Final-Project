public class O extends Piece{
    public O(){
	super(new int[][]{
		new int[]{0, 7, 7},
		new int[]{0, 7, 7},
		new int[]{0, 0, 0}
	    },
	    new Pair[][]{
		new Pair[]{new Pair(0,0)},
		new Pair[]{new Pair(0,-1)},
		new Pair[]{new Pair(-1,-1)},
		new Pair[]{new Pair(-1,0)}
	    }
	    );
    }
}
    
