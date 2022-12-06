//package unsw.dungeon;

/**
 * A class to represent treasure
 * @author z5190769 Rebecca
 *
 */

public class Treasure extends Entity {
    
	public Treasure(int x, int y) {
        super(x, y, "Treasure");
    }
	
    @Override
	public boolean movable(Dungeon dungeon) {
    	return true;
    }
	
}
