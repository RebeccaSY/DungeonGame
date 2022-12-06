//package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Enemy extends Entity implements Subject {
	private Dungeon dungeon;
	private ArrayList<Observer> listObservers = new ArrayList<Observer>();	
	/**
     * Create a enemy positioned in square (x,y)
     * @param x
     * @param y
     */
	public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y, "Enemy");
        this.dungeon = dungeon;
    }
	
    public void moveUp() {
    	if (dungeon.isValidMove(getX(), getY() - 1, this) == false)
    		return;
        if (getY() > 0) {
            setY(getY() - 1);
        	dungeon.goInteract(getX(), getY());
        }
    }

    public void moveDown() {
    	if (dungeon.isValidMove(getX(), getY() + 1, this) == false) 
    		return;
        if (getY() < dungeon.getHeight() - 1) {
            setY(getY() + 1);
        	dungeon.goInteract(getX(), getY());
        }
    }

    public void moveLeft() {
    	if (dungeon.isValidMove(getX() - 1, getY(), this) == false) 
    		return;
        if (getX() > 0) {
            setX(getX() - 1);
        	dungeon.goInteract(getX(), getY());
        }
    }

    public void moveRight() {
    	if (dungeon.isValidMove(getX() + 1, getY(), this) == false)
    		return;
        if (getX() < dungeon.getWidth() - 1) {
            setX(getX() + 1);
        	dungeon.goInteract(getX(), getY());
        }
    }

 //Notify observer when position of enemy changes
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
   
	public void moveTowards(Dungeon dungeon) {
		Player player = dungeon.getPlayer();
		List<Coordinates> moves = new ArrayList<Coordinates>();
		moves = dungeon.getValidMoves(getX(), getY(), this); 
		Coordinates p = new Coordinates(player.getX(), player.getY());
		Coordinates e = new Coordinates(getX(), getY());
		double distance = p.distanceBetweenTwoPoints(e);
		Coordinates c = null; 
		//find the move that results in least distance to player
		for (Coordinates m: moves) {
			if (p.distanceBetweenTwoPoints(m) < distance) {
				distance = p.distanceBetweenTwoPoints(m);
				c = m;
			}
		}
		
		// make the movement
		if (c != null) {
			if (c.getX() != e.getX()) {
				if (c.getX() > e.getX())
					moveRight(); 
				else
					moveLeft();
			} else {
				if (c.getY() > e.getY())
					moveDown();
				else
					moveUp(); 
			}
		}
		
	}
	
	//Enemy moves away from player who is invincible
	public void moveAway(Dungeon dungeon) {
		Player player = dungeon.getPlayer();
		List<Coordinates> moves = new ArrayList<Coordinates>();
		moves = dungeon.getValidMoves(getX(), getY(), this); 
		Coordinates p = new Coordinates(player.getX(), player.getY());
		double distance = 0;
		Coordinates c = null; 
		//find the move that results in greatest distance to player
		for (Coordinates m: moves) {
			if (p.distanceBetweenTwoPoints(m) > distance) {
				distance = p.distanceBetweenTwoPoints(m);
				c = m;
			}
		}
		Coordinates e = new Coordinates(getX(), getY());
		
		//make the move
		if (c != null) {
			if (c.getX() != e.getX()) {
				if (c.getX() > e.getX())
					moveRight(); 
				else
					moveLeft();
			} else {
				if (c.getY() > e.getY())
					moveDown();
				else
					moveUp(); 
			}
		}
	}
	

	public void move(Dungeon dungeon) {
		Player player = dungeon.getPlayer();
		if (player.isInvincible()) {
			moveAway(dungeon);
		} else {
			moveTowards(dungeon);
		}
	}
	
	public void PEinteract(Dungeon dungeon, Player player) {
		if (player.isInvincible()) {
			dungeon.removeEntity(this);
		} else if (player.hasSword()) {
			dungeon.removeEntity(this);
			Inventory new_inv = dungeon.getInventory();
			Item swordItem = new_inv.findtype("Sword");
			swordItem.setAdditional(swordItem.getAdditional() - 1);
			if (swordItem.getAdditional() == 0) {
				new_inv.removeItem(swordItem);
			}
			dungeon.setInventory(new_inv);
		} else {
			// game over
			dungeon.removeEntity(player);
			player.setAlive();
		}
	}	
}

/* MOVED TO Dungeon.java
//Checks if a move is valid i.e. check if the square is a wall or other obstacle
public boolean isValidMove(int x, int y) {
     List<Entity> entities = dungeon.getEntitiesOnSquare(x, y);
		for (Entity e : entities) {
			if (e.isObstacleForEnemy()) {
				return false;
			} else if (e.isDoor()) {
				Door door = (Door) e;
				if (door.isOpen() == false)
					return false;					
			}
		}
     return true; 
}
*/