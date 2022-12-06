//package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class HomeController extends ScreenController {

    @FXML
    private Button basicBtn;
    
    @FXML
    private Button complexBtn;

    @FXML
    private Button quitBtn;
        
    public HomeController(Stage stage) {
    	super(stage);
    }
    
    @FXML
    /**
     * To choose a basic goal
     * @param event
     * @throws IOException
     */ 
	void handlebasicBtn(ActionEvent event) throws IOException {
    	//Stage stage = new Stage();
    	BGScreen basicscreen = new BGScreen(stage, "BasicGoal.fxml");
    	BasicController bc = new BasicController(stage);
    	basicscreen.start(bc);
	}
    
    @FXML
    /**
     * To choose a complex goal
     * @param event
     * @throws IOException
     */ 
    void handlecomplexBtn(ActionEvent event) throws IOException {
    	//Stage stage = new Stage();
    	BGScreen complexscreen = new BGScreen(stage, "ComplexGoal.fxml");
    	ComplexController cc = new ComplexController(stage);
    	complexscreen.start(cc);
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
