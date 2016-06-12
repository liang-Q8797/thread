package producerANDconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	public static void main(String[] args) {
		BlockingQueue<PCData> queue = new LinkedBlockingQueue<>();
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue); 
//		Producer producer1 = new Producer(queue);
//		Producer producer2 = new Producer(queue);
//		Producer producer3 = new Producer(queue);
//		Consumer Consumer1 = new Consumer(queue);
//		Consumer Consumer2 = new Consumer(queue);
//		Consumer Consumer3 = new Consumer(queue);
		ExecutorService service = Executors.newFixedThreadPool(4);
		for(int i=0;i<5;i++){
			service.submit(producer);
			service.submit(consumer);
		}
//		producer.stop();
//		service.submit(producer1);
//		service.submit(producer2);
//		service.submit(producer3);
//		
//		service.submit(Consumer1);
//		service.submit(Consumer2);
//		service.submit(Consumer3);
//		producer1.stop();
//		producer2.stop();
//		producer3.stop();
		service.shutdown();
	}
}
