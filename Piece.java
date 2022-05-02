import java.util.Iterator;
public class Piece implements Iterable<Pixel>{
    private int x;
    private int y;
    private int rotation;
    private Pair[][] offsets;
    private int[][] raw;
    public Piece(int[][] raw, Pair[][] offsets){
	y = 1;
	x = 5;
	rotation = 0;
	this.raw = raw;
	this.offsets = offsets;
    }
    public Piece clone(){
	Piece out = new Piece(raw, offsets);
	out.x = this.x;
	out.y = this.y;
	out.rotation = this.rotation;
	return out;
    }
    public Piece cleaned(int x){
	while(rotation != 0){
	    rotate(1);
	}
	Piece toReturn = new Piece(raw, offsets);
	toReturn.y = 1;
	toReturn.x = x;
	return toReturn;
    }
    public Pair[] generateOffsets(int direction){
	int begin = rotation;
	int end = (rotation + direction) % 4;
	Pair[] toReturn = new Pair[offsets[0].length];
	for(int i = 0; i < toReturn.length; i++){
	    toReturn[i] = Pair.add(offsets[begin][i], offsets[end][i].inverse());
	}
	return toReturn;
    }
    public void translate(Pair offset){
	x += offset.X;
	y += offset.Y;
    }
    public void rotate(int direction){
	//mutator for rotation and raw
	rotation = (rotation + direction) % 4;
	int times = direction % 4;
	for(int i = 0; i < times; i++){
	    raw = rotate(raw);
	}
    }
    public static int[][] rotate(int[][] input){
	//rotation about the center of a square grid
	int l = input.length;
	int[][] output = new int[l][l];
	for(int row = 0; row < l/2 + 1; row++){
	    for(int col = row; col < l/2 + 1; col++){
		int topLeft  = input[row][col];
		int topRight = input[col][l - row - 1];
		int bottomRight = input[l - row - 1][l - col - 1];
		int bottomLeft = input[l - col - 1][row];
		output[col][l - row - 1] = topLeft;
		output[l - row - 1][l - col - 1] = topRight;
		output[l - col - 1][row] = bottomRight;
		output[row][col] = bottomLeft;
	    }
	}
	return output;
    }
    public PieceIterator iterator(){
	return new PieceIterator(this);
    }
    class PieceIterator implements Iterator<Pixel>{
	private Piece p;
	private int row;
	private int col;
	private int reported;
	public PieceIterator(Piece p){
	    this.p = p;
	    row = 0;
	    col = 0;
	    reported = 0;
	}
	public boolean hasNext(){
	    return reported < 4;
	}
	private void advance(){
	    col++;
	    if(col == p.raw[0].length){
		col = 0;
		row++;
	    }
	}
	public Pixel next(){
	    while(p.raw[row][col] == 0){
		advance();
	    }
	    reported++;
	    Pixel toReturn = new Pixel(col + p.x, row + p.y, p.raw[row][col]);
	    advance();
	    return toReturn;
	}
    }
    
    public int getX(){
	return x;
    }
    public int getY(){
	return y;
    }
    public int[][] getRaw(){
	return raw;
    }
    public void drop(){
	y++;
    }
    public void undrop(){
	y--;
    }
}
