import java.util.Iterator;
public class Piece implements Iterable<Pixel>{
    private int x;
    private int y;
    private int[][] raw;
    public Piece(int y, int[][] raw){
	x = 1;
	this.y = y;
	this.raw = raw;
    }
    public void rotate(boolean clockwise){
	raw = rotate(raw);
	if(!clockwise){
	    raw = rotate(rotate(raw));
	}
    }
    private int[][] rotate(int[][] input){
	//rotation about the center of a square grid
	int[][] output = new int[input.length][input[0].length];
	for(int i = 0; i < input.length / 2 + 1; i++){
	    for(int j = 0; j < input[0].length / 2 + 1; j++){
		output[i][j] = input[j][input.length - i - 1];
		output[i][output.length - j - 1] = input[j][i];
		output[output.length - i - 1][output.length - j - 1] = input[j][input.length - i - 1];
		output[output.length - i - 1][j] = input[input.length - j - 1][input.length - i - 1];
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
	public PieceIterator(Piece p){
	    this.p = p;
	    row = 0;
	    col = 0;
	}
	public boolean hasNext(){
	    return (row != p.raw.length - 1) || (col != p.raw[0].length - 1);
	}
	public Pixel next(){
	    int x = col;
	    int y = row;
	    int state = p.raw[row][col];
	    if(++col == p.raw.length){
		col = 0;
		row++;
	    }
	    return new Pixel(x + p.x, y + p.y, state);
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
