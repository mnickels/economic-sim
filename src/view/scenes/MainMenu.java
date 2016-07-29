package view.scenes;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.Game;

/**
 * The Controller for the Main Menu scene.
 * 
 * @author Mike Nickels | mnickels@uw.edu
 */
public class MainMenu {
	
	/** The root pane of the MainMenu.fxml file's generated scene. */
	@FXML private GridPane root;
	
	/**
	 * What to do when the new game button of the main menu is pressed.
	 */
	@FXML
	public void handleNewGame() {
		Game.setScene("PlayerScene");
	}
	
	/**
	 * What to do when the exit button of the main menu is pressed.
	 */
	@FXML
	public void handleExit() {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

}
