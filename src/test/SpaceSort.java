package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpaceSort {
	public static int length = 10000000;
	
	public static void main(String[] args) {
		int array[] = new int[length];
		int old[] = new int[length];
		int count = 0;
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		while(count < length){
			int value = (int)(Math.random()*10*length);
			if(map.get(value) == null){
				array[count++] = value;
				map.put(value, value);
			}
		}
		System.arraycopy(array, 0, old, 0, length);
		System.out.println("开始排序");
		long startTime = System.currentTimeMillis();
		Arrays.sort(array);
		System.out.println(System.currentTimeMillis()-startTime);
		
	   startTime = System.currentTimeMillis();
	  spaceToTime(old);
		System.out.println(System.currentTimeMillis()-startTime);
	}
	
	public static void spaceToTime(int[] array){
		int l = array.length;
		int max = array[0];
		for(int i = 1;i<l;i++){
			if(max < array[i]){
				max = array[i];
			}
		}
		int[] temp = new int[max+1];
		for(int i=0;i<l;i++){
			temp[array[i]] = array[i];
		}
		int j = 0;
		for(int i =0;i< temp.length;i++){
			if(temp[i] != 0){
				array[j++] = temp[i];
			}
		}
		
	}
	
}
