//package unsw.dungeon;

/**
 * A class to represent InvincibilityPotion
 * @author z5190769 Rebecca
 *
 */

public class InvincibilityPotion extends Entity {

	public InvincibilityPotion(int x, int y) {
        super(x, y, "InvincibilityPotion");
    }
	
    @Override
	public boolean movable(Dungeon dungeon) {
    	return true;
    }
}
