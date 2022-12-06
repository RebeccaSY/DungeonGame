//package unsw.dungeon;

/**
 * A class to represent an item that can be picked up (added to inventory) i.e. Treasure,
 * 	key, sword, invincibility potion.
 * @author stephen
 *
 */
public class Item {
	
	private String name;	//Name of the object i.e. treasure, key, sword etc...
	private int additional;	//An additional field for information i.e. the unique ID for a key
	
    public Item(String name, int additional) {
        this.name = name;
        this.additional = additional; 
    }
    
    public String getName() {
    	return name;
    }
    
    public int getAdditional() {
    	return additional;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setAdditional(int additional) {
    	this.additional = additional; 
    }
}
