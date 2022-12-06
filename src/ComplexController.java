//package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ComplexController extends ScreenController {

	@FXML
    private Button advancedBtn;
	
	@FXML
    private Button advanced1Btn;
	
	@FXML
    private Button backBtn;

    public ComplexController(Stage stage) {
		super(stage);
	}


    @FXML
    void handleadvancedBtn(ActionEvent event) throws IOException {
    	DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced.json");
        DungeonController controller = dungeonLoader.loadController();
    	BGScreen dungeonScreen = new BGScreen(getStage(), "DungeonView.fxml");
    	dungeonScreen.start(controller);
	}
    
    @FXML
    void handleadvanced1Btn(ActionEvent event) throws IOException {
    	DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("advanced1.json");
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
    	BGScreen homescreen = new BGScreen(stage, "Home.fxml");
    	HomeController hc = new HomeController(stage);
    	homescreen.start(hc);
    }
}
