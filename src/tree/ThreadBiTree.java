package tree;

import java.util.Scanner;
import java.util.Stack;

public class ThreadBiTree {
	protected ThreadBiNode root;
	protected ThreadBiNode pre;
	public ThreadBiTree(){
		root = null;
		pre = root;
	}
	
	public ThreadBiNode getRoot(){
		return root;
	}
	
	public ThreadBiNode getParentByPre(ThreadBiNode child){
		Stack<ThreadBiNode> stack = new Stack<ThreadBiNode>();
		stack.push(root);
		ThreadBiNode pointer = null;
		while(!stack.isEmpty()){
			pointer = stack.pop();
			
			if(!pointer.isLeftThread && pointer.leftChildren == child){
				return pointer;
			}
			else if(!pointer.isRightThread && pointer.rightChildren == child){
				return pointer;
			}
			if(pointer.rightChildren != null && !pointer.isRightThread)
				stack.push(pointer.rightChildren);
			if(pointer.leftChildren != null && !pointer.isLeftThread)
				stack.push(pointer.leftChildren);
		}
		return null;
	}
	
	public ThreadBiNode getParent(ThreadBiNode child){
		Stack<ThreadBiNode> stack = new Stack<ThreadBiNode>();
		ThreadBiNode pointer = root;
		stack.push(pointer);
		boolean flag = false;
		ThreadBiNode lastVisited = null;
		while(!stack.isEmpty()){
			while(!flag && stack.peek().leftChildren!=null && !stack.peek().isLeftThread){
				stack.push(stack.peek().leftChildren);
			}
			if(stack.peek().isRightThread || stack.peek().rightChildren == lastVisited){
				pointer = stack.pop();
				if(!pointer.isLeftThread && pointer.leftChildren == child){
					return pointer;
				}
				else if(!pointer.isRightThread && pointer.rightChildren == child){
					return pointer;
				}
				flag = true;
				lastVisited = pointer;
			}
			else if(stack.peek().rightChildren !=null && !stack.peek().isRightThread ){
				stack.push(stack.peek().rightChildren);
				flag = false;
			}
		}
		return null;
	}
	
	public int createThreadBiTree(ThreadBiNode node){
		if(root == null){
			root = new ThreadBiNode();
			node = root;
		}
		Scanner sc = new Scanner(System.in);
		node.data = sc.nextLine();
		if(node.data.equals("#")){
			return -1;
		}                      
		node.leftChildren = new ThreadBiNode();
		createThreadBiTree(node.leftChildren);
		node.rightChildren = new ThreadBiNode();
		createThreadBiTree(node.rightChildren);
		return 0;
	}
	
	public int createThreadBiTreeByPreOrderNoRecursion(ThreadBiNode node){
		Stack<ThreadBiNode> stack = new Stack<ThreadBiNode>();
		Scanner sc = new Scanner(System.in);
		if(root == null){
			root = new ThreadBiNode();
			node = root;
		}
		ThreadBiNode pointer = null;
		stack.push(root);
		while(!stack.isEmpty()){
			pointer = stack.pop();
			pointer.data = sc.nextLine();
			if(!pointer.data.equals("#")){
				pointer.leftChildren = new ThreadBiNode();
				pointer.rightChildren = new ThreadBiNode();
				stack.push(pointer.rightChildren);
				stack.push(pointer.leftChildren);
			}
		}
		return 0;
	}
	
	public int initPre(){
		pre = null;
		return 0;
	}
	
	public int ThreadingBiTreeByPre(ThreadBiNode node){
		if(node == null){
			return -1;
		}
		if(node.leftChildren == null){
			node.leftChildren = pre;
			node.isLeftThread = true;
		}
		if(pre!=null && pre.rightChildren == null){
			pre.rightChildren = node;
			pre.isRightThread = true;
		}
		pre = node;
		if(!node.isLeftThread)
			ThreadingBiTreeByPre(node.leftChildren);
		if(!node.isRightThread)
			ThreadingBiTreeByPre(node.rightChildren);
		return 0;
	}
	
	
	public int ThreadingBiTreeByIn(ThreadBiNode node){
		if(node == null){
			return -1;
		}
		if(!node.isLeftThread);
			ThreadingBiTreeByIn(node.leftChildren);
		if(node.leftChildren == null){
			node.leftChildren = pre;
			node.isLeftThread = true;
		}
		if(pre!=null && pre.rightChildren == null){
			pre.rightChildren = node;
			pre.isRightThread = true;
		}
		pre = node;
		if(!node.isRightThread)
			ThreadingBiTreeByIn(node.rightChildren);
		return 0;
	}
	
	public int ThreadingBiTreeByInNoRecursion(){
		Stack<ThreadBiNode> stack = new Stack<ThreadBiNode>();
		ThreadBiNode pointer = null;
		boolean flag = true;
		stack.push(root);
		while(!stack.isEmpty()){
			while(stack.peek().leftChildren != null && flag){
				stack.push(stack.peek().leftChildren);
			}
			pointer = stack.pop();
			if(pointer.leftChildren == null){
				pointer.isLeftThread = true;
				pointer.leftChildren = pre;
			}
			if(pre!=null && pre.rightChildren == null){
				pre.rightChildren = pointer;
				pre.isRightThread = true;
			}
			pre = pointer;
			if(pointer.rightChildren != null){
				stack.push(pointer.rightChildren);
				flag = true;
			}
			else{
				flag = false;
			}
			
		}
		return 0;
	}
	
	public int ThreadingBiTreeByPost(ThreadBiNode node){
		if(node == null){
			return -1;
		}
		if(!node.isLeftThread){
			ThreadingBiTreeByPost(node.leftChildren);
		}
		if(!node.isRightThread){
			ThreadingBiTreeByPost(node.rightChildren);
		}
		if(node.leftChildren == null){
			node.leftChildren = pre;
			node.isLeftThread = true;
		}
		if(pre!=null && pre.rightChildren==null){
			pre.rightChildren = node;
			pre.isRightThread = true;
		}
		pre = node;
		return 0;
	}
	
	public int ThreadingBiTreeByPostNoRecursion(){
		Stack<ThreadBiNode> stack =  new Stack<ThreadBiNode>();
		ThreadBiNode pointer = null;
		ThreadBiNode preVisited = null;
		boolean flag = true;
		while(!stack.isEmpty()){
			while(stack.peek().leftChildren!=null && flag){
				stack.push(stack.peek().leftChildren);
			}
			if(stack.peek().rightChildren == null || stack.peek().rightChildren == pre){
				pointer = stack.pop();
				if(pointer.leftChildren != null){
					pointer.leftChildren = pre;
					pointer.isLeftThread = true;
				}
				if(pre!=null && pre.rightChildren == null){
					pre.rightChildren = pointer;
					pre.isRightThread = true;
				}
				pre = pointer;
				flag = false;
			}
			else {
				flag = true;
				stack.push(pointer.rightChildren);
			}
		}
		return 0;
	}
	
	public int printThreadingBiTreeByPre(){
		ThreadBiNode pointer = root;
		while(pointer != null){
			System.out.print(pointer.data);
			if(pointer.isRightThread || pointer.isLeftThread && !pointer.isRightThread){
				pointer = pointer.rightChildren;
			}
			else if(!pointer.isLeftThread){
				pointer = pointer.leftChildren;
			}
		}
		return 0;
	}
	
	public int printThreadingBiTreeByIn(){
		
		return 0;
	}
	
	public int printThreadingBiTreeByPost(){
		ThreadBiNode pointer = root;
		while(!pointer.isLeftThread && !pointer.isRightThread){
			while(pointer.leftChildren!=null && !pointer.isLeftThread){
				pointer = pointer.leftChildren;
			}
			if(pointer.rightChildren!=null && !pointer.isRightThread){
				pointer = pointer.rightChildren;
			}
		}
		while(pointer != null){
			System.out.print(pointer.data);
			if(pointer == root)
				break;
			ThreadBiNode parent = getParent(pointer);
			if(parent.leftChildren == pointer){
				if(parent.rightChildren != null && !parent.isRightThread){
					pointer = parent.rightChildren;
					while(!pointer.isLeftThread && !pointer.isRightThread){
						while(pointer.leftChildren!=null && !pointer.isLeftThread){
							pointer = pointer.leftChildren;
						}
						if(pointer.rightChildren!=null && !pointer.isRightThread){
							pointer = pointer.rightChildren;
						}
					}
				}
				else{
					pointer = parent;
				}
			}
			else{
				pointer = parent;
			}
		}
		return 0;
	}
	
	public int printNoThreadingBiTreeByInNoRecursion(){
		Stack<ThreadBiNode> stack = new Stack<ThreadBiNode>();
		ThreadBiNode pointer = null;
		boolean flag = true;
		stack.push(root);
		while(!stack.isEmpty()){
			while(stack.peek().leftChildren!=null && flag){
				stack.push(stack.peek().leftChildren);
			}
			pointer = stack.pop();
			System.out.print(pointer.data);
			if(pointer.rightChildren != null){
				flag = true;
				stack.push(pointer.rightChildren);
			}
			else{
				flag = false;
			}
		}
		return 0;
	}
	
	
	public static void main(String[] args){
		ThreadBiTree tree = new ThreadBiTree();
		tree.createThreadBiTree(tree.getRoot());
		tree.initPre();
		tree.ThreadingBiTreeByPost(tree.getRoot());
		tree.printThreadingBiTreeByPost();
		//tree.printNoThreadingBiTreeByInNoRecursion();
	}
}
