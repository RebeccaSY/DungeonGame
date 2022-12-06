//package unsw.dungeon;

/**
 * A class to represent the goal of completing all boulders. Observes Player.java.
 * @author stephen
 *
 */
public class Goal_Boulders implements Goal, Observer {
	private int not_switched = -1; 
	
	//Returns true if number of triggered_switches is 0 
	public boolean isComplete() {
		if (not_switched == -1) 
			return false;
		else {
			System.out.println(not_switched);
			if (not_switched == 0)
				return true;	
		}
		return false; 
	}
	
	@Override 
	public void update(Subject obj) {
		if (obj instanceof Dungeon)
			update( (Dungeon) obj);
	}
	
	//Updates the number of triggered switches in the dungeon
	public void update (Dungeon obj) {
    	not_switched = obj.getNumUntriggered(); 
	}
}