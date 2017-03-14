class Sclass
{
    int sem;
    int year;
    String dept;
    Subject[][] timetable=new Subject[6][7];   
    Sclass()
    {
    	for(int i=0;i<6;i++){
    		for(int j=0;j<7;j++){
    			timetable[i][j]=new Subject();
    		}
    	}
		for(int i=4;i<7;i++)
		{
			timetable[5][i].subjectID="X";//Represents classes cannot be alloted.
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
