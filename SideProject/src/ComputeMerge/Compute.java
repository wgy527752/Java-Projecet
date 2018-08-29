package ComputeMerge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Compute<V> extends Thread{
	private V input1;
	private V input2;
	private V output;
	
	public Compute(V input1, V input2) {
		this.input1 = input1;
		this.input2 = input2;
		this.output = null;
	}
	
	@Override
	public void run() {
		output = (V) (input1.toString() + input2.toString());
	}
	
	public V getOutput() {
		return output;
	}
}
