package test;

public	 class Prime2 {  
		  
	    /** 
	     * @param args 
	     */  
	    public static void main(String[] args) {  
	  
	        int n = 20;  
	  
	        int[] array = new int[n];  
	  
	        for (int i = 2; i < n; i++) {  
	  
	            array[i] = i;  
	  
	        }  
	  
	        for (int i = 2; i < n; i++) {  
	            if (array[i] != 0) {  
	  
	                int j, temp;  
	  
	                temp = array[i];  
	  
	                for (j = 2 * temp; j < n; j = j + temp) {  
	                    array[j] = 0;  
	                }  
	                System.out.println("\n");  
	  
	            }  
	    for (int k : array) {
	    	if(k !=0){
	    		System.out.println(k);
	    	}
		}
	        }  
	  
	    }  
	  
}
