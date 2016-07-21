import java.util.Scanner;


public class 三六零题2 {
	private static int find(int n, int b){
		if(n<0 || b<0 || b>n)
			return -1;
		int middle = (int) Math.ceil(n/2);
		if(b>middle || b==middle){
			return b-1;
		}
		else{
			return b+1;
		}
	}
	
	public static void main(String[] args){
		int n;
		int b;
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()){
			n = sc.nextInt();
			b = sc.nextInt();
			System.out.println(find(n, b));
		}
	}
}
