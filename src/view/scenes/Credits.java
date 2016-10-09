package view.scenes;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import view.StageController;

public class Credits {

	@FXML private TextArea text;
	
	@FXML
	private void handleBack() {
		StageController.setScene("MainMenu");
	}
	
}
