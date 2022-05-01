public class I extends Piece{
    public I(){
	super(new int[][]{
		new int[]{0,0,0,0,0},
		new int[]{0,0,0,0,0},
		new int[]{0,6,6,6,6},
		new int[]{0,0,0,0,0},
		new int[]{0,0,0,0,0},
	    },
	    new Pair[][]{
		new Pair[]{new Pair(0,0), new Pair(-1,0), new Pair(2,0), new Pair(-1,0), new Pair(2,0)},
		new Pair[]{new Pair(-1,0), new Pair(0,0), new Pair(0,0), new Pair(0,1), new Pair(0,-2)},
		new Pair[]{new Pair(-1,1), new Pair(1,1), new Pair(-2,1), new Pair(1,0), new Pair(-2,0)},
		new Pair[]{new Pair(0,1), new Pair(0,1), new Pair(0,1), new Pair(0,-1), new Pair(0,2)}
	    }
	    );
    }
}
    
