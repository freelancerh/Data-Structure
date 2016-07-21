package sort;

import java.util.Arrays;

public class ChangeSort {
	public static int partition(int start, int end,int[] arr){
		int pivot = arr[start];
		while(start < end){
			while(end>start && arr[end]>pivot){
				end--;
			}
			if(end > start){
				arr[start] = arr[end];
				start++;
			}
			
			while(end>start && arr[start]<pivot){
				start++;
			}
			if(end > start){
				arr[end] = arr[start];
				end--;
			}
		}
		arr[start] = pivot;
		return start;
	}
	
	public static void quickSort(int[] arr, int start, int end){
		int i = partition(start, end, arr);
		if(start < end){
			quickSort(arr,start, i-1);
			quickSort(arr, i+1, end);
		}
	}
	
	public static void main(String[]args){
		int[] arr = {23,11,9,25,39,9,16,5,50};
		System.out.println(Arrays.toString(arr));
		quickSort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
}
