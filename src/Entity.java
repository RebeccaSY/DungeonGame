//package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    private String type;
    private IntegerProperty displayed; 
    
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     * @param type
     */
    public Entity(int x, int y, String type) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.type = type;
        this.displayed = new SimpleIntegerProperty(1);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }
    
    public IntegerProperty displayed() {
        return displayed;
    }

    public void setDisplayed() {
    	displayed.set(0); 
    }
    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

    public String getType() {
        return type;
    }
    
    //Returns true if an entity is an obstacle for an enemy. 
    public boolean isObstacleForEnemy() {
    	if (type.equals("Wall") || type.equals("Exit") || type.equals("Boulder")
    		|| type.equals("Enemy") || type.equals("Door"))
    		return true;
    	return false;
    }
    
    //Returns true if an entity is a door.
    public boolean isDoor() {
    	if (type.equals("Door")) 
    		return true;
    	return false;
    }
    
    public boolean movable(Dungeon dungeon) {
    	return true;
    }
}
