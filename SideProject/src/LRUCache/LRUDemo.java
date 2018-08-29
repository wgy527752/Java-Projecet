package LRUCache;

public class LRUDemo {
	static ThreadLRU<Integer, String> lru = ThreadLRU.getLRUThread(15);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		new LRUDemo();
		
	}
	
	public LRUDemo() {
		Thread t1 = new Thread() {
			public void run() {
				for (int i = 90; i < 100; i++) {
		            lru.put(i,  i + "--" + i);
		        }
		        lru.put(95, 95+"**");//将91-91用91**替代，并且移动到最前方
		        lru.remove(93);//将93移走
		        lru.remove(94);//将93移走
		        lru.remove(51);//将93移走
		        lru.put(50, "sdasd");//将93移走
		        System.out.println("*************");
		        lru.sop();
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
		        lru.put(50,  50 + "--" + 50);
		        lru.put(51,  51 + "--" + 51);
		        //lru.get(50);
		        //lru.put(93, 93+"**");//将91-91用91**替代，并且移动到最前方
		        //lru.remove(95);//将93移走
		        lru.put(50, "sdasd");//将93移走
			}
		};
		t2.start();
		t1.start();
	}

}
