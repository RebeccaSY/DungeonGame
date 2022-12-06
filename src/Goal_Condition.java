//package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
/**
 * A class to represent composite goals
 * @author stephen
 *
 */

public class Goal_Condition implements Goal {
	private Goal goal; 
	private Subgoal subgoal; 
	private String operator; 
	
	public Goal_Condition(String operator) {
		this.operator = operator;
	}
	
	//Loop through sub goals and take into account AND / OR operator
	public boolean isComplete() {
		if (operator.equals("NONE"))
			return goal.isComplete();
		else if (operator.equals("AND")) {
			return (goal.isComplete() && subgoal.isComplete());
		}
		else if (operator.equals("OR")) {
			return (goal.isComplete() || subgoal.isComplete());
		}
		
		return false; 
		/*
		//single basic goal
		if (goals.size() == 1 && operator.equals("NONE")) {
			System.out.println("I CAME HERE");
			return goals.get(0).isComplete(); 
		}//two basic goals (OR subgoal) 
		else if (goals.size() == 2 && composite_goals.isEmpty()) {
			//operator AND
			if (operator.equals("AND")) {
				for (Goal g: goals) {
					if (!g.isComplete()) 
						return false; 
				}
				return true; 
			//operator OR
			} else if (operator.equals("OR")) {
				for (Goal g: goals) {
					if (g.isComplete())
						return true;
				}
				return false; 
			}
		//one basic and one composite subgoal goal 
		} else if (goals.size() == 1 && composite_goals.size() == 1) {
			//operator AND
			if (operator.equals("AND")) {
				if (goals.get(0).isComplete() && composite_goals.get(0).
						isComplete() ) 
					return true;
				return false;
			//operator OR
			} else if (operator.equals("OR")) {
				if (goals.get(0).isComplete() || composite_goals.get(0).
						isComplete())
					return true;
				return false;
			}
		//two composite goals
		} else if (composite_goals.size() == 2) {
			//operator AND
			if (operator.equals("AND")) {
				if (composite_goals.get(0).isComplete() && composite_goals.
						get(1).isComplete())
					return true; 
				return false;
			//operator OR 
			} else if (operator.equals("OR")) {
				if (composite_goals.get(0).isComplete() || composite_goals.
						get(1).isComplete()) 
					return true;
				return false; 
			}
		}
		
		*/
	}
	
	public void setOperator(String o) {
		this.operator = o;
	}
	
	public void setGoal(Goal g) {
		this.goal = g; 
	}
	
	public void setSubgoal(Subgoal g) {
		this.subgoal = g; 
	}
}
