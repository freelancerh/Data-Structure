package tree;

import java.util.Scanner;
import java.util.Stack;

public class SortedBiTree {
	protected SortedBiNode root;
	protected int nodeNum;
	
	public int  CreateSortedBiTree(){
		Scanner sc = new Scanner(System.in);
		SortedBiNode pointer = null;
		System.out.println("输入节点数");
		nodeNum = sc.nextInt();
		System.out.println("按顺序输入节点值");
		root = new SortedBiNode();
		root.data = sc.nextInt();
		for(int i=1; i<nodeNum; i++){
			pointer = new SortedBiNode();
			pointer.data = sc.nextInt();
			insertNodeByNoRecursion(root,pointer);
		}
		return 0;
	}
	
	public int insertNode(SortedBiNode topNode, SortedBiNode node){
		if(node.data > topNode.data){
			if(topNode.rightChildren == null){
				topNode.rightChildren = node;
			}
			else{
				insertNode(topNode.rightChildren, node);
			}
		}
		else if(node.data <= topNode.data){
			if(topNode.leftChildren == null){
				topNode.leftChildren = node;
			}
			else{
				insertNode(topNode.leftChildren, node);
			}
		}
		return 0;
	}
	
	public int insertNodeByNoRecursion(SortedBiNode topNode, SortedBiNode node){
		SortedBiNode pointer = topNode;
		while(true){
			if(node.data > pointer.data){
				if(pointer.rightChildren == null){
					pointer.rightChildren = node;
					break;
				}
				else{
					pointer = pointer.rightChildren;
				}
			}
			else{
				if(pointer.leftChildren == null){
					pointer.leftChildren = node;
					break;
				}
				else{
					pointer = pointer.leftChildren;
				}
			}
		}
		return 0;
	}
	
	public int deleteNode(int data){
		SortedBiNode pre = null;
		SortedBiNode pointer = root;
		while(true){
			if(pointer.data == data){
				break;
			}
			else if(pointer.data < data){
				pre = pointer;
				pointer = pointer.rightChildren;
			}
			else if(pointer.data > data){
				pre = pointer;
				pointer = pointer.leftChildren;
			}
		}
		if(pre == null){
			root = null;
		}
		else if(pointer.rightChildren == null && pointer.leftChildren == null){
			if(pre.leftChildren == pointer){
				pre.leftChildren = null;
			}
			else if(pre.rightChildren == pointer){
				pre.rightChildren = null;
			}
		}
		else if(pointer.rightChildren != null && pointer.leftChildren == null){
			if(pre.leftChildren == pointer){
				pre.leftChildren = pointer.rightChildren;
			}
			else if(pre.rightChildren == pointer){
				pre.rightChildren = pointer.rightChildren;
			}
		}
		else if(pointer.leftChildren != null && pointer.rightChildren == null){
			if(pre.leftChildren == pointer){
				pre.leftChildren = pointer.leftChildren;
			}
			else if(pre.rightChildren == pointer){
				pre.rightChildren = pointer.leftChildren;
			}
		}
		else{
			SortedBiNode temp = pointer.leftChildren;
			while(temp.rightChildren != null){
				temp = temp.rightChildren;
			}
			temp.rightChildren = pointer.rightChildren;
			if(pre.leftChildren == pointer){
				pre.leftChildren = pointer.leftChildren;
			}
			else if(pre.rightChildren == pointer){
				pre.rightChildren = pointer.leftChildren;
			}
		}
		return 0;
	}
	
	
	public int deleteNodeNew(int data){
		SortedBiNode pre = null;
		SortedBiNode pointer = root;
		while(true){
			if(pointer.data == data){
				break;
			}
			else if(pointer.data < data){
				pre = pointer;
				pointer = pointer.rightChildren;
			}
			else if(pointer.data > data){
				pre = pointer;
				pointer = pointer.leftChildren;
			}
		}
		if(pre == null){
			root = null;
		}
		else if(pointer.rightChildren == null && pointer.leftChildren == null){
			if(pre.leftChildren == pointer){
				pre.leftChildren = null;
			}
			else if(pre.rightChildren == pointer){
				pre.rightChildren = null;
			}
		}
		else if(pointer.rightChildren != null && pointer.leftChildren == null){
			if(pre.leftChildren == pointer){
				pre.leftChildren = pointer.rightChildren;
			}
			else if(pre.rightChildren == pointer){
				pre.rightChildren = pointer.rightChildren;
			}
		}
		else if(pointer.leftChildren != null && pointer.rightChildren == null){
			if(pre.leftChildren == pointer){
				pre.leftChildren = pointer.leftChildren;
			}
			else if(pre.rightChildren == pointer){
				pre.rightChildren = pointer.leftChildren;
			}
		}
		else{
			SortedBiNode tempPre = pointer;
			SortedBiNode temp = pointer.leftChildren;
			while(temp.rightChildren != null){
				pre = temp;
				temp = temp.rightChildren;
			}
			pre.rightChildren = temp.leftChildren;
			pointer.data = temp.data;
		}
		return 0;
	}
	
	public SortedBiNode findNode(int data){
		SortedBiNode pointer = root;
		while(pointer != null){
			if(pointer.data == data){
				return pointer;
			}
			else if(pointer.data > data){
				pointer = pointer.leftChildren;
			}
			else if(pointer.data < data){
				pointer = pointer.rightChildren;
			}
		}
		return null;
	}
	
	public int printSortedBiTreeByIn(){
		Stack<SortedBiNode> stack = new Stack<SortedBiNode>();
		SortedBiNode pointer = null;
		boolean flag = false;
		if(root != null)
			stack.push(root);
		while(!stack.isEmpty()){
			while(!flag && stack.peek().leftChildren != null){
				stack.push(stack.peek().leftChildren);
			}
			pointer = stack.pop();
			System.out.print(pointer.data);
			if(pointer.rightChildren != null){
				flag = false;
				stack.push(pointer.rightChildren);
			}
			else{
				flag = true;
			}
		}
		return 0;
	}
	
	public static void main(String[] args){
		SortedBiTree tree = new SortedBiTree();
		tree.CreateSortedBiTree();
		tree.printSortedBiTreeByIn();
		System.out.println();
		tree.deleteNode(5);
		tree.printSortedBiTreeByIn();
		SortedBiNode temp = tree.findNode(4);
		if(temp != null){
			System.out.println(temp.data);
		}
	}
}
