import java.util.ArrayList;
import java.awt.event.KeyEvent;
public class EventQueue{
    //queue for KeyEvents implemented with an arrayList;
    private ArrayList<KeyEvent> queue;
    public EventQueue(){
	this.queue = new ArrayList<KeyEvent>();
    }
    public void push(KeyEvent e){
	queue.add(e);
    }
    public KeyEvent pop(){
	if(queue.isEmpty()) return null;
	return queue.remove(0);
    }
}