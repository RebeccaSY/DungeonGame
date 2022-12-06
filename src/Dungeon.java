/**
 *
 */
//package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon implements Subject{

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private ArrayList<Observer> listObservers = new ArrayList<Observer>();
    private Inventory inventory;
    private int numOFenemy, numOFsword, numOFtreasure, numOFdoor, numOFkey;
    private int numOFboulder, numOFswitch;
    private Goal_Condition goals; 
    private Exit exit; 
    
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.inventory = new Inventory();
        this.setNumOFenemy(0);
        this.setNumOFsword(0);
        this.setNumOFtreasure(0);
        this.setNumOFdoor(0);
        this.setNumOFkey(0);
        this.setNumOFboulder(0);
        this.setNumOFswitch(0);
    }

	public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
    	if (entity.getType().equals("Enemy")) {
    		setNumOFenemy(getNumOFenemy() + 1);
    	} else if (entity.getType().equals("Sword")) {
    		setNumOFsword(getNumOFsword() + 1);
    	} else if (entity.getType().equals("Treasure")) {
    		setNumOFtreasure(getNumOFtreasure() + 1);
    	} else if (entity.getType().equals("Door")) {
    		setNumOFdoor(getNumOFdoor() + 1);
    	} else if (entity.getType().equals("Key")) {
    		setNumOFkey(getNumOFkey() + 1);
    	} else if (entity.getType().equals("Boulder")) {
    		setNumOFboulder(getNumOFboulder() + 1);
    	} else if (entity.getType().equals("FloorSwitch")) {
    		setNumOFswitch(getNumOFswitch() + 1);
    	} else if (entity.getClass() == Exit.class) {
    		this.exit = (Exit) entity;
    	}
    	
        entities.add(entity);
        notifyObservers();
    }
    
    public void removeEntity(Entity entity) {
    	if (entities.contains(entity)) {
    		if (entity.getType().equals("Enemy")) {
        		setNumOFenemy(getNumOFenemy() - 1);
        	} else if (entity.getType().equals("Sword")) {
        		setNumOFsword(getNumOFsword() - 1);
        	} else if (entity.getType().equals("Treasure")) {
        		setNumOFtreasure(getNumOFtreasure() - 1);
        	} else if (entity.getType().equals("Door")) {
        		setNumOFdoor(getNumOFdoor() - 1);
        	} else if (entity.getType().equals("Key")) {
        		setNumOFkey(getNumOFkey() - 1);
        	} else if (entity.getType().equals("Boulder")) {
        		setNumOFboulder(getNumOFboulder() - 1);
        	} else if (entity.getType().equals("FloorSwitch")) {
        		setNumOFswitch(getNumOFswitch() - 1);
        	}
    		entities.remove(entity);
    		entity.setDisplayed(); 
    	}
    	notifyObservers();
    }
    
	//Returns the ArrayList of all entities in the dungeon
    public List<Entity> getEntities() {
    	return entities;
    }
    
    //Returns the ArrayList of all entities on a particular square in the dungeon 
    public List<Entity> getEntitiesOnSquare(int x, int y) {
    	List<Entity> e = new ArrayList<>();
    	for (Entity entity: entities) {
    		if (entity != null && entity.getX() == x && entity.getY() == y) {
    			e.add(entity);
    		}
    	}
    	return e;
    }
        
    //Returns true if a particular square contains a wall
    public boolean isWall(int x, int y) {
    	List<Entity> e = new ArrayList<>();
    	e = getEntitiesOnSquare(x, y);
    	for (Entity entity: e) {
    		if (entity.getClass() == Wall.class) {
    			return true;
    		}
    	}
    	return false; 
    }
    
  //Returns true if a particular square contains a boulder
    public boolean isBoulder(int x, int y) {
    	List<Entity> e = new ArrayList<>();
    	e = getEntitiesOnSquare(x, y);
    	for (Entity entity: e) {
    		if (entity.getClass() == Boulder.class) {
    			return true;
    		}
    	}
    	return false; 
    }
    
    //Returns true if a particular square has at least one item that can be picked up 
    public boolean isItem(int x, int y) {
    	List<Entity> e = new ArrayList<>();
    	e = getEntitiesOnSquare(x, y);
    	for (Entity entity: e) {
    		if ((entity.getType().equals("Sword") || entity.getType().equals("Treasure")) || 
    			(entity.getType().equals("InvincibilityPotion") || entity.getType().equals("Key"))) {
    			return true;
    		}
    	}
    	return false;
    }

  //Returns the exit entity
    public Exit getExit() {
    	for (Entity e: entities) {
    		if (e.getClass() == Exit.class)
    			return (Exit) e;
    	}
    	return null;
    }
      
    /**
    public void removeEnemy(Enemy obj ) {
    	//remove enemy obj
    	//notify observers 
    }
    **/
    
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

	public int getNumOFenemy() {
		return numOFenemy;
	}

	public void setNumOFenemy(int numOFenemy) {
		this.numOFenemy = numOFenemy;
	}

	public int getNumOFsword() {
		return numOFsword;
	}

	public void setNumOFsword(int numOFsword) {
		this.numOFsword = numOFsword;
	}

	public int getNumOFtreasure() {
		return numOFtreasure;
	}

	public void setNumOFtreasure(int numOFtreasure) {
		this.numOFtreasure = numOFtreasure;
	}

	public int getNumOFdoor() {
		return numOFdoor;
	}

	public void setNumOFdoor(int numOFdoor) {
		this.numOFdoor = numOFdoor;
	}

	public int getNumOFkey() {
		return numOFkey;
	}

	public void setNumOFkey(int numOFkey) {
		this.numOFkey = numOFkey;
	}   
	

    public int getNumOFswitch() {
		return numOFswitch;
	}

	public int getNumOFboulder() {
		return numOFboulder;
	}

    public void setNumOFswitch(int numOFswitch) {
		this.numOFswitch = numOFswitch;
	}

	public void setNumOFboulder(int numOFboulder) {
		this.numOFboulder = numOFboulder;
	}
	
	// check if there is a valid interaction
	public void goInteract(int x, int y) {
		// if the player is interacting with other entities
		List<Entity> curr_entities = getEntitiesOnSquare(x, y);
		if (curr_entities.size() > 1 && curr_entities.contains(getPlayer()))
		{
			for (Entity e : curr_entities) {
				if (e.getType().equals("Boulder")) {
					Boulder boul = (Boulder) e;
					if (boul.movable(this)) {
						boul.pushBoulder(this, getPlayer());						
					}
				} else if (e.getType() != "Player") {
					playerInteract(getPlayer(), e);
				}
			}
		}		
	}
	
	// interaction between two entities
	public void playerInteract(Player player, Entity entity2) {
		
		if (entity2.getType().equals("Enemy")) {
			Enemy enemy = (Enemy) entity2;
			enemy.PEinteract(this, player);
		} else if (isItem(entity2.getX(), entity2.getY())) {
			player.playerPickup(this, entity2);
		} else if (entity2.getType().equals("Door")) {
			Door door = (Door) entity2;
			door.playerOpenDoor(this, player);
		} else if (entity2.getType().equals("Exit")) {
			Exit exit = (Exit) entity2;
			exit.throughExit(this, player);
		} else if (entity2.getType().equals("Portal")) {
			Portal portal = (Portal) entity2;
			portal.throughPortal(this, player);
		} 
		
	}
	
    //Checks if a move is valid i.e. check if the square is a wall or other obstacle
    public boolean isValidMove(int x, int y, Entity obj) {
    	List<Entity> list = getEntitiesOnSquare(x, y);
    	if (obj.getClass() == Player.class) {
    		for (Entity e : list) {
    	        if (e.movable(this) == false)
    	        	return false;
    		}
    	} else if (obj.getClass() == Enemy.class) {
     		for (Entity e : list) {
     			if (e.isObstacleForEnemy())
     				return false;				
     		}
    	}
    	return true; 
    }
    
    //returns array list of valid moves
    public List<Coordinates> getValidMoves(int x, int y, Entity obj) {
		List<Coordinates> moves = new ArrayList<Coordinates>();
    	if (obj.getClass() == Enemy.class) {
    		//check move down, if valid, add to list
    		if (isValidMove(x, y + 1, obj))
    			moves.add(new Coordinates(x, y + 1));	
    		//check move left, if valid, add to list
    		if (isValidMove(x - 1, y, obj))
    			moves.add(new Coordinates(x - 1, y));
    		//check move right, if valid, add to list
    		if (isValidMove(x, y - 1, obj))
    			moves.add(new Coordinates(x, y - 1));
    		//check move up, if valid, add to list
    		if (isValidMove(x + 1, y, obj))
    			moves.add(new Coordinates(x + 1, y));
    	}
    	return moves;
    }
	
    // check if the player has a key in inventory
    public boolean hasKey() {
    	Item k = inventory.findtype("Key");
        if (k != null && inventory.findUsage(k) > 0) {
          return true;
        }
        return false;
    }
    
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public Goal_Condition getGoals() {
		return goals;
	}
	
	public void addGoal(Goal g, String o) {
		goals.setGoal(g);
	}
	
	public void setGoals(Goal_Condition g) {
		this.goals = g;
	}
	
	public boolean hasEntity(Entity e) {
		return entities.contains(e);
	}
	
	public int getNumTreasure() {
		return numOFtreasure; 
	}
	
	public int getNumEnemy() {
		return numOFenemy; 
	}
	
	public int getNumUntriggered() {
		int i = 0; 
    	for (Entity e: getEntities()) {
    		if (e.getClass() == FloorSwitch.class) {
    			FloorSwitch f = (FloorSwitch) e;
    			if (f.isTriggered() == false)
    				i++;
    		}
    	}
    	return i; 
	}
	
	public int getExitX() {
		return exit.getX(); 
	}
	
	public int getExitY() {
		return exit.getY(); 
	}
}
