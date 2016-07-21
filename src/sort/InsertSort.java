package sort;

import java.util.Arrays;

public class InsertSort {
	public static void insertSort(int[] arr){
		
		for(int i=1; i<arr.length; i++){
			int temp = arr[i];
			for(int j=i-1; j>-1; j--){
				if(temp > arr[j]){
					arr[j+1] = temp;
					break;
				}
				arr[j+1] = arr[j];
				if(j == 0){
					arr[j] = temp;
				}
			}
		}
	}
	
	public static void shellSort(int[] arr){
		int step = arr.length/2;
		while(step != 0){
		 for(int k=0; k<step; k++){
			 for(int i=step+k; i<arr.length; i=i+step){
					int temp = arr[i];
					for(int j=i-step; j>-1; j=j-step){
						if(temp > arr[j]){
							arr[j+step] = temp;
							break;
						}
						else{
							arr[j+step] = arr[j];
						}
						if(j == k){
							arr[j] = temp;
						}
					}
				}
		 	}
		 step = step/2;
		}
	}
	
	public static void main(String[] args){
		int[] arr = {23,11,9,25,39,9,5,7,16,28};
		System.out.println(Arrays.toString(arr));
		shellSort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
