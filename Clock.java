public class Clock{
    //wrapper around a double; enforcing correct clock updates is the responsibility of the programmer
    private double time;
    public Clock(){
	time = 0.0;
    }
    public double getTime(){
	return time;
    }
    public void update(double inc){
	time += inc;
    }
}
