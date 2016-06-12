package producerANDconsumer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable{
	private BlockingQueue<PCData> queue;
	
	public Consumer(BlockingQueue<PCData> queue){
		this.queue = queue;
	}

	@Override
	public void run() {
		try{
			while(true)
			{
				//PCData data = queue.take(); //如果没有数据，一直等待
				//超过50毫秒，则返回null
				PCData data = queue.poll(1000,TimeUnit.MILLISECONDS);
				if(data != null){
					System.out.println("消费者："+Thread.currentThread().getName()+": "+data);
					int result = data.getData() * data.getData();
					System.out.println(MessageFormat.format("{0}*{1}",data.getData(),data.getData(),result));
					Thread.sleep(1000+new Random().nextInt(200));
				}else{
					System.out.println("没有数据可以处理了！");
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
