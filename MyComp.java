import java.util.*;
public class MyComp implements Comparator<Subject>
{
	public int compare(Subject s1,Subject s2){
		return s2.year-s1.year;
	}
}
