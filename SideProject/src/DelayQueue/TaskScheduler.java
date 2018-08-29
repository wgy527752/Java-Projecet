package DelayQueue;

public class TaskScheduler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DelayQueue<Task> queue = new DelayQueue<>();
		new Thread(new TaskProducer(queue), "Producer Thread").start();
		new Thread(new TaskProducer(queue), "Producer Thread").start();
		new Thread(new TaskProducer(queue), "Producer Thread").start();
        new Thread(new TaskConsumer(queue), "Consumer Thread").start();
        new Thread(new TaskConsumer(queue), "Consumer Thread").start();
        new Thread(new TaskConsumer(queue), "Consumer Thread").start();
        
	}

}
