// Aaron Reagan
// 4/26/2020
// CS 145
// Lab #2: Critters
//
// This is the super unique class "Orca" a critter class called by the superclass.
// It appears as Green and will form up into pods, then collapse.

import java.awt.*;
import java.util.*;


public class Orca extends Critter {

	public static ArrayList<Orca> orcaList = new ArrayList<Orca>(); // orcas in or out of pods and behavior fields

	private final Neighbor[] sides = new Neighbor[4];
	private final String[] whichO = { "OrcaX", "OrcaY", "OrcaZ", "OrcaZZ" };
	
	private final int side;
	private final int orcaRmove;
	
	private int oMovesOth;
	private int podC;
	private int oTravC;
	private int oClose;
	private int count;
	private int oInd;
	
	private boolean moveL;
	private boolean collapse;
	
	private CritterInfo info;

	private static final double PODS_GATHER = .05;

	public Orca() {

		final Random r = new Random();

		orcaRmove = r.nextInt(90);

		moveL = true;

		orcaList.add(this);

		oMovesOth = 0;

		side = 0;

		podC = orcaList.get(0).gRoundsColl();

		collapse = orcaList.get(0).doCollapse();

		oClose = 0;

		oTravC = orcaList.get(0).aNum();

	}


	public Color getColor()	{ // color
		
		return Color.GREEN;

	}



	public String toString() { // return string
		
		this.count++;
		
		if (this.count > 0) {
			
			this.count = 1;
			this.oInd++;
			
			if (this.oInd == 4)	{
				
				this.oInd = 0;
				
			}
			
			return whichO[this.oInd];
			
		}
			
		else {
			
			return whichO[this.oInd];
			
		}

	}

	public Action getMove(final CritterInfo info) { // state test

		refreshState(info);

		if (collapse) {

			podC++;

			return podActions();
			
		} 

		else {
			

			return normalBehavior();

		}

	}

	private Action podActions()	{ // actions determination

		final Direction d = info.getDirection();

		if (sides[0] == Neighbor.OTHER) {

			return Action.INFECT;
			
		} 

		else if (sides[1] == Neighbor.OTHER) {

			return Action.LEFT;
			
		}

		else if (sides[3] == Neighbor.OTHER) {

			return Action.RIGHT;
		
		} 

		else if (podC < 25&& !(d == Direction.EAST)) {

			return Action.LEFT;
			
		} 

		else if (podC > 25 && podC < 100 && !(d == Direction.WEST)) {

			return Action.RIGHT;
			
		}

		else if (podC > 100 && podC < orcaRmove + 100 && !(d == Direction.EAST)) {

			return Action.LEFT;
	
		} 

		else if (podC > orcaRmove + 100 && podC < 175 && !(d == Direction.NORTH)) {

			return Action.RIGHT;

		}
			
		else if (podC > 175 && podC < 225 && !(d == Direction.SOUTH)) {

			return Action.RIGHT;
		
		} 

		else if (podC > 225 && podC < orcaRmove + 225 && !(d == Direction.NORTH)) {

			return Action.LEFT;
		
		}

		else if (podC > orcaRmove + 225 && podC < 255) {

			return normalBehavior();
			
		} 

		else if (podC < 255) {

			return Action.HOP;
		
		} 

		else {

			podC = 0;

			return Action.HOP;

		}

	}

	private Action normalBehavior() { // trap actions

		if (sides[0] == Neighbor.OTHER) {

			return Action.INFECT;
			
		} 

		else if (oClose == 3) {

			return sideBehavior();
	
		}


		else if (oClose == 2) {

			return cornerBehavior();
		
		} 

		else {

			return Action.RIGHT;

		}

	}

	private Action cornerBehavior() {	// orca actions near corner

		if (isOpen(sides[1])) {

			return Action.LEFT;

		} 	
			
		else {

			return Action.RIGHT;

		}

	}

	private Action sideBehavior() { // pod side actions

		if (isOpen(sides[0])) {

			return Action.INFECT;
			
		} 

		else if (isOpen(sides[1])) {

			return Action.LEFT;
			
		} 

		else {

			return Action.RIGHT;

		}

	}

	private boolean isOpen(final Neighbor side) { // neighbor actinos

		return !(side == Neighbor.SAME || side == Neighbor.WALL);

	}

	public int oActionsAft() { // actions since

		return oMovesOth;

	}


	private void refreshState(final CritterInfo info) { //updates

		int others = 0;

		oTravC++;

		sides[0] = info.getFront();

		sides[1] = info.getLeft();

		sides[2] = info.getBack();

		sides[3] = info.getRight();

		oClose = 0;

		for (int i = 0; i < sides.length; i++) {

			if (sides[i] == Neighbor.OTHER) {

				others++;

			}

			if (sides[i] == Neighbor.SAME || sides[i] == Neighbor.WALL) {

				oClose++;

			}

		}

		if (others == 0) {

			oMovesOth++;

		} else {

			oMovesOth = 0;

		}

		int oCountAlone = 0;

		for (int i = 0; i < orcaList.size(); i++) {

			if (orcaList.get(i).oActionsAft() > 75) {

				oCountAlone++;

			}

		}

		if (oCountAlone > orcaList.size() * PODS_GATHER) {

			collapse = true;

		}

		if (podC > 0) {

			collapse = true;

		}

		this.info = info;

	}

	public int aNum() { // return info for orca travel count

		return oTravC;

	}

	public int gRoundsColl() { // rounds for collapse

		return podC;

	}


	public boolean doCollapse() {

		return collapse;

	}
	
}
