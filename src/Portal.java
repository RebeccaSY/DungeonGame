//package unsw.dungeon;

import java.util.List;

/**
 * A class to represent entity Portal
 * @author z5190769 Rebecca
 * @param <portalID>
 *
 */


public class Portal extends Entity {
	
	// each pair of portals have the same ID
	private int portalID;
	
	public Portal(int x, int y, int portalID) {
        super(x, y, "Portal");
        this.portalID = portalID;
    }
	
    public int getID() {
    	return portalID;
    }
    
    public void setID(int ID) {
    	this.portalID = ID;
    }
    
    @Override
    public boolean movable(Dungeon dungeon) {
    	return true;
    }

	public void throughPortal(Dungeon dungeon, Player player) {
		List<Entity> entities = dungeon.getEntities();
		for (Entity e : entities) {
			if (e.getType().equals("Portal")) {
				Portal pair_portal = (Portal) e;
				if ((pair_portal.getX() != this.getX() && pair_portal.getY() != this.getY()) 
					&& (pair_portal.getID() == this.getID())) {
					player.moveTo(pair_portal.getX(), pair_portal.getY());
				}
			}			
		}
	}
    
}
