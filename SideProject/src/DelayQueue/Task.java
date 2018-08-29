package DelayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Task implements Delayed{
	private String name;
	private long startTime;
	
	public Task(String name, long delay) {
		this.name = name;
		this.startTime = System.currentTimeMillis() + delay;
	}
	
	@Override
	public int compareTo(Delayed o) {
        return (int)(this.startTime - ((Task) o).startTime);
    }

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		long diff = startTime - System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MICROSECONDS);
	}
	
	@Override
    public String toString() {
        return "task " + name + " at " + startTime;
    }
}
