package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import util.PropertiesManager;
import util.XMLProperties;

/**
 * Main controller for the application.
 * Manages the switching between JavaFX scenes.
 * 
 * @author Mike Nickels | mnickels@uw.edu
 * @author Joshua Neighbarger | jneigh@uw.edu
 */
public final class StageController extends Application {
	
	/** The path to the folder in this project directory that contains all FXML scene files. */
	private static String FXML_PATH;
	/** The extension used for FXML files. */
	private static String FXML_EXT;
	/** The title to show in the main application window. */
	private static String GAME_TITLE;
	/** Instance of the current Game object being used in the application. */
	private static StageController GAME;
	
	/** The primary Stage for the application UI. */
	private Stage primaryStage;
	
	static {
		final XMLProperties prop = PropertiesManager.getXML("./config/application.xml");
		FXML_PATH = prop.getString("fxml-path");
		FXML_EXT = prop.getString("fxml-ext");
		GAME_TITLE = prop.getString("game-title");
	}

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
		Scene scene = null;
		try {
			scene = new Scene(new FXMLLoader().load(new FileInputStream(FXML_PATH + sceneName + FXML_EXT)));
			scene.getStylesheets().add((new File(FXML_PATH + "application.css")).toURI().toURL().toExternalForm());
		} catch (IOException e) {
			System.err.println("Error loading scene: " + sceneName);
			e.printStackTrace();
		}
		if (scene != null) {
			GAME.primaryStage.setScene(scene);
			GAME.primaryStage.centerOnScreen();
		}
		GAME.primaryStage.show();
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        GAME.primaryStage.setX((primScreenBounds.getWidth() - GAME.primaryStage.getWidth()) / 2);
        GAME.primaryStage.setY((primScreenBounds.getHeight() - GAME.primaryStage.getHeight()) / 2);
	}

}
