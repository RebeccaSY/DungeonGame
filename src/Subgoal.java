//package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;


public class Subgoal implements Goal {
	private List<Goal_Condition> goal_conditions; 	
	
	public Subgoal() {
		this.goal_conditions = new ArrayList<>();
	}
	
	public boolean isComplete() {
		for (Goal_Condition gc: goal_conditions) {
			if (!gc.isComplete())
				return false; 
		}
		return true; 
	}
	
	public void addGoalCondition(Goal_Condition g) {
		this.goal_conditions.add(g);
	}
}
