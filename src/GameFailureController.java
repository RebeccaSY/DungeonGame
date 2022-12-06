//package unsw.dungeon;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class GameFailureController extends ScreenController {
	
	@FXML
    private Button backBtn;
	
	@FXML
    private Button quitBtn;
	
	public GameFailureController(Stage stage) {
		super(stage);
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
    
    @FXML
    /**
     * To quit the game
     * @param event
     * @throws IOException
     */ 
    void handlequitBtn(ActionEvent event) {
    	System.exit(0);
    }    
    
}
