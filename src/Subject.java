//package unsw.dungeon;

/**
 * An class interface to represent Subjects in the subject-observer pattern. 
 * @author stephen
 *
 */
public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers(); 
}
