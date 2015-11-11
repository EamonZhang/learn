package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ReDupl {
public static void main(String[] args) {
	List myList = new ArrayList();  
    myList.add(1);  
    myList.add(2);  
    myList.add(1);  
    myList.add(3);  
    myList.add(4);  
    myList.add(5);  
    myList.add(6);  
    myList.add(5);  

    myList = new ArrayList(new HashSet(myList));  
    Iterator it = myList.iterator();  
    while (it.hasNext()) {  
        System.out.println(""+it.next());  
    }  
}
}
