package tree;

import java.util.ArrayList;
import java.util.Scanner;

public class TreeParentDefine {
	protected TreeNodeParentDefine tree[];
	protected int size;
	
	public TreeParentDefine(){
		this.size = 100;
		tree = new TreeNodeParentDefine[size];
		for(int i=0; i<size; i++){
			tree[i] = new TreeNodeParentDefine();
			tree[i].parentNum = -2;
		}
	}
	
	public TreeParentDefine(int size){
		this.size = size;
		tree = new TreeNodeParentDefine[size];
		for(int i=0; i<size; i++){
			tree[i] = new TreeNodeParentDefine();
			tree[i].parentNum = -2;
		}
	}
	
	public int createTree(){
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<size; i++){
			System.out.println("当前节点标号为"+i);
			tree[i].data = sc.nextInt();
			tree[i].parentNum = sc.nextInt();
			if(tree[i].parentNum == -2){
				break;
			}
		}
		sc.close();
		return 0;
	}
	
	public int clearTree(){
		for(int i=0; i<size; i++){
			tree[i].parentNum = -2;
		}
		return 0;
	}
	
	public boolean isEmpty(){
		for(int i=0; i<size; i++){
			if(tree[i].parentNum != -2){
				return false;
			}
		}
		return true;
	}
	
	public int treeDepth(){
		boolean leaf[] = new boolean[size];
		int Depth = 0;
		TreeNodeParentDefine temp;
		for(int i=0; i<size; i++){
			leaf[i] = true;
		}
		for(int i=0; i<size; i++){
			if(tree[i].parentNum >=0 && tree[i].parentNum < size)
				leaf[tree[i].parentNum] = false;
		}
		for(int i=0; i<size; i++){
			if(leaf[i]){
				int currentDepth = 1;
				temp = tree[i];
				while(temp.parentNum != -1){
					currentDepth++;
					temp = tree[temp.parentNum];
				}
				if(currentDepth > Depth){
					Depth = currentDepth;
				}
			}
		}
		return Depth;
	}
	
	public int root(){
		for(int i=0; i<size; i++){
			if(tree[i].parentNum == -1){
				return i; 
			}
		}
		return -1;
	}
	
	public int Assign(int child, int data){
		tree[child].data = data;
		return 0;
	}
	
	public int getParent(int node){
		return tree[node].parentNum;
	}
	
	public ArrayList<Integer> getChild(int parent){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<size; i++){
			if(tree[i].parentNum == parent){
				list.add(i);
			}
		}
		return list;
	}
	
	public int insertChild(int parent, int data){
		for(int i=0; i<size; i++){
			if(tree[i].parentNum == -2){
				tree[i].data = data;  
				tree[i].parentNum = parent;
				break;
			}
		}
		return 0;
	}
	
	public int deleteChild(int parent){
		for(int i=0; i<size; i++){
			if(tree[i].parentNum == parent){
				tree[i].parentNum = -2;
			}
		}
		return 0;
	}
	
	public static void main(String[] args){
		TreeParentDefine tree = new TreeParentDefine(5);
		tree.createTree();
		System.out.println("深度："+tree.treeDepth());
		
	}
}
