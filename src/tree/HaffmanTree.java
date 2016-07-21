package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HaffmanTree {
	protected HaffmanNode root;
	
	public int createHaffmanTree(){
		int n;
		ArrayList<HaffmanNode> list = new ArrayList<HaffmanNode>();
		HaffmanNode pointer = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入节点数");
		n = sc.nextInt();
		for(int i=0; i<n; i++){
			pointer = new HaffmanNode();
			System.out.println("请输入值");
			pointer.weight = sc.nextInt();
			pointer.data = String.valueOf(pointer.weight);
			//pointer.data = sc.nextLine();
			//System.out.println("请输入权");
			
			list.add(pointer);
		}
		while(list.size() != 1){
			int first = 0;
			int second = 0;
			int min;
			if(list.get(0).weight < list.get(1).weight){
				first = 0;
				second = 1;
				min = list.get(0).weight;
			}
			else{
				first = 1;
				second = 0;
				min = list.get(1).weight;
			} 
			for(int i=2; i<list.size(); i++){
				if(min > list.get(i).weight){
					second = first;
					first = i;
					min = list.get(i).weight;
				}
				else if(list.get(second).weight > list.get(i).weight && min < list.get(i).weight){
					second = i;
				}
			}
			System.out.println("first:"+first+" second:"+second);
			pointer = new HaffmanNode();
			pointer.leftChildren = list.get(first);
			pointer.rightChildren = list.get(second);
			pointer.weight = pointer.leftChildren.weight + pointer.rightChildren.weight;
			list.remove(first);
			if(first < second){
				list.remove(second-1);
			}
			else{
				list.remove(second);
			}
			list.add(pointer);
		}
		root = list.get(0);
		return 0;
	}
	
	public int printHaffmanTree(){
		Queue<HaffmanNode> queue = new LinkedList<HaffmanNode>();
		HaffmanNode pointer = null;
		queue.offer(root);
		while(!queue.isEmpty()){
			pointer = queue.poll();
			System.out.print(pointer.weight+" ");
			if(pointer.leftChildren != null)
				queue.offer(pointer.leftChildren);
			if(pointer.rightChildren != null)
				queue.offer(pointer.rightChildren);
		}
		return 0;
	}
	
	public HaffmanNode getRoot(){
		return root;
	}
	
	public int printHaffmanCode(HaffmanNode root, String path){
		if(root.leftChildren == null && root.rightChildren == null){
			System.out.println("值为："+root.data+"路径为："+path);
		}
		if(root.leftChildren != null){
			path += "0";
			printHaffmanCode(root.leftChildren, path);
		}
		if(root.rightChildren != null){
			if(root.leftChildren != null){
				path = path.substring(0, path.length()-1)+"1";
			}
			else{
				path = path + "1";
			}
			printHaffmanCode(root.rightChildren, path);
		}
		return 0;
	}
	
	public static void main(String[] args){
		HaffmanTree tree  = new HaffmanTree();
		tree.createHaffmanTree();
		System.out.println(tree.getRoot().weight);
		
		tree.printHaffmanTree();
		System.out.println();
		tree.printHaffmanCode(tree.getRoot(), "");
	}
}
