public class Pair{
    //read only data class for coordinate pairs;
    //java standard library doesn't come with a pair class, bruh moment;
    public final int X;
    public final int Y;
    public Pair(int X, int Y){
	this.X = X;
	this.Y = Y;
    }
    public Pair inverse(){
	//convenience function to generate inverse pair;
	return new Pair(-X, -Y);
    }
    public static Pair add(Pair a, Pair b){
	return new Pair(a.X + b.X, b.X + b.Y);
    }
}
