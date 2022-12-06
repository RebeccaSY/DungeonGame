//package unsw.dungeon;

/**
 * A class to represent the goal of reaching the exit of a maze. Observes Player.java.
 * @author stephen
 *
 */
public class Goal_Exit implements Goal, Observer{
	private Coordinates player = new Coordinates(-1, -1);
	private Coordinates exit = new Coordinates(-1, -1);
	
	//Returns true if the player's x-y coordinates are the same as the exit x-y coordinates.
	public boolean isComplete() {
		if (player.equalsXY(-1, -1) || exit.equalsXY(-1, -1))
			return false; 
		else {
			if (player.equals(exit))
				return true; 
		}
		return false;
	}
	
	@Override 
	public void update(Subject obj) {
		if (obj instanceof Player)
			update( (Player) obj);
		if (obj instanceof Dungeon)
			update( (Dungeon) obj);
	}

	//Updates the player coordinates from player. 
	public void update (Player obj) {
		this.player.setX(obj.getX());
		this.player.setY(obj.getY());
	}
	
	//Updates the exit coordinates from dungeon
	public void update (Dungeon obj) {
		this.exit.setX(obj.getExitX());
		this.exit.setY(obj.getExitY());
	}
}
