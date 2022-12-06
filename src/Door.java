//package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A class to represent entity Door
 * @author z5190769 Rebecca
 * @param <ID>
 *
 */

public class Door extends Entity {
	
	// each door corresponds to a key with the same ID
	private int ID;
	private IntegerProperty open; 
	
	/**
     * Create an door positioned in square (x,y) with an ID and status
     * @param x
     * @param y
     *
     */
	public Door(int x, int y, int ID) {
        super(x, y, "Door");
        this.ID = ID;
        this.open = new SimpleIntegerProperty(0);
    }
	
    public int getID() {
    	return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
    }

	
	public IntegerProperty isOpen() {
        return open;
    }
    
    @Override
    // A door can be moved onto by player when it's open or the player has the key
    public boolean movable(Dungeon dungeon) {
    	Inventory inv = dungeon.getInventory();
    	if (open.getValue() == 1) {
    		return true;
    	} else if (inv == null) {
    		return false;
    	} else if (inv.findtype("Key") != null) {
    		Item key = inv.findtype("Key");
    		if (key.getAdditional() == ID) {
        		return true;
    		}
    	} 
    	return false;
    }
    
    public void openDoor(Door door) {
    	open.set(1);
    }
    
	public void playerOpenDoor(Dungeon dungeon, Player player) {
		Inventory new_inv = dungeon.getInventory();
		Item key = new_inv.findtype("Key");
		openDoor(this);
		new_inv.removeItem(key);
		dungeon.setInventory(new_inv);
	}

	
}


