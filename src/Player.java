//package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject{

    private Dungeon dungeon;
    private ArrayList<Observer> listObservers = new ArrayList<Observer>();
    private String direction;
    public static final int POTION_TIMER = 6; 
    public static final int SWORD_DURABILITY = 5; 
    private IntegerProperty alive; 

    //private Inventory inventory; 
    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y, "Player");
        this.dungeon = dungeon;
        //this.setInventory(dungeon.getInventory());
        this.setDirection("");
        this.alive = new SimpleIntegerProperty(1);
    }

    public void moveUp() {
        this.setDirection("Up");
    	if (dungeon.isValidMove(getX(), getY() - 1, this) == false)
    		return;
        if (getY() > 0) {
            setY(getY() - 1);
	        dungeon.goInteract(getX(), getY());
	        if (isInvincible()) {
	        	updateInvincibility();
	        }
	        updateEnemy();
        }
    }

    public void moveDown() {
        this.setDirection("Down");
    	if (dungeon.isValidMove(getX(), getY() + 1, this) == false) 
    		return;
        if (getY() < dungeon.getHeight() - 1) {
            setY(getY() + 1);
	        dungeon.goInteract(getX(), getY());
	        if (isInvincible()) {
	        	updateInvincibility();
	        }
	        updateEnemy();
        }
    }

    public void moveLeft() {
        this.setDirection("Left");
    	if (dungeon.isValidMove(getX() - 1, getY(), this) == false) 
    		return;
        if (getX() > 0) {
            setX(getX() - 1);
	        dungeon.goInteract(getX(), getY());
	        if (isInvincible()) {
	        	updateInvincibility();
	        }
	        updateEnemy();
        }
    }

    public void moveRight() {
        this.setDirection("Right");
    	if (dungeon.isValidMove(getX() + 1, getY(), this) == false)
    		return;
        if (getX() < dungeon.getWidth() - 1) {
            setX(getX() + 1);
	        dungeon.goInteract(getX(), getY());
	        if (isInvincible()) {
	        	updateInvincibility();
	        }
	        updateEnemy();
        }
    }
    
    public void moveTo(int x, int y) {
    	x().set(x);
    	y().set(y);
    	if (isInvincible()) {
        	updateInvincibility();
        }
    	updateEnemy();
    }
    
    // check if the player is invincible
    public boolean isInvincible() {
    	Inventory inventory = dungeon.getInventory();
    	if (inventory.findtype("InvincibilityPotion") != null) {
    		return true;
    	}
    	return false;
    }
    
    // the effect of InvincibilityPotion only lasts a limited time
    public void updateInvincibility() {
    	Inventory new_inv = dungeon.getInventory();
    	Item potionItem = new_inv.findtype("InvincibilityPotion");
    	potionItem.setAdditional(potionItem.getAdditional() - 1);
    	if (potionItem.getAdditional() == 0) {
    		new_inv.removeItem(potionItem);
    	}
    	dungeon.setInventory(new_inv);
    }
    
    // update enemy movement based on every player movement
    public void updateEnemy() {
    	List<Entity> entities = dungeon.getEntities();
    	List<Enemy> enemy_entities = new ArrayList<>();
    	for (Entity e : entities) {
    		if (e.getType().equals("Enemy")) {
    			Enemy enemy = (Enemy) e;
    			enemy_entities.add(enemy);
    		}
    	}
    	for (Enemy en : enemy_entities) {
    		en.move(dungeon);
    	}
    }
    
    // check if the player has a valid sword
    public boolean hasSword() {
    	Inventory inventory = dungeon.getInventory();
    	if (inventory == null) {
    		return false;
    	}
    	Item swordItem = inventory.findtype("Sword");
        if (swordItem != null) {
        	if (inventory.findUsage(swordItem) > 0) {
        		return true;
        	}
        }
        return false;
    }
    
    //Checks if a square has an Item that can be picked up
    public boolean isItem(int x, int y) {
    	if (dungeon.isItem(x, y) == true) {
    		return true;
    	}
    	return false; 
    }
    
    //Notify observer when position of player changes
    public void setY(int y) {
    	y().set(y);
    	notifyObservers();
    }
    
    public void setX(int X) {
    	x().set(X);
    	notifyObservers();
    }
    
    //Subject-Observer related functions
    @Override
    public void registerObserver(Observer o) {
    	if(! listObservers.contains(o)) 
    		listObservers.add(o);
    }
    
    @Override
    public void removeObserver(Observer o) {
    	listObservers.remove(o);
    }
    
    @Override
    public void notifyObservers() {
    	for (Observer obs : listObservers) {
    		obs.update(this);
    	}
    }
    
    public Exit getExit() {
    	Exit e = dungeon.getExit();
    	return e; 
    }
    
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
    
	public void playerPickup(Dungeon dungeon, Entity entity) {
		if (entity.getType().equals("Sword")) {
			if (this.hasSword() == false) {
				Item swordItem = new Item("Sword", SWORD_DURABILITY);
				Inventory new_inv = dungeon.getInventory();
				new_inv.addItem(swordItem);
				dungeon.setInventory(new_inv);
				dungeon.removeEntity(entity);
			}
		} else if (entity.getType().equals("Treasure")) {
			Item TreasureItem = new Item("Treasure", dungeon.getNumOFtreasure() + 1);
			Inventory new_inv = dungeon.getInventory();
			new_inv.addItem(TreasureItem);
			dungeon.setInventory(new_inv);
			dungeon.removeEntity(entity);
		} else if (entity.getType().equals("InvincibilityPotion")) {
			if (this.isInvincible() == false) {
				Item Potion = new Item("InvincibilityPotion", POTION_TIMER);
				Inventory new_inv = dungeon.getInventory();
				new_inv.addItem(Potion);
				dungeon.setInventory(new_inv);
				dungeon.removeEntity(entity);
			}
		} else if (entity.getType().equals("Key")) {
			if (dungeon.hasKey() == false) {
				Key new_key = (Key) entity;
				Item KeyItem = new Item("Key", new_key.getID());
				Inventory new_inv = dungeon.getInventory();
				new_inv.addItem(KeyItem);
				dungeon.setInventory(new_inv);
				dungeon.removeEntity(entity);
			}
		}
	}	
	
    public IntegerProperty alive() {
        return alive;
    }
    
    public void setAlive() {
    	alive.set(0); 
    }
}

/* moved to Dungeon.java
 * 
 * 
 * 
//Checks if a move is valid i.e. check if the square is a wall or other obstacle
public boolean isValidMove(int x, int y) {
	List<Entity> entities = dungeon.getEntitiesOnSquare(x, y);
	for (Entity e : entities) {
        if (e.movable(dungeon) == false) {
        	return false;
        }
	}
	return true; 
}

    // check if the player has a key in inventory
    public boolean hasKey() {
    	if (inventory == null) {
    		return false;
    	}
    	Item keyItem = inventory.findtype("Key");
        if (keyItem != null && inventory.findUsage(keyItem) > 0) {
          return true;
        }
        return false;
    }

    public void setInventory(Inventory inventory) {
    	this.inventory = inventory;
    	notifyObservers();
    }
*/
