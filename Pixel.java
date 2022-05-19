public class Pixel{
    //read-only data class representing a place on the grid
    public final int X;
    public final int Y;
    public final int STATE;
    public Pixel(int X, int Y, int STATE){
	this.X = X;
	this.Y = Y;
	this.STATE = STATE;
    }
}
