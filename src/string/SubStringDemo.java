package string;

import java.util.ArrayList;
import java.util.List;

public class SubStringDemo {

	public static void main(String[] args) {
		List<String> handler = new ArrayList<String>();
		for(int i = 0;i<1000;i++){
//			HugeStr h = new HugeStr();
			ImprovedHugeStr h = new ImprovedHugeStr();
			handler.add(h.getSubString(1, 5));
			if((i % 100) == 0){
				   Runtime   r   =   Runtime.getRuntime();   
				  float   freeMemory   =   (float)   r.freeMemory();   
				  float   totalMemory   =   (float)   r.totalMemory();  
				  System.out.println((int)freeMemory+"	"+(int)totalMemory);
			}
		}
	}
	static class HugeStr{
		private String str = new String(new char[100000000]);
		public String getSubString(int beginIndex,int endIndex){
			return str.substring(beginIndex, endIndex);
		}
	}
	static class ImprovedHugeStr{
		private String str = new String(new char[100000000]);
		public String getSubString(int beginIndex,int endIndex){
			return new String(str.substring(beginIndex, endIndex));
		}
	}
}
