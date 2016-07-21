package tree;

import java.util.Scanner;

public class TreeChildrenDefine {
	TreeNodeChildrenDefine tree[];
	protected int size;
	TreeChildrenDefine(){
		size = 10;
		tree = new TreeNodeChildrenDefine[10];
	}
	
	TreeChildrenDefine(int size){
		this.size = size;
		tree = new TreeNodeChildrenDefine[size];
	}
	
	public int createTree(){
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<size; i++){
			TreeNodeChildrenDefine node = new TreeNodeChildrenDefine();
			node.data = sc.nextInt();
			node.nextChildren = null;
			tree[i] = node;
		}
		for(int i=0; i<size; i++){
			System.out.println("请输入节点"+i+1+"的子节点编号,按-1结束");
			while(true){
				int data = sc.nextInt();
				if(data == -1){
					break;
				}
				TreeNodeChildrenDefine next = new TreeNodeChildrenDefine();
				next.data = data;
				next.nextChildren = null;
				if(tree[i].nextChildren == null){
					tree[i].nextChildren = next;
				}
				else{
					next.nextChildren = tree[i].nextChildren;
					tree[i].nextChildren = next;
				}
			}
		}
		return 0;
	}
	
	public int printTree(){
		TreeNodeChildrenDefine pointer;
		for(int i=0; i<size; i++){
			pointer = tree[i].nextChildren;
			System.out.println("当前节点为："+(i+1)+"其值为："+tree[i].data);
			System.out.print("其子节点为：");
			while(pointer != null){
				System.out.print(pointer.data);
				pointer = pointer.nextChildren;
			}
		}
		return 0;
	}
	
	public static void main(String[] args){
		TreeChildrenDefine tree = new TreeChildrenDefine();
		tree.createTree();
		tree.printTree();
	}
	
}
