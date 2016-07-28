package view.scenes;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.Game;

public class MainMenu {
	
	@FXML GridPane root;

	public MainMenu() {
		
	}
	
	@FXML
	public void handleNewGame() {
		
	}
	
	@FXML
	public void handleExit() {
		Stage stage = (Stage) root.getScene().getWindow();
		stage.close();
	}

}
