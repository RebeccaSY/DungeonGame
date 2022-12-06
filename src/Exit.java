//package unsw.dungeon;

/**
 * A class to represent the Exit entity which can be a goal to a maze.
 * @author stephen
 *
 */

public class Exit extends Entity {
    
	public Exit(int x, int y) {
        super(x, y, "Exit");
    }
	
    @Override
	public boolean movable(Dungeon dungeon) {
    	return true;
    }
    
	public void throughExit(Dungeon dungeon, Player player) {
		// game over, check goals
		dungeon.removeEntity(player);
	}    
}
