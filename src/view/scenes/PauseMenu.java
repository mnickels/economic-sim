package view.scenes;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.StageController;

/**
 * FXML Controller for the PauseMenu.
 * 
 * @author Joshua Neighbarger | jneigh@uw.edu
 */
public class PauseMenu {

	@FXML private GridPane root;
	
	@FXML
	private void handleSaveQuit() {
		System.out.println("Save on exit not implemented.");
		((Stage) root.getScene().getWindow()).close();
		StageController.setScene("MainMenu");
	}
	
	@FXML
	private void handleResume() {
		((Stage) root.getScene().getWindow()).close();
	}
	
	@FXML
	private void handleKeyPressed(final KeyEvent event) {
		switch (event.getCode()) {
		case ESCAPE:
			handleResume();
			break;
		default:
			break;
		}
	}
	
}
