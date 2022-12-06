//package unsw.dungeon;

/**
 * A class to represent entity Key
 * @author z5190769 Rebecca
 * @param <ID>
 *
 */

public class Key extends Entity {
	
	// each door corresponds to a key with the same ID
	private int ID;
	
	/**
     * Create an key positioned in square (x,y), with an ID
     * @param x
     * @param y
     * @param ID
     */
	public Key(int x, int y, int ID) {
        super(x, y, "Key");
        this.ID = ID;
    }
	
    public int getID() {
    	return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
     
    @Override
    public boolean movable(Dungeon dungeon) {
    	return true;
    }
}
