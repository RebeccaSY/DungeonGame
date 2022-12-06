//package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
    	//get dungeon dimensions from JSON file
        int width = json.getInt("width");
        int height = json.getInt("height");
        
        //create new dungeon
        Dungeon dungeon = new Dungeon(width, height);
        
        //get entities from JSON file
        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        //get goals from JSON file
        JSONObject jsonOriginalGoal = json.getJSONObject("goal-condition");
        Goal_Condition original_goal = new Goal_Condition("NONE");
        loadGoalCondition(dungeon, original_goal, jsonOriginalGoal);
        dungeon.setGoals(original_goal);
        return dungeon;
    }

    private void loadBasicGoal (Goal_Condition gc, String s) {
    	Goal g = null;
    	switch (s) {
        case "exit":
        	g = new Goal_Exit();
            break; 
        case "enemies":
        	g = new Goal_Enemies();
            break; 
        case "boulders":
        	g = new Goal_Boulders();
            break;
        case "treasure":
        	g = new Goal_Treasures();
            break;	
    	}
    	gc.setGoal(g);
 	}   
    	
	private void loadOperator(Goal_Condition gc, String s) {
		gc.setOperator(s);
	}
    
    private void loadSubGoal (Dungeon d, Goal_Condition gc, JSONArray s) {
    	Subgoal subgoal = new Subgoal(); 
        for (int i = 0; i < s.length(); i++) {
        	Goal_Condition gc1 = new Goal_Condition("NONE"); 
            loadGoalCondition(d, gc1, s.getJSONObject(i));
            subgoal.addGoalCondition(gc1); 
        }
        gc.setSubgoal(subgoal); 
    }
    
	private void loadGoalCondition(Dungeon d, Goal_Condition gc, JSONObject g) {
		//CASE 1: basic goal NO subgoals
        if (!g.has("subgoals")) {
        	loadBasicGoal(gc, g.getString("goal"));
        } else {
        //CASE 2: basic goal AND subgoals
        	loadOperator(gc, g.getString("goal"));
            JSONArray jsonSubgoal = g.getJSONArray("subgoals");
        	loadSubGoal(d, gc, jsonSubgoal); 
        }
	}	


    // loads entity to dungeon - load entity into dungeon and their images
    private void loadEntity(Dungeon dungeon, JSONObject json) {
    	//JSON object is an entity. Get type of entity, and its XY coords
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        //add entity to dungeon, load the image for the entity
        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;
        case "door":
            Door door = new Door(x, y, 1);
            onLoad(door);
            entity = door;
            break;
        case "key":
            Key key = new Key(x, y, 1);
            onLoad(key);
            entity = key;
            break;            
        case "boulder":
            Boulder b = new Boulder(x, y);
            onLoad(b);
            entity = b;
            break;             
        case "switch":
            FloorSwitch f = new FloorSwitch(x, y);
            onLoad(f);
            entity = f;
            break;  
        case "portal":
            Portal p = new Portal(x, y, 1);
            onLoad(p);
            entity = p;
            break; 
        case "enemy":
            Enemy e = new Enemy(dungeon, x, y);
            onLoad(e);
            entity = e;
            break; 
        case "sword":
            Sword s = new Sword(x, y);
            onLoad(s);
            entity = s;
            break;     
        case "invincibility":
            InvincibilityPotion ip = new InvincibilityPotion(x, y);
            onLoad(ip);
            entity = ip;
            break; 
        case "treasure":
            Treasure t = new Treasure(x, y);
            onLoad(t);
            entity = t;
            break; 
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Exit exit);
   
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(FloorSwitch floorswitch);
    
    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(InvincibilityPotion invincibility);

    public abstract void onLoad(Treasure treasure);
    
    // TODO Create additional abstract methods for the other entities

}
