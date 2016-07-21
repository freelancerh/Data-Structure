package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Graph_Matrix {
	protected int[][] graph;
	protected String[] vertex;
	protected int vertexNum;
	protected int edgeNum;
	protected boolean[] visited;
	
	
	public Graph_Matrix(){
		graph = new int[10][10];
		vertexNum = 10;
		vertex = new String[vertexNum];
		visited = new boolean[vertexNum];
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<vertexNum; i++){
			vertex[i] = sc.nextLine();
		}
	}
	
	public Graph_Matrix(int size){
		graph = new int[size][size];
		vertexNum = size;
		vertex = new String[vertexNum];
		visited = new boolean[vertexNum];
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<vertexNum; i++){
			vertex[i] = sc.nextLine();
		}
	}
	
	public int createGraph(int type){
		Scanner sc = new Scanner(System.in);
		edgeNum = sc.nextInt();
		for(int i=0; i<edgeNum; i++){
			int start = sc.nextInt();
			int end = sc.nextInt();
			graph[start][end] = 1;
			if(type == 0){
				graph[end][start] = 1;
			}
		}
		return 0;
	}
	
	public int createNet(int type){
		Scanner sc = new Scanner(System.in);
		edgeNum = sc.nextInt();
		int start;
		int end;
		int weight;
		for(int i=0; i<edgeNum; i++){
			start = sc.nextInt();
			end = sc.nextInt();
			weight = sc.nextInt();
			graph[start][end] = weight;
			if(type == 0){
				graph[end][start] = weight;
			}
		}
		return 0;
	}
	
	public int prim(){
		ArrayList<Integer> v = new ArrayList<Integer>();
		ArrayList<Integer> u = new ArrayList<Integer>();
		int[][] primGraph = new int[vertexNum][vertexNum];
		if(vertexNum < 1){
			return -1;
		}
		v.add(0);
		for(int i=1; i<vertexNum; i++){
			u.add(i);
		}
		while(!u.isEmpty()){
			int minWeight = 0;
			int vertexV = 0;
			int vertexU = 0;
			boolean flag = false;	//µÚÒ»¸öminWeight
			for(int i=0; i<v.size(); i++){
				for(int j=0; j<u.size(); j++){
					int numV = v.get(i);
					int numU = u.get(j);
					if(graph[numV][numU] > 0){
						if(!flag){
							minWeight = graph[numV][numU];
							vertexV = i;
							vertexU = j;
							flag = true;
						}
						else if(minWeight > graph[numV][numU]){
							vertexV = i;
							vertexU = j;
							minWeight = graph[numV][numU];
						}
					}
				}
			}
			primGraph[v.get(vertexV)][u.get(vertexU)] = minWeight;
			v.add(u.get(vertexU));
			u.remove(vertexU);
		}
		for(int i=0; i<vertexNum; i++){
			for(int j=0; j<vertexNum; j++){
				System.out.print(primGraph[i][j]);
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
		System.out.print(vertex[k]+" ");
		visited[k] = true;
		for(int i=0; i<vertexNum; i++){
			if(graph[k][i] > 0 && !visited[i]){
				DFStravel(i);
			}
		}
		return 0;
	}
	
	public int BFS(){
		for(int i=0; i<vertexNum; i++){
			visited[i] = false;
		}
		for(int i=0; i<vertexNum; i++){
			if(!visited[i]){
				BFStravel(i);
			}
		}
		return 0;
	}
	
	public int BFStravel(int k){
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(k);
		visited[k] = true;
		while(!queue.isEmpty()){
			int temp = queue.poll();
			System.out.print(vertex[temp] + " ");
			for(int i=0; i<vertexNum; i++){
				if(graph[temp][i] > 0 && !visited[i]){
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
		return 0;
	}

	public void dijkstra(){
		int[] minDistance = new int[vertexNum];
		int[] pre = new int [vertexNum];
		boolean[] isMin = new boolean [vertexNum];
		for(int i=0; i<vertexNum; i++){
			if(graph[0][i] == 0){
				 minDistance[i] = 10000;
				 pre[i] = -1;
			}
			else{
				minDistance[i] = graph[0][i];
				pre[i] = 0;
			}
		}
		isMin[0] = true;
		while(true){
			int minFlag =0;
			int tempMinDis=0;
			boolean flag = true;
			for(int i=0; i<vertexNum; i++){
				if(!isMin[i]){
					if(flag){
						minFlag = i;
						tempMinDis = minDistance[i];
						flag = false;
					}
					else{
						if(tempMinDis > minDistance[i]){
							tempMinDis = minDistance[i];
							minFlag = i;
						}
					}
				}
			}
			
			isMin[minFlag] = true;
			for(int i=0; i<vertexNum; i++){
				if(!isMin[i] && graph[minFlag][i]>0){
					if(minDistance[i] > minDistance[minFlag]+graph[minFlag][i]){
						minDistance[i] =  minDistance[minFlag]+graph[minFlag][i];
						pre[i] = minFlag;
					}
				}
			}
			for(int i=0; i<vertexNum; i++){
				System.out.print(minDistance[i]+" ");
			}
			boolean breakFlag = false;
			for(int i=0; i<vertexNum; i++){
				if(isMin[i] == false){
					break;
				}
				if(i == vertexNum-1){
					breakFlag = true;
				}
			}
			if(breakFlag){
				break;
			}
		}
		for(int i=0; i<vertexNum; i++){
			System.out.println(minDistance[i]);
		}
		
	}
	 
	
	public void floyd(){
		int[][] tempGraph = new int[vertexNum][vertexNum];
		int[][] pre = new int[vertexNum][vertexNum];
		for(int i=0; i<vertexNum; i++){
			for(int j=0; j<vertexNum; j++){
				tempGraph[i][j] = graph[i][j];
				if(graph[i][j] > 0){
					pre[i][j] = i;
				}
				else{
					pre[i][j] = -1;
				}
			}
		}
		for(int k=0; k<vertexNum; k++){
			for(int i=0; i<vertexNum; i++){
				if(tempGraph[i][k] > 0)
				for(int j=0; j<vertexNum; j++){
					if(tempGraph[k][j]>0 ){
						if(tempGraph[i][j] > tempGraph[i][k]+tempGraph[k][j] || tempGraph[i][j] == 0){
							tempGraph[i][j] = tempGraph[i][k] + tempGraph[k][j];
							pre[i][j] = k;
						}
						
					}
				}
			}
		}
		System.out.println();
		for(int i=0; i<vertexNum; i++){
			for(int j=0; j<vertexNum; j++){
				System.out.print(tempGraph[i][j]+" ");
			}
			System.out.println();
		}
		
		for(int i=0; i<vertexNum; i++){
			for(int j=0; j<vertexNum; j++){
				System.out.print(pre[i][j]);
			}
			System.out.println();
		}
	}
	
	public void AOV(){
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] in = new int[vertexNum];
		for(int i=0; i<vertexNum; i++){
			for(int j=0; j<vertexNum; j++){
				if(graph[i][j] > 0){
					in[j]++;
				}
			}
		}
		for(int i=0; i<vertexNum; i++){
			if(in[i] == 0){
				queue.offer(i);
			}
		}
		while(!queue.isEmpty()){
			int pointer = queue.poll();
			System.out.print("±àºÅ"+(pointer+1)+" "+vertex[pointer]+" ");
			for(int i=0; i<vertexNum; i++){
				if(graph[pointer][i] > 0){
					in[i]--;
					if(in[i] == 0){
						queue.offer(i);
					}
				}
			}
		}	
	}
	
	public void AOE(){
		Queue<Integer> inQueue = new LinkedList();
		Queue<Integer> outQueue = new LinkedList();
		int[] in = new int[vertexNum];
		int[] out = new int[vertexNum];
		int[] early = new int[vertexNum];
		int[] last = new int[vertexNum];
		for(int i=0; i<vertexNum; i++){
			for(int j=0; j<vertexNum; j++){
				if(graph[i][j] > 0){
					in[j]++;
					out[i]++;
				}
			}
		}
		
		for(int i=0; i<vertexNum; i++){
			if(in[i] == 0){
				inQueue.offer(i);
			}
			if(out[i] == 0){
				outQueue.offer(i);
			}
		}
		
		while(!inQueue.isEmpty()){
			int pointer = inQueue.poll();
			boolean flag = true;
			int max = 0;
			for(int i=0; i<vertexNum; i++){
				if(graph[i][pointer] > 0){
					if(flag){
						max = early[i] + graph[i][pointer];
						flag = false;
					}
					else{
						if(early[i] + graph[i][pointer] > max){
							max = early[i] + graph[i][pointer];
						}
						
					}
					
				}
				if(graph[pointer][i] > 0){
					in[i]--;
					if(in[i] == 0){
						inQueue.offer(i);
					}
				}
			}
			early[pointer] = max;
		}
		
		for(int i=0; i<vertexNum; i++){
			if(out[i] == 0){
				last[i] = early[i];
			}
		}
		
		while(!outQueue.isEmpty()){
			int pointer = outQueue.poll();
			boolean flag = true;
			int max = last[pointer];
			for(int i=0; i<vertexNum; i++){
				if(graph[pointer][i] > 0){
					if(flag){
						max = last[i] - graph[pointer][i];
						flag = false;
					}
					else{
						if(last[i] - graph[pointer][i] > max){
							max = last[i] - graph[pointer][i];;
						}
					}
					
				}
				if(graph[i][pointer] > 0){
					out[i]--;
					if(out[i] == 0){
						outQueue.offer(i);
					}
				}
			}
			last[pointer] = max;
		}
		
		for(int i=0; i<vertexNum; i++){
			if(early[i] == last[i]){
				System.out.print("±àºÅ"+(i+1)+" "+vertex[i]+" ");
			}
		}
		System.out.println();
		for(int i=0; i<vertexNum; i++){
			System.out.print(early[i] + " ");
		}
		for(int i=0; i<vertexNum; i++){
			System.out.print(last[i] + " ");
		}
		
	}
	
	public static void main(String[] args){
		Graph_Matrix graph = new Graph_Matrix(9);
		//graph.createGraph(1);
		graph.createNet(1);
		System.out.println(graph.vertexNum+" "+graph.edgeNum);
		graph.DFS();
		graph.BFS();
		System.out.println();
		graph.AOE();
		//graph.floyd();
		//graph.prim();
		//graph.dijkstra();
	}
}
