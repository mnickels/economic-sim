package view;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main controller for the application.
 * Manages the switching between JavaFX scenes.
 * 
 * @author Mike Nickels | mnickels@uw.edu
 * @author Joshua Neighbarger | jneigh@uw.edu
 */
public final class Game extends Application {
	
	/** The path to the folder in this project directory that contains all FXML scene files. */
	private static final String FXML_PATH = "./res/fxml/";
	/** The extension used for FXML files. */
	private static final String FXML_EXT = ".fxml";
	/** The title to show in the main application window. */
	private static final String GAME_TITLE = "Economic Simulator";
	
	/** Instance of the current Game object being used in the application. */
	private static Game GAME;
	
	/** The primary Stage for the application UI. */
	private Stage primaryStage;
	
	/**
	 * Constructor for the Game class. Should only be called via {@link #launch(Class, String...)}.
	 */
	public Game() {
		GAME = this;
	}

	@Override
	public void start(Stage primaryStage) {
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
		}
		GAME.primaryStage.show();
	}

}
