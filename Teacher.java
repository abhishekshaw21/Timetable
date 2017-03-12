import java.util.*;
class Teacher
{
	String name;
	String tID;
	Subject[][] timetable=new Subject[6][7];
	int teaching_hours;
    LinkedList<Subject> subQ =new LinkedList<Subject>();
	Teacher()
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
	public evaluate_heuristic()
	{
		int first_period_weight=1000;
		int non_teaching_weight=500;
		int day_ratio_weight=100;
		
	}
	public void notavailable(int day,int start_period,int end_period)
	{
			for(int i=start_period;i<=end_period;i++)
			{
				timetable[day][i].subjectID="X";	
			}	
	}
	public void displayQ()
	{
		for(Subject s:subQ){
				System.out.println("subjectID:  "+s.subjectID);
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
	public int heuristic_result()
	{
		int non_teaching=non_teaching_day();
		int first_period;
	}
	private boolean non_teachingday(){
		int day=0;
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<7;j++)
			{
					if(timetable[i][j]!=null && timetable[i][j]!="X")
						break;
			}
			if(j==7)
				return false;
		}
		return true;
	}
	private static int first_period(){
		int day=0;
		for(int i=0;i<6;i++)
		{
			if(timetable[i][0]!=null && timetable[i][0]!="X")
				day++;
		}
		return day;	
	}
	static private float day_ratio(int day)
	{
		int unassigned=0,assigned=0;
		for(int j=0;j<7;j++)
		{
			if(timetable[day][j]==null||timetable[day][j]=="X")
				unassigned++;
			else
				assigned++;
		}
		return ((float)unassigned/(assigned+unassigned));
	}
}
