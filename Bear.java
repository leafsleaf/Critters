// Aaron Reagan
// 4/26/2020
// CS 145
// Lab #2: Critters
//
// This is the class "Bear" a critter class called by the superclass.
// It appears as black(/) or white(\) and moves dictated by specific rules.

import java.util.*;
import java.awt.*;

public class Bear extends Critter { // method/class that defines the bear critter
	
	private int slash;
	private boolean polar;

	public Bear(boolean polar)	{ // returns white if polar(\)
		
		this.slash = 0;
		this.polar = polar;
		
	}

	public Color getColor() { // returns color
		
		if (this.polar)	{
			
			return Color.WHITE;
		
		}
			
		else	{
			
			return Color.BLACK; 
			
		}
		
	}

	public String toString() { // return string
		
		this.slash = this.slash + 1;
		
		if (slash % 2 == 0)	{
			
			return "/";
		
		}

		else {
			
			return "\\";
			
		}
		
	}


	public Action getMove(CritterInfo info)	{ // infect return
		
		this.slash++;
		
		if (info.getFront().equals(Neighbor.OTHER))	{
			
			return Action.INFECT;
		
		} 
		
		else if (info.getFront().equals(Neighbor.EMPTY))	{
			
			return Action.HOP;
			
		} 	
		
		else {
			
			return Action.LEFT;
			
		}
		
	}
	
}