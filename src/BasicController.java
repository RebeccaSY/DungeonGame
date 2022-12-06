//package unsw.dungeon;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * A controller for choosing basic goals
 * @author Rebecca Wang
 *
 */
public class BasicController extends ScreenController {

	@FXML
    private Button exitBtn;
	
	@FXML
    private Button enemyBtn;
	
	@FXML
    private Button bswitchBtn;
	
	@FXML
    private Button treasureBtn;
	
    @FXML
    private Button backBtn;

	//private BGScreen BGscreen;
        
    public BasicController(Stage stage) {
    	super(stage);
    }

    
    @FXML
    void handleexitBtn(ActionEvent event) throws IOException {
    	DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("maze.json");
        DungeonController controller = dungeonLoader.loadController();
    	BGScreen dungeonScreen = new BGScreen(getStage(), "DungeonView.fxml");
    	dungeonScreen.start(controller);
	}   
    
    @FXML
    void handlebswitchBtn(ActionEvent event) throws IOException {
    	DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("boulders.json");
        DungeonController controller = dungeonLoader.loadController();
    	BGScreen dungeonScreen = new BGScreen(getStage(), "DungeonView.fxml");
    	dungeonScreen.start(controller);
	}    
    
    @FXML
    void handleenemyBtn(ActionEvent event) throws IOException {
    	DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("enemies.json");
        DungeonController controller = dungeonLoader.loadController();
    	BGScreen dungeonScreen = new BGScreen(getStage(), "DungeonView.fxml");
    	dungeonScreen.start(controller);
	}    
    
    @FXML
    void handletreasureBtn(ActionEvent event) throws IOException {
    	DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("treasures.json");
        DungeonController controller = dungeonLoader.loadController();
    	BGScreen dungeonScreen = new BGScreen(getStage(), "DungeonView.fxml");
    	dungeonScreen.start(controller);
	}    
    
    @FXML
    /**
     * To return to Home
     * @param event
     * @throws IOException
     */ 
	void handlebackBtn(ActionEvent event) throws IOException {
    	//Stage stage = new Stage();
    	BGScreen homescreen = new BGScreen(stage, "Home.fxml");
    	HomeController hc = new HomeController(stage);
    	homescreen.start(hc);
	}
}
