//package unsw.dungeon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
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

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController extends ScreenController {

    @FXML
    private GridPane squares;

    @FXML
    private Button RestartBtn;
    
    @FXML
    private Button ExitBtn;
        
    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    public DungeonController(Stage stage, Dungeon dungeon, List<ImageView> initialEntities) {
    	super(stage);
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        
        Image ground = new Image("/floor_32x32.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }

    @FXML
    public void handleKeyPress(KeyEvent event) throws IOException {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            checkGameOver();
            break;
        case DOWN:
            player.moveDown();
            checkGameOver();
            break;
        case LEFT:
            player.moveLeft();
            checkGameOver();
            break;
        case RIGHT:
            player.moveRight();
            checkGameOver();
            break;
        default:
            break;
        }
    }

    public void checkGameOver() throws IOException {
    	if (player.alive().getValue().intValue() == 0) {
    		BGScreen failureScreen = new BGScreen(getStage(), "GameFailure.fxml");
    		GameFailureController gfc = new GameFailureController(getStage());
    		failureScreen.start(gfc);
    	} else if (dungeon.getEntities().contains(player) == false || dungeon.getGoals().isComplete()) {
    		BGScreen successScreen = new BGScreen(getStage(), "GameSuccess.fxml");
    		GameSuccessController gsc = new GameSuccessController(getStage());
    		successScreen.start(gsc);
    	}
    }

    @FXML
	public void handleRestartBtn(ActionEvent event) throws IOException {
    	BGScreen screen = new BGScreen(stage, "BasicGoal.fxml");
    	BasicController hc = new BasicController(stage);
    	screen.start(hc);
	}
   
    @FXML
	public void handleExitBtn(ActionEvent event) throws IOException {
    	//Stage stage1 = new Stage();
    	BGScreen homescreen = new BGScreen(stage, "Home.fxml");
    	HomeController hc = new HomeController(stage);
    	homescreen.start(hc);
	}
}

