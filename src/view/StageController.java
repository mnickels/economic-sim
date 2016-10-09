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
import javafx.stage.StageStyle;
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
	/** The resolution or stage dimensions to be used. */
	private static int[] RESOLUTION;
	/** The boolean if the stage should be full-screen or not. */
	private static boolean FULLSCREEN;
	
	/** The primary Stage for the application UI. */
	private Stage primaryStage;
	
	static {
		XMLProperties prop;
		
		prop = PropertiesManager.getXML("./config/application.xml");
		FXML_PATH = prop.getString("fxml-path");
		FXML_EXT = prop.getString("fxml-ext");
		GAME_TITLE = prop.getString("game-title");
		
		prop = PropertiesManager.getXML("./config/display.xml");
		RESOLUTION = prop.getIntArray("resolution");
		FULLSCREEN = prop.getBoolean("fullscreen");
	}

	@Override
	public void start(Stage primaryStage) {
		GAME = this;
		this.primaryStage = primaryStage;
		primaryStage.setHeight(RESOLUTION[1]);
		primaryStage.setWidth(RESOLUTION[0]);
		primaryStage.setTitle(GAME_TITLE);
		setScene("MainMenu");
	}
	
	/**
	 * Changes the currently displayed scene to the scene indicated by the fxml file name sceneName.
	 * @param sceneName the name of the .fxml file to load as the current screen (exclude the .fxml file extension)
	 */
	public static void setScene(String sceneName) {
		final FXMLLoader loader = new FXMLLoader();
		try {
			final Scene scene = new Scene(loader.load(new FileInputStream(FXML_PATH + sceneName + FXML_EXT)));
			scene.getStylesheets().add((new File(FXML_PATH + "application.css")).toURI().toURL().toExternalForm());
			GAME.primaryStage.setScene(scene);
			GAME.primaryStage.setFullScreen(FULLSCREEN); // Change to exact resolution later to get rid of message... Setting resolution as setWidth() and setHeight() caused scaling problems with Windows's integrated scaling options (100% - 250%).
			GAME.primaryStage.hide(); // Necessary to allow proper resize of the stage... :(
			GAME.primaryStage.show();
		} catch (IOException e) {
			System.err.println("Error loading scene: " + sceneName);
			e.printStackTrace();
		}
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        GAME.primaryStage.setX((primScreenBounds.getWidth() - GAME.primaryStage.getWidth()) / 2);
        GAME.primaryStage.setY((primScreenBounds.getHeight() - GAME.primaryStage.getHeight()) / 2);
	}

}
