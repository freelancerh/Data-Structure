package queue;

public class QueueNode {
	protected int data;
	protected QueueNode next;
	
	public QueueNode(){
		data = 0;
		next = null;
	}
	
	public QueueNode(int data){
		this.data = data;
		next = null;
	}
	
	public QueueNode(int data, QueueNode next){
		this.data = data;
		this.next = next;
	}
	
	public void setData(int e){
		data = e;
	}
	
	public int getData(){
		return data;
	}
}
