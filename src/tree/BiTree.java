package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
/*中序序列不能确定一棵二叉树，即不能创建一棵二叉树*/
public class BiTree {
	protected BiNode root;
	
	public BiTree(){
		root = null;
	}
	
	public BiNode getRoot(){
		return root;
	}
	
	public int createBiTreeByPreOrder(BiNode node){
		if(root == null){
			root = new BiNode();
			node = root;
		}
		Scanner sc = new Scanner(System.in);
		node.data = sc.nextLine();
		if(node.data.compareTo("#") == 0){
			return -1;
		}
		node.leftChild = new BiNode();
		createBiTreeByPreOrder(node.leftChild);
		
		node.rightChild = new BiNode();
		createBiTreeByPreOrder(node.rightChild);
		return 0;
	}
	
	
	public boolean isBalance(BiNode root){
		if(root == null){
			return true;
		}
		if(Math.abs(getHeight(root.leftChild) - getHeight(root.rightChild)) > 1){
			return false;
		}
		if(isBalance(root.leftChild) && isBalance(root.rightChild)){
			return true;
		}
		return false;
	}
	
	public int createBiTreeByPreOrderNoRecursion(BiNode node){
		Stack<BiNode> stack = new Stack<BiNode>();
		BiNode pointer = null;
		if(root == null){
			root = new BiNode();
			node = root;
		}
		Scanner sc = new Scanner(System.in);
		stack.push(node);
		while(!stack.isEmpty()){
			pointer = stack.pop();
			pointer.data = sc.nextLine();
			if(!pointer.data.equals("#")){
				pointer.leftChild = new BiNode();
				pointer.rightChild = new BiNode();
				stack.push(pointer.rightChild);
				stack.push(pointer.leftChild);
			}
		}
		return 0;
	}
	
	public int createBiTreeByInOrderNoRecursion(BiNode node){
		Stack<BiNode> stack = new Stack<BiNode>();
		root = new BiNode();
		node = root;
		stack.push(root);
		while(!stack.isEmpty()){
			
		}
		return 0; 
	}
	
	
	public int getHeight(BiNode root){
		int height;
		 if(root == null){
			 return 0;
		 }
		 height = 1 + Math.max(getHeight(root.leftChild), getHeight(root.rightChild));
		 return height;
	}
	
	public int getHeightByNoRecursion(BiNode root){
		Queue<BiNode> queue = new LinkedList<BiNode>();
		int height = 0;
		int size;
		BiNode pointer = null;
		queue.offer(root);
		while(!queue.isEmpty()){
			height++;
			size = queue.size();
			for(int i=0; i<size; i++){
				pointer = queue.poll();
				if(pointer.leftChild != null)
					queue.offer(pointer.leftChild);
				if(pointer.rightChild != null)
					queue.offer(pointer.rightChild);
			}
			
		}
		return height;
	}
	
	
	
	public int getWidth(BiNode root){
		Queue<BiNode> queue = new LinkedList<BiNode>();
		BiNode pointer = null;
		int width = 0;
		int temp = 0;
		queue.offer(root);
		while(!queue.isEmpty()){
			temp = queue.size();
			if(temp > width){
				width = temp;
			}
			for(int i=0; i<temp; i++){
				pointer = queue.poll();
				if(pointer.leftChild != null){
					queue.offer(pointer.leftChild);
				}
				if(pointer.rightChild != null){
					queue.offer(pointer.rightChild);
				}
			}
		}
		return width;
	}
	
	public int changeChild(BiNode root){
		if(root == null){
			return -1;
		}
		BiNode temp = root.leftChild;
		root.leftChild = root.rightChild;
		root.rightChild = temp;
		changeChild(root.leftChild);
		changeChild(root.rightChild);
		return 0;
	}
	
	public int printBiTreeByPostOrderNoRecursion(BiNode node){
		Stack<BiNode> stack = new Stack<BiNode>();
		BiNode pointer = null;
		BiNode lastVisited = null;
		stack.push(node);
		boolean flag = true; //flag 表示是否可以进入左子树
		while(!stack.isEmpty()){
			while(stack.peek().leftChild != null && flag){
				stack.push(stack.peek().leftChild);
			}
			if(stack.peek().rightChild == lastVisited){
				pointer = stack.pop();
				System.out.print(pointer.data);
				lastVisited = pointer;
				flag = false;
			}
			else if(stack.peek().rightChild != null){
				stack.push(stack.peek().rightChild);
				flag = true;
			}
			else{
				pointer = stack.pop();
				System.out.print(pointer.data);
				lastVisited = pointer;
				flag = false;
			}
		}
		return 0;
	}
	
	public int printBiTreeByInOrderNoRecursion(BiNode node){
		Stack<BiNode> stack = new Stack<BiNode>();
		BiNode pointer = null;
		stack.push(node);
		boolean flag = true;
		while(!stack.isEmpty()){
			while(stack.peek().leftChild != null && flag){
				stack.push(stack.peek().leftChild);
			}
			pointer = stack.pop();
			System.out.print(pointer.data);
			if(pointer.rightChild != null){
				stack.push(pointer.rightChild);
				flag = true;
			}	
			else{
				flag = false;
			}
		}
		return 0;
	}
	
	public int printBiTreeByInOrder(BiNode node){
		if(node == null){
			return -1;
		}
		printBiTreeByPreOrder(node.leftChild);
		System.out.print(node.data);
		printBiTreeByPreOrder(node.rightChild);
		return 0;
	}
	
	public int printBiTreeByPreOrderNoRecursion(BiNode node){
		Stack<BiNode> stack = new Stack<BiNode>();
		BiNode pointer = null;
		stack.push(node);
		while(!stack.isEmpty()){
			pointer = stack.pop();
			System.out.print(pointer.data);
			if(pointer.rightChild != null){
				stack.push(pointer.rightChild);
			}
			if(pointer.leftChild != null){
				stack.push(pointer.leftChild);
			}
		}
		return 0;
	}
	
	public int printBiTreeByPreOrder(BiNode node){
		if(node == null)
			return -1;
		System.out.println(node.data);
		printBiTreeByPreOrder(node.leftChild);
		printBiTreeByPreOrder(node.rightChild);
		return 0;
	}
	
	
	public static void main(String[] args){
		BiTree tree = new BiTree();
		tree.createBiTreeByPreOrderNoRecursion(tree.getRoot());
		System.out.println(tree.isBalance(tree.getRoot()));
		System.out.println(tree.getWidth(tree.getRoot()));
		System.out.println(tree.getHeightByNoRecursion(tree.getRoot()));
		//System.out.println(tree.getRoot().data);
		//tree.createBiTreeByPreOrder(tree.getRoot());
		tree.printBiTreeByPostOrderNoRecursion(tree.getRoot());
		tree.changeChild(tree.getRoot());
		tree.printBiTreeByPostOrderNoRecursion(tree.getRoot());
	}
}
