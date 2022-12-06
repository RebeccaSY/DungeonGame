//package unsw.dungeon;

import java.util.List;

/**
 * A class to represent different kinds of interactions
 * @author z5190769 Rebecca Wang
 *
 */


public class Interaction {
	public Interaction() {}
	
	/* MOVED TO ENEMY.JAVA
	// player collides with enemy
	public void PEinteract(Dungeon dungeon, Player player, Enemy enemy) {
		if (player.isInvincible()) {
			dungeon.removeEntity(enemy);
		} else if (player.hasSword()) {
			dungeon.removeEntity(enemy);
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
		}
	}
	*/
	
	/* MOVED TO Player.java
	// player picks up item
	public void playerPickup(Dungeon dungeon, Player player, Entity entity) {
		if (entity.getType().equals("Sword")) {
			if (player.hasSword() == false) {
				Item swordItem = new Item("Sword", 5);
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
			if (player.isInvincible() == false) {
				Item Potion = new Item("InvincibilityPotion", 5);
				Inventory new_inv = dungeon.getInventory();
				new_inv.addItem(Potion);
				dungeon.setInventory(new_inv);
				dungeon.removeEntity(entity);
			}
		} else if (entity.getType().equals("Key")) {
			if (player.hasKey() == false) {
				Key new_key = (Key) entity;
				Item KeyItem = new Item("Key", new_key.getID());
				Inventory new_inv = dungeon.getInventory();
				new_inv.addItem(KeyItem);
				dungeon.setInventory(new_inv);
				dungeon.removeEntity(entity);
			}
		}
	}
	*/
	
	/* MOVED TO Boulder.java
	// player pushes boulder
	public void pushBoulder(Dungeon dungeon, Player player, Boulder boulder) {
		if (boulder.movable(dungeon)) {
			
			String direction = player.getDirection();
			if (direction == "Down") {
				boulder.moveTo(player.getX(), player.getY() + 1);
			} else if (direction == "Up") {
				boulder.moveTo(player.getX(), player.getY() - 1);
			} else if (direction == "Right") {
				boulder.moveTo(player.getX() + 1, player.getY());
			} else if (direction == "Left") {
				boulder.moveTo(player.getX() - 1, player.getY());
			}
			
			int boulderX = boulder.getX();
			int boulderY = boulder.getY();
			List<Entity> curr_entities = dungeon.getEntitiesOnSquare(boulderX, boulderY);
			for (Entity e : curr_entities) {
				if (e.getType().equals("FloorSwitch")) {
					FloorSwitch fswitch = (FloorSwitch) e;
					BoulderSwitch(dungeon, boulder, fswitch);
				}
			}
			
		}	
	}
	*/
	
	/* MOVED TO Door.java
	// player opens the door with key
	public void playerOpenDoor(Dungeon dungeon, Player player, Door door) {
		Inventory new_inv = dungeon.getInventory();
		Item key = new_inv.findtype("Key");
		dungeon.removeEntity(door);
		new_inv.removeItem(key);
		dungeon.setInventory(new_inv);
	}
	*/
	
	/* MOVED TO Portal.java
	// player reaches a portal
	public void throughPortal(Dungeon dungeon, Player player, Portal portal) {
		List<Entity> entities = dungeon.getEntities();
		for (Entity e : entities) {
			if (e.getType().equals("Portal")) {
				Portal pair_portal = (Portal) e;
				if ((pair_portal.getX() != portal.getX() && pair_portal.getY() != portal.getY()) 
					&& (pair_portal.getID() == portal.getID())) {
					player.moveTo(pair_portal.getX(), pair_portal.getY());
				}
			}			
		}
	}
	*/
	
	/* MOVED TO Exit.java
	// player reaches the exit
	public void throughExit(Dungeon dungeon, Player player, Exit exit) {
		// game over, check goals
		dungeon.removeEntity(player);
	}
	
	*/
	
	
	/* MOVED TO FloorSwitch.java
	// the switch is triggered
	public void BoulderSwitch(Dungeon dungeon, Boulder boulder, FloorSwitch fswitch) {
		fswitch.setTriggered(true);
	}
	*/
}
