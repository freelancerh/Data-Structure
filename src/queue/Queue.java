package queue;

public class Queue {
	protected int head;
	protected int tail;
	protected int size;
	protected int[] queue;
	protected boolean flag;
	public Queue(){
		head = 0;
		tail = 0;
		size = 10;
		queue = new int[10];
		flag = false;
	}
	
	public Queue(int size){
		head = 0;
		tail = 0;
		this.size = size;
		queue = new int[size];
		flag = false;
	}
	
	public int enQueue(int e){
		if(isFull()){
			return -1;
		}
		flag = true;
		queue[tail] = e;
		tail = (tail+1) % size;
		return 0;
	}
	
	public int deQueue(){
		if(isEmpty()){
			return -1;
		}
		flag = false;
		int temp = queue[head];
		head = (head+1) % size;
		return temp;
	}
	
	public boolean isFull(){
		if(head==tail && flag){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isEmpty(){
		if(head==tail && !flag){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int clearQueue(){
		if(isEmpty()){
			return -1;
		}
		while(!isEmpty()){
			deQueue();
		}
		return 1;
	}
	
	public int getHead(){
		if(isEmpty()){
			return -1;
		}
		return queue[head];
	}
	
	public int queueLength(){
		if(isEmpty()){
			return 0;
		}
		if(isFull()){
			return size;
		}
		return (tail+size-head) % size;
	}
	
	public static void main(String[] args){
		Queue queue = new Queue(5);
		for(int i=1;i<6;i++){
			queue.enQueue(i);
		}
		for(int i=0;i<4;i++){
			System.out.println(queue.deQueue()+"head:"+queue.head+"tail"+queue.tail);
		}
		for(int i=1;i<6;i++){
			queue.enQueue(i);
		}
		
		System.out.println("³¤¶È"+queue.queueLength()+"head:"+queue.head+"tail"+queue.tail);
		while(!queue.isEmpty()){
			System.out.println(queue.deQueue()+"head:"+queue.head+"tail"+queue.tail);
		}
	}
}
