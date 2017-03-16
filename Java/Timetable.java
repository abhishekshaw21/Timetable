import java.util.*;
class Timetable
{
	static final int no_of_lab=5;
	static final int no_of_year=4;
	static final int no_of_dept=2;//CSE->0,IT->1,ECE->2,EE->3
    static final int no_of_subjects=no_of_year*no_of_dept*8;
    static final int no_of_teacher=4*no_of_year*no_of_dept+no_of_dept;
    static int total=0;
    public static boolean labchecker(Lab l1,Teacher t1,Teacher t2,Teacher t3,Sclass s2,int s1,int j)
    {

    	return (l1.timetable[j][s1].subjectID==null && l1.timetable[j][s1+1].subjectID==null && l1.timetable[j][s1+2].subjectID==null &&
			t1.timetable[j][s1].subjectID==null && t1.timetable[j][s1+1].subjectID==null && t1.timetable[j][s1+2].subjectID==null &&
			t2.timetable[j][s1].subjectID==null && t2.timetable[j][s1+1].subjectID==null && t2.timetable[j][s1+2].subjectID==null &&
			t3.timetable[j][s1].subjectID==null && t3.timetable[j][s1+1].subjectID==null && t3.timetable[j][s1+2].subjectID==null &&
			s2.timetable[j][s1].subjectID==null && s2.timetable[j][s1].subjectID==null && s2.timetable[j][s1].subjectID==null);
    }
    public static void main(String args[])throws Exception
	{
		Subject[] s=new Subject[no_of_subjects];
		for(int i=0;i<no_of_subjects;i++)
		{
			s[i]=new Subject();
			if(i%16<8)
			{
				s[i].subjectID="CS"+(((i/8+1)*100)+(i%8)+1);
				s[i].dept="CS";
				s[i].deptcode=0;
				s[i].year=(int)Math.ceil(((double)i/8+1)/2);
				if((i%8)<5)
				{
					s[i].theortical=3;
					s[i].tutorial=1;
					total+=4;
				}
				else
				{
					s[i].practical=3;
					total+=3;
				}
			}
			else
			{
				s[i].subjectID="IT"+((((i-8)/8+1)*100)+(i%8)+1);
				s[i].dept="IT";
				s[i].deptcode=1;
				s[i].year=(int)Math.ceil((((double)i-8)/8+1)/2);
				if((i%8)<5)
				{
					s[i].theortical=3;
					s[i].tutorial=1;
					total+=4;
				}
				else
				{
					s[i].practical=3;
					total+=3;
				}
			}
		}
		Sclass[][] stt=new Sclass[no_of_year][no_of_dept];
		for(int i=0;i<no_of_year;i++)
			for(int j=0;j<no_of_dept;j++)
		{
				stt[i][j]=new Sclass();	
		}
		Teacher[] ttt=new Teacher[no_of_teacher];
		for(int i=0;i<no_of_teacher;i++)
		{
				ttt[i]=new Teacher();
				ttt[i].name="T"+i;
		}	
		Lab[] ltt=new Lab[no_of_lab];
		for(int i=0;i<no_of_lab;i++)
		{
				ltt[i]=new Lab();
				ltt[i].labName="C"+i;
		}
		for(int i=0;i<=5;i++)
		{
			ttt[0].notavailable(i,4,6);	
			ttt[0].teaching_hours=6;
		}
		for(int i=1;i<no_of_teacher/2;i++)
		{
			ttt[i].teaching_hours=14;
		}
		for(int i=no_of_teacher/2;i<no_of_teacher;i++)
		{
			ttt[i].teaching_hours=16;
		}
		for(int day=0;day<no_of_subjects;day++)
		{
			System.out.println(s[day].subjectID+" "+s[day].dept+" "+s[day].year+" "+s[day].theortical+" "+s[day].tutorial+" "+s[day].practical);
		}
		int labref=0;
		do
		{
				for(int i=0;i<no_of_subjects;i++){
					int j;
					do{
						j=(int)Math.floor(Math.random()*no_of_teacher);
					}while(ttt[j].teaching_hours==0);
					    if(ttt[j].teaching_hours>0 && (s[i].theortical >0 || s[i].tutorial>0||s[i].practical>0))
					    {
							if(s[i].practical>0)
							{
									ltt[labref].labQ.add(s[i]);
									labref=(labref+1)%no_of_lab;
									total-=3;
									do
									{
										s[i].practical--;
										ttt[j].teaching_hours-=3;
										ttt[j].subQ.add(s[i]);
								   		do{
											j=(int)Math.floor(Math.random()*no_of_teacher);
								   		}while(ttt[j].teaching_hours<3);
								   	}while(s[i].practical>0);
							}
							else
							{
								if(s[i].theortical>0)
								{
										s[i].theortical--;
										ttt[j].teaching_hours--;
										total--;
										ttt[j].subQ.add(s[i]);
								}
								else if(s[i].tutorial>0)
								{
									s[i].tutorial--;
									ttt[j].teaching_hours--;
									total--;
									ttt[j].subQ.add(s[i]);
								}
							}
					    }
				}
		}while(total!=0);
		for(int i=0;i<no_of_lab;i++)//Keep the 4th year at the top of the queue
		{
			Collections.sort(ltt[i].labQ,new MyComp());
		}	
		/*for(int i=0;i<no_of_teacher;i++)
		{
				System.out.println("Teacher "+(i+1));
				ttt[i].displayQ();
		}
		Thread.sleep(50000);*/
		/*for(int i=0;i<no_of_lab;i++)
		{
			System.out.println("Lab  "+(i+1)+"\n");
			ltt[i].displayQ();
		}
		Thread.sleep(10000);*/
		/*for(int i=0;i<no_of1_teacher;i++)
		{
				System.out.println("Teacher "+(i+1)+"\n");
				ttt[i].displayQ();
				Thread.sleep(1000);
		}*/

		int assigned=0;
		Subject sub=new Subject();
		while(assigned!=no_of_lab)
		{
			assigned=0;
			for(int i=0;i<no_of_lab;i++)
			{
				Teacher[] labteacher =new Teacher[3];
				labteacher[0]=new Teacher();
				labteacher[1]=new Teacher();
				labteacher[2]=new Teacher();
				int j=0;
				if(ltt[i].labQ.size()==0)
					assigned++;
				else
				{
						sub=ltt[i].labQ.removeFirst();
						for(int k=0;k<no_of_teacher;k++)
						{
							if(ttt[k].subQ.contains(sub))
							{
								ttt[k].subQ.remove(sub);
								labteacher[j]=ttt[k];
								j++;
							}
						}
					int s1=0,s2=1,s3=4,selected=0; //empty slots are 1-3,2-4,5-7
					for(j=0;j<6;j++)
					{
						if(Timetable.labchecker(ltt[i],labteacher[0],labteacher[1],labteacher[2],stt[sub.year-1][sub.deptcode],s1,j)==true)
						{
							ltt[i].timetable[j][s1]=sub;
							ltt[i].timetable[j][s1+1]=sub;
							ltt[i].timetable[j][s1+2]=sub;
							labteacher[0].timetable[j][s1]=sub;
							labteacher[0].timetable[j][s1+1]=sub;
							labteacher[0].timetable[j][s1+2]=sub;
							labteacher[1].timetable[j][s1]=sub;
							labteacher[1].timetable[j][s1+1]=sub;
							labteacher[1].timetable[j][s1+2]=sub;
							labteacher[2].timetable[j][s1]=sub;
							labteacher[2].timetable[j][s1+1]=sub;
							labteacher[2].timetable[j][s1+2]=sub;
							stt[sub.year-1][sub.deptcode].timetable[j][s1]=sub;
							stt[sub.year-1][sub.deptcode].timetable[j][s1+1]=sub;
							stt[sub.year-1][sub.deptcode].timetable[j][s1+2]=sub;
							selected=1;
							break;
						}
						else if(Timetable.labchecker(ltt[i],labteacher[0],labteacher[1],labteacher[2],stt[sub.year-1][sub.deptcode],s2,j)==true)
						{
							ltt[i].timetable[j][s2]=sub;
							ltt[i].timetable[j][s2+1]=sub;
							ltt[i].timetable[j][s2+2]=sub;
							labteacher[0].timetable[j][s2]=sub;
							labteacher[0].timetable[j][s2+1]=sub;
							labteacher[0].timetable[j][s2+2]=sub;
							labteacher[1].timetable[j][s2]=sub;
							labteacher[1].timetable[j][s2+1]=sub;
							labteacher[1].timetable[j][s2+2]=sub;
							labteacher[2].timetable[j][s2]=sub;
							labteacher[2].timetable[j][s2+1]=sub;
							labteacher[2].timetable[j][s2+2]=sub;
							stt[sub.year-1][sub.deptcode].timetable[j][s2]=sub;
							stt[sub.year-1][sub.deptcode].timetable[j][s2+1]=sub;
							stt[sub.year-1][sub.deptcode].timetable[j][s2+2]=sub;
							selected=1;
							break;
						}
					}
					for(j=0;j<6 && selected==0;j++)//this looping style checks for the availability of lab classes in the first half and then in second half
					{
						if(Timetable.labchecker(ltt[i],labteacher[0],labteacher[1],labteacher[2],stt[sub.year-1][sub.deptcode],s3,j)==true)
						{
							ltt[i].timetable[j][s3]=sub;
							ltt[i].timetable[j][s3+1]=sub;
							ltt[i].timetable[j][s3+2]=sub;
							labteacher[0].timetable[j][s3]=sub;
							labteacher[0].timetable[j][s3+1]=sub;
							labteacher[0].timetable[j][s3+2]=sub;
							labteacher[1].timetable[j][s3]=sub;
							labteacher[1].timetable[j][s3+1]=sub;
							labteacher[1].timetable[j][s3+2]=sub;
							labteacher[2].timetable[j][s3]=sub;
							labteacher[2].timetable[j][s3+1]=sub;
							labteacher[2].timetable[j][s3+2]=sub;
							stt[sub.year-1][sub.deptcode].timetable[j][s3]=sub;
							stt[sub.year-1][sub.deptcode].timetable[j][s3+1]=sub;
							stt[sub.year-1][sub.deptcode].timetable[j][s3+2]=sub;
							break;
						}
					}
				}
			}
		}
		for(int i=0;i<no_of_year;i++)
		{
			for(int j=0;j<no_of_dept;j++)
			{
				System.out.println("Year:"+(i+1)+" Department:"+j+"\n");
				stt[i][j].display();	
			}
		}
		for(int i=0;i<no_of_lab;i++)
		{
			System.out.println("Lab  "+(i+1)+"\n");
			ltt[i].display();
		}
	    	//do
		//{
			assigned=0;
			for(int i=0;i<no_of_teacher;i++)
			{
				if(ttt[i].subQ.size()==0)
					assigned++;
				else
				{
					sub=ttt[i].subQ.removeFirst();
					int k=ttt[i].max_day_ratio();
					int fs=ttt[i].first_period();
					if(fs<4){
						for(int j=0;j<7;j++)
						{
							if(ttt[i].timetable[k][j]==null&&stt[sub.year-1][sub.dept].timetable[k][j]==null)
							{
								ttt[i].timetable[k][j]=sub;
								stt[sub.year][sub.dept].timetable[k][j]=sub;
								break;
							}
						}
						if(j==7)
						{
							ttt[i].subQ.add(sub);
						}
					}
				}
			}
		//}while(assigned!=no_of_teacher;
		/*for(int i=0;i<no_of_teacher;i++)
		{
				System.out.println("Teacher "+(i+1)+"\n");
				ttt[i].display();
				Thread.sleep(1000);
		}*/
	}
}
