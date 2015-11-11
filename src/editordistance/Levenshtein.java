package editordistance;

import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
/** 
 * @author shao 
 * 
 */  
public class Levenshtein {  
  
    /** 
     * @param str1 待比较字符串1 
     * @param str2 待比较字符串2 
     * @return 编辑距离 
     * @description 计算编辑距离 
     */  
    public static int getDistance(String str1, String str2){  
        int len1 = str1.length();  
        int len2 = str2.length();  
        if(len1==0)  
            return len2;  
        else if(len2==0)  
            return len1;  
          
        int[][] disM = new int[len1+1][len2+1];  
        for(int i=0;i<=len2;++i)  
            disM[0][i] = i;  
        for(int j=0;j<=len1;++j)  
            disM[j][0] = j;  
        for(int i=1;i<=len1;++i)  
            for(int j=1;j<=len2;++j)  
            {  
                int top = disM[i-1][j]+1;  
                int left = disM[i][j-1]+1;  
                int lt = str1.charAt(i-1)==str2.charAt(j-1)?disM[i-1][j-1]:disM[i-1][j-1]+1;  
                  
                disM[i][j] = top<left?(top<lt?top:lt):(left<lt?left:lt);                 
            }  
        return disM[len1][len2];  
    }  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        String str1 = null;  
        String str2 = null;  
        System.out.println("str1:");  
        try {  
            str1 = br.readLine();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        System.out.println("str2:");  
        try {  
            str2 = br.readLine();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }         
        System.out.println(getDistance(str1,str2));  
    }  
  
} 