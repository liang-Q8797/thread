package threadpool;

public class PThread extends Thread 
{
	// 线程池
	private ThreadPool pool;
	// 任务
	private Runnable target;
	// 是否已关闭
	private boolean isShutDown = false;
	// 是否空闲
	private boolean isIdle = false;

	public PThread(Runnable target, String name, ThreadPool pool) {
		super(name);
		this.pool = pool;
		this.target = target;
	}

	// 获取任务
	public Runnable getTarget() {
		return target;
	}
	
	//获取当前线程的状态
	public boolean getIsIdle(){
		return isIdle;
	}

	@Override
	public void run() {
		//只要没有关闭，则一直不结束该进程
		while (!isShutDown) {
			isIdle = false;
			if(target!=null){
				target.run();
			}
			isIdle = true;
			try{
				//该任务结束后，不关闭线程，而是放入线程池空闲队列
				pool.repool(this);
				synchronized(this)
				{
					//线程空闲，等待新的任务到来
					wait();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			isIdle = false;
		}
	}
	//关闭线程
	public synchronized void shutDown() {
		isShutDown = true;
		notifyAll();
	}
	
	public synchronized void setTarget(Runnable target) {
		this.target = target;
		notifyAll();
	}
}
