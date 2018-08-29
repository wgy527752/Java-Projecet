package DelayQueue;

import java.util.PriorityQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class DelayQueue<E extends Delayed>{
	private final transient ReentrantLock lock = new ReentrantLock(); 
	private final PriorityQueue<E> q = new PriorityQueue<>();
	private final Condition available = lock.newCondition();
	/**
	 * leader这个成员有啥作用？DelayQueue的设计其实是一个Leader/Follower模式，
	 * leader就是指向Leader线程的。该模式可以减少不必要的等待时间，当一个线程是Leader时，它只需要一个时间差；
	 * 其他Follower线程则无限等待。比如头节点任务还有5秒就要开始了，那么Leader线程会sleep 5秒，不需要傻傻地等待固定时间间隔。
	 **/
	private Thread leader = null;
	
	public DelayQueue() {}

	/**
     * Inserts the specified element into this delay queue.
     *
     * @param e the element to add
     * @return {@code true}
     * @throws NullPointerException if the specified element is null
     */
	public boolean put(E e) {
		final ReentrantLock lock = this.lock;
		lock.lock();
		try {
			q.offer(e);
			//如果第一个元素等于刚刚插入进去的元素，
			//说明刚才队列是空的。现在队列里有了一个任务，
			//那么就应该唤醒所有在等待的消费者线程，避免了方案2的缺点。
			//将leader重置为null，这些消费者之间互相竞争，自然有一个会被选为leader。
			if(q.peek() == e) {
				leader = null;
				available.signal();
			}
			return true;
		}finally {
			lock.unlock();
		}
	}
	
	/**
    * Retrieves and removes the head of this queue, waiting if necessary
    * until an element with an expired delay is available on this queue.
    *
    * @return the head of this queue
    * @throws InterruptedException {@inheritDoc}
    */
	public E take() throws InterruptedException {
		final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
        	for(;;) {
        		E first = q.peek();
        		if(first == null) {
        			available.await();
        		}else {
        			long delay = first.getDelay(NANOSECONDS);
                    if (delay <= 0)
                        return q.poll();
                    first = null; // don't retain ref while waiting
                    //想象一下有个多个消费者线程用take方法去取任务,内部先加锁,然后每个线程都去peek头节点。如果leader不为空说明已经有线程在取了，让当前消费者无限等待。
                    if (leader != null)
                        available.await();
                    //如果为空说明没有其他消费者去取任务,设置leader为当前消费者，并让他等待指定的时间
                    else {
	        			Thread thisThread = Thread.currentThread();
	        			leader = thisThread;
	        			try {
	        				available.awaitNanos(delay);
	        			}finally {
	        				if(leader == thisThread) {
	        					leader = null;
	        				}
	        			}
                    }
        		}
        	}
        }finally {
            if (leader == null && q.peek() != null)
                available.signal();
            lock.unlock();
        }
	}
	
}
