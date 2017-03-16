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
	public int display()
	{
		int count=0;
		for(int i=0;i<6;i++){
			for(int j=0;j<7;j++){
				if(timetable[i][j].subjectID==null)
				{
					System.out.print("  -   ");
				}
				else if(timetable[i][j].subjectID!="X")
				{
					System.out.print(timetable[i][j].subjectID+" ");
					count++;
				}
			}
			System.out.println();
		}
		System.out.println("No. of subjects:"+count);
		return count;
	}
	private boolean non_teachingday(int day){
		for(int i=0;i<6;i++)
		{
			for(int j=0;j<7;j++)
			{
					if(timetable[i][j].subjectID!=null && timetable[i][j].subjectID!="X")
						break;
				if(j==7)
					return false;
			}
		}
		return true;
	}
	public  int first_period(){
		int day=0;
		for(int i=0;i<6;i++)
		{
			if(timetable[i][0].subjectID!=null && timetable[i][0].subjectID!="X")
				day++;
		}
		return day;	
	}
	private float day_ratio(int day)
	{
		int unassigned=0,assigned=0;
		for(int j=0;j<7;j++)
		{
			if(timetable[day][j].subjectID==null)
				unassigned++;
			else
				assigned++;
		}
		return ((float)unassigned/(assigned+unassigned));
	}
	public int max_day_ratio()
	{
		float max=-1,x;
		int day=6,free_day=-1;
		for(int i=0;i<6;i++)
		{
			x=day_ratio(i);
			if(x>max)
			{
				if(x==1 && free_day==-1)
				{
					free_day=i;
				}
				else
				{	
				     max=x;
				     day=i;
				}
			}
		}
		if(max==0)
			return free_day;
		else
			return day;
	}
	/*public evaluate_heuristic()
	{
		int first_period_weight=10*(this.first_period-3);
		int non_teaching_weight=0;
		int day_ratio_weight=0,x;
		for(int i=0;i<6;i++)
		{
			x=day_ratio(i);
			x=x*x;
			day_ratio_weight+=x;
		}
		day_ratio_weight*=10;
		for(int i=0;i<6;i++)
			if(non_teaching_day(i))
				non_teaching_day=50;
		return first_period_weight+non_teaching_weight+day_ratio_weight
	}*/
}
