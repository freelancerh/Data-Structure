package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Graph_List {
	protected VertexNode[] vertex;
	protected int vertexNum;
	protected int edgeNum;
	protected boolean[] visited;
	
	public Graph_List(){
		vertex = new VertexNode[10];
		vertexNum = 10;
		visited = new boolean[vertexNum];
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<vertexNum; i++){
			vertex[i] = new VertexNode();
			vertex[i].data = sc.nextLine();
		}
	}
	
	public Graph_List(int size){
		vertex = new VertexNode[size];
		vertexNum = size;
		visited = new boolean[vertexNum];
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<vertexNum; i++){
			vertex[i] = new VertexNode();
			vertex[i].data = sc.nextLine();
		}
	}
	
	public int createGraph(int type){
		Scanner sc = new Scanner(System.in);
		edgeNum = sc.nextInt();
		for(int i=0; i<edgeNum; i++){
			int first = sc.nextInt();
			int end = sc.nextInt();
			if(type == 0){
				EdgeNode node = new EdgeNode();
				node.vertexNo = end;
				node.next = vertex[first].first;
				vertex[first].first = node;
				EdgeNode inversenode = new EdgeNode();
				inversenode.vertexNo = first;
				inversenode.next = vertex[end].first;
				vertex[end].first = inversenode;
			}
			else{
				EdgeNode node = new EdgeNode();
				node.vertexNo = end;
				node.next = vertex[first].first;
				vertex[first].first = node;
			}
		}
		return 0;
	}
	
	public int createNet(int type){
		Scanner sc = new Scanner(System.in);
		edgeNum =sc.nextInt();
		for(int i=0; i<edgeNum; i++){
			int start = sc.nextInt();
			int end = sc.nextInt();
			int weight = sc.nextInt();
			EdgeNode node = new EdgeNode();
			node.vertexNo = end;
			node.weight = weight;
			node.next = vertex[start].first;
			vertex[start].first = node;
			if(type == 0){
				EdgeNode inverseNode = new EdgeNode();
				inverseNode.vertexNo = start;
				inverseNode.weight = weight;
				inverseNode.next = vertex[end].first;
				vertex[end].first = inverseNode;
			}
		}
		return 0;
	}
	
	public int prim(){
		if(vertexNum < 1)
			return -1;
		VertexNode[] primVertex = new VertexNode[vertexNum];
		for(int i=0; i<vertexNum; i++){
			primVertex[i] = new VertexNode();
			primVertex[i].data = vertex[i].data;
		}
		ArrayList<Integer> v = new ArrayList<Integer>();
		ArrayList<Integer> u = new ArrayList<Integer>();
		v.add(0);
		for(int i=1; i<vertexNum; i++){
			u.add(i);
		}
		while(!u.isEmpty()){
			int minWeight = 0;
			int vertexV = 0;
			int vertexU = 0;
			boolean flag = false;
			for(int i=0; i<v.size(); i++){
				for(int j=0; j<u.size(); j++){
					int numV = v.get(i);
					int numU = u.get(j);
					EdgeNode pointer = vertex[numV].first;
					while(pointer != null){
						if(pointer.vertexNo == numU){
							if(!flag){
								minWeight = pointer.weight;
								vertexV = i;
								vertexU = j;
								flag = true;
							}
							else if(minWeight > pointer.weight){
								minWeight = pointer.weight;
								vertexV = i;
								vertexU = j;
							}
						}
						pointer = pointer.next;
					}
				}
			}
			EdgeNode node = new EdgeNode();
			node.weight = minWeight;
			node.vertexNo = u.get(vertexU);
			node.next = primVertex[v.get(vertexV)].first;
			primVertex[v.get(vertexV)].first = node;
			v.add(u.get(vertexU));
			u.remove(vertexU);
		}
		for(int i=0; i<vertexNum; i++){
			EdgeNode pointer = primVertex[i].first;
			System.out.print("节点"+primVertex[i].data+"的邻接点为： ");
			while(pointer != null){
				System.out.print(pointer.vertexNo + "权值为："+pointer.weight+" ");
				pointer = pointer.next;
			}
			System.out.println();
		}
		return 0;
	}
	
	public int DFS(){
		for(int i=0; i<vertexNum; i++){
			visited[i] = false;
		}
		for(int i=0; i<vertexNum; i++){
			if(!visited[i]){
				DFStravel(i);
			}
		}
		return 0;
	}
	
	public int DFStravel(int k){
		System.out.print(vertex[k].data+" ");
		EdgeNode pointer = vertex[k].first;
		visited[k] = true;
		while(pointer != null){
			if(!visited[pointer.vertexNo])
				DFStravel(pointer.vertexNo);
			pointer = pointer.next;
		}
		return 0;
	}
	
	public int BFS(){
		for(int i=0; i<vertexNum; i++){
			visited[i] = false;
		}
		for(int i=0; i<vertexNum; i++){
			if(!visited[i])
			   BFStravel(i);
		}
		return 0;
	}
	
	public int BFStravel(int k){
		Queue<VertexNode> queue = new LinkedList<VertexNode>();
		queue.offer(vertex[k]);
		visited[k] = true;
		while(!queue.isEmpty()){
			VertexNode currentVertex = queue.poll();
			System.out.print(currentVertex.data + " ");
			EdgeNode pointer = currentVertex.first;
			while(pointer != null ){
				if(!visited[pointer.vertexNo]){
					queue.offer(vertex[pointer.vertexNo]);
					visited[pointer.vertexNo] = true;
				}
				pointer = pointer.next;
			}
		}
		return 0;
	}
	
	public static void main(String[] args){
		Graph_List graph = new Graph_List(6);
		//graph.createGraph(0);
		graph.createNet(0);
		graph.DFS();
		System.out.println();
		graph.BFS();
		System.out.println();
		graph.prim();
	}
	
}
