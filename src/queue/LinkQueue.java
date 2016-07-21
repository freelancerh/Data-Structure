package queue;

public class LinkQueue {
	protected QueueNode head;
	protected QueueNode tail;
	
	public LinkQueue(){
		head = tail = new QueueNode(0);
	}
	
	public int enQueue(int e){
		tail.next = new QueueNode(e);
		head.setData(head.getData()+1);
		tail = tail.next;
		return 0;
	}
	
	public int deQueue(){
		if(isEmpty()){
			return -1;
		}
		int temp = head.next.getData();
		head.next = head.next.next;
		return temp;
	}
	
	public boolean isEmpty(){
		if(head.next == null)
			return true;
		else{
			return false;
		}
	}
	
	public int clearQueue(){
		head.next = null;
		tail = head;
		return 1;
	}
	
	public int getHead(){
		if(isEmpty()){
			return -1;
		}
		return head.next.getData();
	}
	
	public int queueLength(){
		return head.data;
	}
	
	public static void main(String[] args){
		LinkQueue queue = new LinkQueue();
		for(int i=0; i<10; i++){
			queue.enQueue(i);
		}
		System.out.println("³¤¶È£º"+queue.queueLength());
		while(!queue.isEmpty()){
			System.out.println(queue.deQueue());
		}
	}
}
