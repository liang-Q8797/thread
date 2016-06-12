package producerANDconsumer;

public class PCData {
	private int data;
	
	public PCData(int data){
		this.data = data;
	}
	
	public int getData(){
		return data;
	}
	
	@Override
	public String toString(){
		return "data: "+data;
	}
}
