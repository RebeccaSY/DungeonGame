//package unsw.dungeon;
/**
 * A class to represent sword
 * @author z5190769 Rebecca
 *
 */
public class Sword extends Entity {
    
	public Sword(int x, int y) {
        super(x, y, "Sword");
    }

    @Override
	public boolean movable(Dungeon dungeon) {
    	return true;
    }
}
