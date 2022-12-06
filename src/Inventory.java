//package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent player inventory.
 * @author stephen
 *
 */
public class Inventory {
	//ArrayList of Items to represent a player's inventory
	private List<Item> items;
	
	public Inventory() {
		this.items = new ArrayList<>();
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public Item findtype(String type) {
		for (Item i : items) {
			if (i.getName().equals(type)) {
				return i;
			}
		}
		return null;
	}

	public int findUsage(Item item) {
		return item.getAdditional();
	}
	
	public int getNumSwords() {
		int k = 0;
		for (Item i: items) {
			if (i.getName() == "Sword")
				k++;
		}
		return k;
	}
	
}
