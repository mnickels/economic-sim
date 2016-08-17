package view.scenes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.PropertiesManager;

public class PlayerScene {

	private static String FXML_PATH;
	
	static {
		FXML_PATH = PropertiesManager.getXML("./config/application.xml").getString("fxml-path");
	}
	
	@FXML
	private void handlePauseMenu() {
		final Stage stage = new Stage();
		try {
			final Scene scene = new Scene(new FXMLLoader().load(new FileInputStream(FXML_PATH + "PauseMenu.fxml")));
			scene.getStylesheets().add((new File(FXML_PATH + "application.css")).toURI().toURL().toExternalForm());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
	}
	
}
