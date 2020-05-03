// Aaron Reagan
// 4/26/2020
// CS 145
// Lab #2: Critters
//
// This is the class "Lion" a critter class called by the superclass.
// It appears as Red, Green or Blue randomly and moves dictated by specific rules.

import java.awt.*;
import java.util.*;

public class Lion extends Critter	{

	private int rGb;
	private int slash;
	private Color currRgb;
	private Random r;


	public Lion()	{ // what makes up the lion
		
		this.r = new Random();
		this.slash = 3;
		
	}

	public Color getColor()	{ // the color

		this.rGb = this.r.nextInt(3);
		
		if (slash % 3 == 0)	{

			if (rGb == 0)	{

				currRgb = Color.RED;
				return currRgb;

			}		
			
			else if (rGb == 1)	{
				
				currRgb = Color.GREEN;
				return currRgb;
				
			} 
				
			else 	{
				
				currRgb = Color.BLUE;
				return currRgb;

			}
			
		} 
		
		else	{
			
			return currRgb;
			
		}

	}

	public String toString() 	{ // return string

		return "L";

	}

	public Action getMove(CritterInfo info)	{ // infect/move
		
		this.slash++;

		if (info.getFront().equals(Neighbor.OTHER))	{
			
			return Action.INFECT;
		
		} 	
			
		else if (info.getFront().equals(Neighbor.WALL) || info.getRight().equals(Neighbor.WALL))	{
			
			return Action.LEFT;
		
		} 
			
		else if (info.getFront().equals(Neighbor.SAME))	{
			
			return Action.RIGHT;
			
		} 
			
		else {
			
			return Action.HOP;
			
		}
		
	}

}