package search;

public class BinarySearch {
	
	public static int BinSearch(int[] arr, int key){
		int len = arr.length;
		int start = 0;
		int end = len-1;
		while(true){
			
			int mid = (end+start)/2;
			if(arr[mid] < key){
				start = mid+1;
			}
			else if(arr[mid] > key){
				end = mid-1;
			}
			else{
				return mid;
			}
			if(start == end && arr[start] != key){
				return -1;
			}
		}
	}
	
	public static void main(String[] args){
		int[] arr = {1,2,3,5,6,7,8,10};
		System.out.println(BinSearch(arr,11));
	}
}
