package threadpool;

public class Main {
	public static void main(String[] args) {
		ThreadPool pool = ThreadPool.getInstance();
		
		for(int i=0;i<5;i++){
			pool.start(new TargetThread("testThread"+i));
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!pool.getShutDown()){
			pool.shutDown();
		}
		
		System.out.println("成功关闭线程池！！！");
	}
}
