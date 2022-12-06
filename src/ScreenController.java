//package unsw.dungeon;

import javafx.stage.Stage;
import java.util.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * A background controller
 * 
 */ 

public class ScreenController {
	public Stage stage;
	private HashMap<String, Pane> scenes = new HashMap<>();
	private Scene main;
	
	public ScreenController(Stage stage) {
		this.stage = stage;
	}
	
    protected void addScreen(String name, Pane pane){
    	scenes.put(name, pane);
    }

    protected void removeScreen(String name){
    	scenes.remove(name);
    }

    protected void activate(String name){
    	main.setRoot( scenes.get(name) );
    }
    
	public Stage getStage() {
		return this.stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
    protected void setMainScreen(Scene main){
    	this.main = main;
    }
}	

/*
 * public class ScreenController {
	private Stage stage;
	
	public ScreenController(Stage stage) {
		this.stage = stage;
	}
	
	public Stage getStage() {
		return this.stage;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
*/
