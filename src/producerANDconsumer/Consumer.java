package producerANDconsumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
	private BlockingQueue<PCData> queue;
	
	public Consumer(BlockingQueue<PCData> queue){
		this.queue = queue;
	}

	@Override
	public void run() {
		try{
			while(true){
				PCData data = queue.take();
				if(data != null){
					System.out.println("消费者："+Thread.currentThread().getName()+": "+data);
					int result = data.getData() * data.getData();
					System.out.println(MessageFormat.format("{0}*{1}",data.getData(),data.getData(),result));
					Thread.sleep(1000+new Random().nextInt(200));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
