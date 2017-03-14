import java.util.*;
public class Lab
{
	String labName;
	Subject[][] timetable=new Subject[6][7];
	LinkedList<Subject> labQ =new LinkedList<Subject>();
	int labmax=10;	
	Lab() 
	{
		for(int i=0;i<6;i++)
		{
    		for(int j=0;j<7;j++)
    		{
    			timetable[i][j]=new Subject();
    		}
    	}
		for(int i=4;i<7;i++)
			timetable[5][i].subjectID="X";
	}
	public void displayQ()
	{
		for(Subject s:labQ){
				System.out.println("subjectID: "+s.subjectID);
			}
	}
	public void display()
	{
		for(int i=0;i<6;i++){
			for(int j=0;j<7;j++){
				System.out.print(timetable[i][j].subjectID+" ");
			}
			System.out.println();
		}
	}
}
