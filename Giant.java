// Aaron Reagan
// 4/26/2020
// CS 145
// Lab #2: Critters
//
// This is the class "Giant" a critter class called by the superclass.
// It appears as grey and randomly and moves dictated by what mode he is in between
// fee, fie, foe and fum.

import java.awt.*;
import java.util.*;

public class Giant extends Critter	{ // stores in array and alternates
	
	private final String[] whichG = { "fee", "fie", "foe", "fum" };
	private int slash;
	private int gInd;

	public Action getMove(CritterInfo info)	{ // returns what giant will do
		
		if (info.getFront() == Neighbor.OTHER)	{
			
			return Action.INFECT;
		
		}
			
		else if (info.getFront() == Neighbor.EMPTY)	{
			
			return Action.HOP;
			
		} 
		
		else	{
			
			return Action.RIGHT;
		}
		
	}

	public Color getColor()	{ // color
		
		return Color.GRAY;

	}

	public String toString()	{ // string giant
		
		this.slash++;
		
		if (this.slash > 6)	{
			
			this.slash = 1;
			this.gInd++;
			
			if (this.gInd == 2)	{
				
				this.gInd = 0;
				
			}
			
			return whichG[this.gInd];
			
		}
			
			else {
				
			return whichG[this.gInd];
			
			}
		
	}
	
}





      
      
      
      
            
      