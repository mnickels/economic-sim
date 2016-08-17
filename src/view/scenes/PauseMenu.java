package view.scenes;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.StageController;

public class PauseMenu {

	@FXML private GridPane root;
	
	@FXML
	private void handleSaveQuit() {
		System.err.println("Save on exit not implemented.");
		((Stage) root.getScene().getWindow()).close();
		StageController.setScene("MainMenu");
	}
	
	@FXML
	private void handleResume() {
		((Stage) root.getScene().getWindow()).close();
	}
	
}
