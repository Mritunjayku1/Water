import java.util.*;
public class MyHashMap{
	
	public static void main(String args[]){
		List<String> l1 = new ArrayList<String>();
		l1.add("apple");
		l1.add("zebra");
		l1.add("ball");
		l1.add("cat");
		Comparator comp = new Comparator(){
			public int compare(Object obj1, Object obj2){
				return obj2.toString().compareTo(obj1.toString());
			}
		};
		System.out.println("before comp: "+l1);
		Collections.sort(l1,comp);
		System.out.println("after comp: "+l1);
	}
	
}
