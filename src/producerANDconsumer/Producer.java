package producerANDconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

	private BlockingQueue<PCData> queue;
	private static AtomicInteger count = new AtomicInteger();
	private volatile Boolean isRunning = true; 

	public Producer(BlockingQueue<PCData> queue) {
		// TODO Auto-generated constructor stub
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (isRunning) {
				if(count.get() < 30){
					PCData data = new PCData(count.incrementAndGet());
					System.out.println("生产者： "+Thread.currentThread().getName()+": "+data);
					if (!queue.offer(data)){
						System.err.println("failed to put data:"+ data);
					}
					Thread.sleep(500+new Random().nextInt(200));
				}else{
					isRunning = false;
					//break;
				}
			}
			//System.out.println(Thread.currentThread().getName()+"生产结束！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public void stop(){
		this.isRunning = false;
	}
}
