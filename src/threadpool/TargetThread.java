package threadpool;

public class TargetThread implements Runnable{

	private String threadName;
	public TargetThread(String name) 
	{
		threadName = name;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			Thread.sleep(100);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString(){
		return "TargetThread name is : "+threadName;
	}
}
