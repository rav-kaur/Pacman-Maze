/* 
 * Student Name: Ryan Jiffri
 * Student ID: 181741000
 */

import java.util.Scanner;

public class MazeSolver 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter("");
		
		int x = 0;
		int y = 0;
		
		while(sc.hasNext()) 
		{
			if(sc.next().equals("\n"))
			{
				y++;
				x = 0;
			}
			else 
			{
				x++;
			}
			
		}
		
		System.out.println("X = " + x);
		System.out.println("Y = " + y);
		
		sc.close();
	}
	
	
	
	

}
