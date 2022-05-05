public class Clock{
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
