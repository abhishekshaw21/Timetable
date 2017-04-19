import java.util.*;
public class MyComp implements Comparator<Subject>
{
	public int compare(Subject s1,Subject s2){
		return s1.year-s2.year;
	}
}
