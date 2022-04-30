public class T extends Piece{
    public T(){
	super(new int[][]{
		new int[]{0, 4, 0},
		new int[]{4, 4, 4},
		new int[]{0, 0, 0}
	    },
	    new Pair[][]{
		new Pair[]{new Pair(0,0), new Pair(0,0), new Pair(0,0), new Pair(0,0), new Pair(0,0)},
		new Pair[]{new Pair(0,0), new Pair(1,0), new Pair(1,-1), new Pair(0,2), new Pair(1,2)},
		new Pair[]{new Pair(0,0), new Pair(0,0), new Pair(0,0), new Pair(0,0), new Pair(0,0)},
		new Pair[]{new Pair(0,0), new Pair(-1,0), new Pair(-1,-1), new Pair(0,2), new Pair(-1,2)}
	    }
	    );
    }
}
    
