package view;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class Game extends Application {
	
	private static final String FXML_PATH = "./res/fxml/";
	private static final String FXML_EXT = ".fxml";
	private static final String GAME_TITLE = "Economic Simulator";
	
	private static Game GAME;
	
	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		GAME = this;
		this.primaryStage = primaryStage;
		primaryStage.setTitle(GAME_TITLE);
		setScene("MainMenu");
	}
	
	/**
	 * Changes the currently displayed scene to the scene indicated by the fxml file name sceneName.
	 * @param sceneName the name of the .fxml file to load as the current screen (exclude the .fxml file extension)
	 */
	public static void setScene(String sceneName) {
		GAME.primaryStage.hide();
		Scene scene = null;
		try {
			scene = new Scene((new FXMLLoader()).load(new FileInputStream(FXML_PATH + sceneName + FXML_EXT)));
		} catch (IOException e) {
			System.err.println("Error loading scene: " + sceneName);
			e.printStackTrace();
		}
		if (scene != null) {
			GAME.primaryStage.setScene(scene);
			GAME.primaryStage.centerOnScreen();
			GAME.primaryStage.show();
		}
	}

}
