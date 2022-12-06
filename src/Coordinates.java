//package unsw.dungeon;

import java.lang.Math;

public class Coordinates {
	int x;
	int y;
	
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distanceBetweenTwoPoints(Coordinates c) {
    	double s1 = Math.abs(this.x - c.getX());
    	double s2 = Math.abs(this.y - c.getY());
    	return Math.hypot(s1, s2);
    }
    
    public boolean equals(Coordinates c) {
    	if (this.x == c.getX() && this.y == c.getY())
    		return true;
    	return false; 
    }
    public boolean equalsXY(int x, int y) {
    	if (this.x == x && this.y == y)
    		return true;
    	return false; 
    }
    
    
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y; 
    }
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public void setY(int y) {
    	this.y = y; 
    }
}
