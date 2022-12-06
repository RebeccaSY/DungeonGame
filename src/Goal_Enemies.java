//package unsw.dungeon;

/**
 * A class to represent the goal of eliminating all enemies in the dungeon. Observes Dungeon.java. 
 * @author stephen
 *
 */

public class Goal_Enemies implements Goal, Observer {
	private int numEnemy = -1; 
	
	//Checks if enemies is 0
	public boolean isComplete() {
		if (numEnemy == -1)
			return false; 
		else {
			if (numEnemy == 0)
				return true;		
		}
		return false;
	}


	@Override 
	public void update(Subject obj) {
		if (obj instanceof Dungeon)
			update( (Dungeon) obj);
	}
	
	//Updates the number of enemies in the dungeon
	public void update (Dungeon obj) {
    	numEnemy = obj.getNumEnemy(); 
	}
}
