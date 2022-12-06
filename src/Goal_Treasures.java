//package unsw.dungeon;

/**
 * A class to represent the goal of collecting all treasures. 
 * @author stephen
 *
 */
public class Goal_Treasures implements Goal, Observer {
	private int treasures = -1; 
		
	//Checks if treasures_remaining is 0
	public boolean isComplete() {
		if (treasures == -1)
			return false; 
		else {
			if (treasures == 0)
				return true;			
		}
		return false;
	}

	@Override 
	public void update(Subject obj) {
		if (obj instanceof Dungeon)
			update( (Dungeon) obj);
	}
	
	//Updates the number of treasures remaining in the dungeon
	public void update (Dungeon obj) {
    	treasures = obj.getNumTreasure(); 
	}
	
}
