package view.scenes;

import javafx.fxml.FXML;
import view.StageController;

public class LoadMenu {
	
	@FXML
	private void handleBack() {
		StageController.setScene("MainMenu");
	}

}
