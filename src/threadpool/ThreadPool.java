package threadpool;

import java.util.List;
import java.util.Vector;

@SuppressWarnings({"rawtypes","unchecked"})
public class ThreadPool 
{

	private static ThreadPool instance = null;
	//空闲的线程队列
	private List<PThread> idleThreads;
	//已有的线程数
	private int threadcounter;
	//是否已关闭
	private boolean isShutdown = false;
	
	//单例模式
	private ThreadPool(){
		this.idleThreads = new Vector(5);
		threadcounter = 0;
	}
	
	public int getCreatedThreadCount(){
		return threadcounter;
	}
	
	//双重检查锁定
	public static ThreadPool getInstance(){
		if(instance==null){
			synchronized (ThreadPool.class) {
				if(instance==null){
					instance = new ThreadPool();
				}
			}
		}
		return instance;
	}
	
	//执行任务
	public synchronized void start(Runnable target){
		PThread thread = null;
		if(idleThreads.size()>0){
			int lastindex = idleThreads.size()-1;
			thread = idleThreads.get(lastindex);
			idleThreads.remove(lastindex);
			thread.setTarget(target);
		}else{                 
			threadcounter++;
			//没有空闲线程，则创建新线程
			thread = new PThread(target,"PThread # "+ threadcounter,this);
			thread.start();
		}
	}
	
	//将线程放入池中
	protected synchronized void repool(PThread repoolThread){
		if(!isShutdown){
			idleThreads.add(repoolThread);
		}else{
			repoolThread.shutDown();
		}
	}
	
	//停止线程池中的所有线程
	public synchronized void shutDown()
	{
		isShutdown = true;
		for(int threadIndex=0;threadIndex<idleThreads.size();threadIndex++){
			PThread thread = idleThreads.get(threadIndex);
			thread.shutDown();
		}
	}	
	
	//
	public boolean getShutDown(){
		return isShutdown;
	}
}
