//package unsw.dungeon;

import java.util.List;


public class Boulder extends Entity {

	/**
     * Create a boulder positioned in square (x,y)
     * @param x
     * @param y
     */
	public Boulder(int x, int y) {
		super(x, y, "Boulder");
	}
	
	public void setX(int x) {
		x().set(x);
	}
	
	public void setY(int y) {
		x().set(y);
	}
		
	public void moveTo(int x, int y) {
    	x().set(x);
    	y().set(y);
    }
	
    @Override
	public boolean movable(Dungeon dungeon) {
    	Player player = dungeon.getPlayer();
    	if (player.getDirection().equals("Down")) {
    		if ((dungeon.isWall(getX(), getY() + 1) || dungeon.isBoulder(getX(), getY() + 1)) || (getY() >= dungeon.getHeight())) {
    			return false;
    		}
    	} else if (player.getDirection().equals("Up")) {
    		if ((dungeon.isWall(getX(), getY() - 1) || dungeon.isBoulder(getX(), getY() - 1)) || (getY() <= 0)) {
    			return false;
    		}
    	} else if (player.getDirection().equals("Left")) {
    		if ((dungeon.isWall(getX() - 1, getY()) || dungeon.isBoulder(getX() - 1, getY())) || (getX() <= 0)) {
    			return false;
    		}
    	} else if (player.getDirection().equals("Right")) {
    		if ((dungeon.isWall(getX() + 1, getY()) || dungeon.isBoulder(getX() + 1, getY())) || (getX() >= dungeon.getWidth())) {
    			return false;
    		}
    	}
    	return true;
    }
    
	public void pushBoulder(Dungeon dungeon, Player player) {
		if (this.movable(dungeon)) {
			String direction = player.getDirection();
			if (direction == "Down") {
				this.moveTo(player.getX(), player.getY() + 1);
			} else if (direction == "Up") {
				this.moveTo(player.getX(), player.getY() - 1);
			} else if (direction == "Right") {
				this.moveTo(player.getX() + 1, player.getY());
			} else if (direction == "Left") {
				this.moveTo(player.getX() - 1, player.getY());
			}
			
			int boulderX = this.getX();
			int boulderY = this.getY();
			List<Entity> curr_entities = dungeon.getEntitiesOnSquare(boulderX, boulderY);
			for (Entity e : curr_entities) {
				if (e.getType().equals("FloorSwitch")) {
					FloorSwitch fswitch = (FloorSwitch) e;
					fswitch.BoulderSwitch(dungeon, this);
				}
			}
			
		}	
	}
}
