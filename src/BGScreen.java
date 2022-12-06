//package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A background screen
 * 
 */ 
public class BGScreen {
	private Stage stage;
	private FXMLLoader fxmlLoader;
	private String title;
	//private String map;
	private DungeonController controller;
	
	public BGScreen(Stage stage, String fxml) {
		this.stage = stage;
		this.title = "Dungeon Game";
		this.fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
	}
	
	public void start(ScreenController controller) throws IOException {
		fxmlLoader.setController(controller);
		Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        stage.setScene(scene);
		stage.setTitle(title);
        stage.show();
	}
	
	public void start(DungeonController controller) throws IOException {
		fxmlLoader.setController(controller);
		Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        stage.setScene(scene);
		stage.setTitle(title);
        stage.show();
	}
}
