//package unsw.dungeon;

public class FloorSwitch extends Entity {
	// FloorSwitch can be triggered or untriggered
	private boolean triggered;
	
	/**
     * Create a FloorSwitch positioned in square (x,y) with a status
     * @param x
     * @param y
     * @param triggered
     */
	public FloorSwitch(int x, int y) {
        super(x, y, "FloorSwitch");
        this.setTriggered(false);
    }
	
	public void trigger(FloorSwitch floorswitch) {
		floorswitch.setTriggered(true);
	}
	
	public void untrigger(FloorSwitch floorswitch) {
		floorswitch.setTriggered(false);
	}
	
	@Override
	public boolean movable(Dungeon dungeon) {
		return true;
	}

	public boolean isTriggered() {
		return triggered;
	}

	public void setTriggered(boolean triggered) {
		this.triggered = triggered;
	}
	
	// the switch is triggered
	public void BoulderSwitch(Dungeon dungeon, Boulder boulder) {
		this.setTriggered(true);
	}
}
