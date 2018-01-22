package aya;

import java.util.Scanner;

public class PermanentCalendar
{
	public static void main(String[] args)
	{
		Scanner cin = new Scanner(System.in);
		TimePoint cursor = new TimePoint();
		int year,month;
		while(true)
		{
			System.out.print("�������꣺");
			do year = cin.nextInt();
			while(!isYear(year));
			
			System.out.print("�������£�");
			do month = cin.nextInt();
			while(!isMonth(month));
			
			cursor.printCalendar(year,month);
		}
	}
	
	private static boolean isMonth(int month)
	{
		if(month>=1&&month<=12)
			return true;
		System.out.print("�·�����������������룬�·�=");
		return false;
	}
	
	private static boolean isYear(int year)
	{
		if(year!=0)
			return true;
		System.out.print("�������������������룬���=");
		return false;
	}
}



class TimePoint
{
	private int year = 2017;
	private int month = 9;
	private int day = 4;
	private int week = 1;
	
	private boolean isLeap()
	{
		int temp=(year>0)?year:year+1;
		if((temp%4==0&&temp%100!=0||temp%400==0)&&temp%3200!=0||temp%172800==0)
			return true;
		return false;
	}
	
	private void stepOn()
	{
		if(week==7)
			week=1;
		else
			week++;
		
		switch(month)
		{
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			if(day==31)
			{
				day=1;
				if(month==12)
				{
					month=1;
					if(year==-1)
						year=1;
					else
						year++;
				}
				else
					month++;
			}
			else
				day++;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			if(day==30)
			{
				day=1;
				month++;
			}
			else
				day++;
			break;
		case 2:
			int end;
			if(isLeap())
				end=29;
			else
				end=28;
			if(day==end)
			{
				day=1;
				month++;
			}
			else
				day++;
		}
	}
	
	private void stepBack()
	{
		if(week==1)
			week=7;
		else
			week--;
		
		switch(month)
		{
		case 2:
		case 4:
		case 6:
		case 8:
		case 9:
		case 11:
		case 1:
			if(day==1)
			{
				day=31;
				if(month==1)
				{
					month=12;
					if(year==1)
						year=-1;
					else
						year--;
				}
				else
					month--;
			}
			else
				day--;
			break;
		case 5:
		case 7:
		case 10:
		case 12:
			if(day==1)
			{
				day=30;
				month--;
			}
			else
				day--;
			break;
		case 3:
			int end;
			if(isLeap())
				end=29;
			else
				end=28;
			if(day==1)
			{
				day=end;
				month--;
			}
			else
				day--;
		}
	}
	
	public void printCalendar(int year,int month)
	{
		if(year>this.year||year==this.year&&month>this.month)
		{
			while(this.year!=year||this.month!=month||day!=1)
				stepOn();
		}
		else
		{
			while(this.year!=year||this.month!=month||day!=1)
				stepBack();
		}
		System.out.print("\n"+((year>0)?"��Ԫ":"��Ԫǰ"));
		System.out.println(Math.abs(year)+"��"+month+"��\nһ\t��\t��\t��\t��\t��\t��");
		for(int i=1;i<week;i++)
			System.out.print("\t");
		do
		{
			System.out.print(day+"\t");
			if(week==7)
				System.out.println();
			stepOn();
		}
		while(day>1);
		System.out.println("\n\n");
	}
}

