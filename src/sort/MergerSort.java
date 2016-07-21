package sort;

import java.util.Arrays;

public class MergerSort {
	public static void mergerSort(int[] arr, int start, int end){
		if(start >= end){
			return ;
		}
		int[] temp = new int[end-start+1];
		int mid = (start+end)/2;
		mergerSort(arr, start, mid);
		mergerSort(arr, mid+1, end);
		int i=start;
		int j=mid+1;
		int count = 0;
		while(i<mid+1 && j<end+1){
			if(arr[i] < arr[j]){
				temp[count++]=arr[i++];
			}
			else{
				temp[count++]=arr[j++];
			}
		}
		if(i == mid+1){
			for(; j<end+1; j++){
				temp[count++] = arr[j];
			}
		}
		else{
			for(; i<mid+1; i++){
				temp[count++] = arr[i];
			}
		}
		for(int k=0; k<temp.length; k++){
			arr[start+k] = temp[k];
		}
	}
	
	public static void main(String[] args){
		int[] arr = {23,11,9,25,39,9,5,7,16,28};
		mergerSort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
}
