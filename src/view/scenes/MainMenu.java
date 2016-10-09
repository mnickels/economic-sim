package view.scenes;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.StageController;

/**
 * The Controller for the Main Menu scene.
 * 
 * @author Joshua Neighbarger | jneigh@uw.edu
 */
public class MainMenu {
	
	@FXML
	private void initialize() {
//		final MainMenu controller = loader.getController();
	}
	
	/** The root pane of the MainMenu.fxml file's generated scene. */
	@FXML private GridPane root;
	
	/**
	 * What to do when the new game button of the main menu is pressed.
	 */
	@FXML
	private void handleNewGame() {
		StageController.setScene("PlayerScene");
	}
	
	@FXML
	private void handleLoadMenu() {
		StageController.setScene("LoadMenu");
	}
	
	@FXML
	private void handleOptionsMenu() {
		StageController.setScene("OptionsMenu");
	}
	
	@FXML
	private void handleCredits() {
		StageController.setScene("Credits");
	}
	
	/**
	 * What to do when the exit button of the main menu is pressed.
	 */
	@FXML
	private void handleExit() {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

}
