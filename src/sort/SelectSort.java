package sort;

import java.util.Arrays;

public class SelectSort {
	public static void generalSelectSort(int[] arr){
		for(int i=0; i<arr.length; i++){
			int max = i;
			for(int j=i; j<arr.length; j++){
				if(arr[max] < arr[j]){
					max = j;
				}
			}
			if(max != i){
				int temp = arr[max];
				arr[max] = arr[i];
				arr[i] = temp;
			}
		}
	}
	
	public static void siftDown(int[] arr, int i, int k){
		for(int j=2*i+1; j < k; j=j*2){
			if(j+1<=k && arr[j] < arr[j+1]){
				j=j+1;
			}
			if(arr[i] < arr[j]){
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i = j;
			}
		}
	}
	
	public static void heapSort(int[] arr){
		for(int k=arr.length-1; k>1; k--){
			for(int i=(k+1)/2-1; i>-1; i--){
				siftDown(arr,i,k);
			}
			int temp = arr[0];
			arr[0] = arr[k];
			arr[k] = temp;
		}
	}
	
	public static void main(String[] args){
		int[] arr = {23,11,9,25,39,9,5,7,16,28};
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
