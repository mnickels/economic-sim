package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.PropertiesManager;

/**
 * The launcher for the program.
 * 
 * @author Mike Nickels | mnickels@uw.edu
 * @author Joshua Neighbarger | jneigh@uw.edu
 */
public final class Launcher extends Application {

	/** The path to the folder in this project directory that contains all FXML scene files. */
	private static String FXML_PATH;
	/** The top-most component in the Launcher. Used to give the scene to each of the controller methods. */
	@FXML private GridPane root;
	
	static {
		FXML_PATH = PropertiesManager.getXML("./config/application.xml").getProperty("fxml-path");
	}
	
	/**
	 * Launches the Game UI.
	 */
	@FXML
	private void handleLaunchGame() {
		Platform.runLater( () -> new Game().start(new Stage()) );
		((Stage) root.getScene().getWindow()).close();
	}
	
	/**
	 * 
	 */
	@FXML
	private void handleOptions() {
		Platform.runLater( () -> new Game().start(new Stage()) );
	}
	
	/**
	 * Opens the defined web page in the user's default browser.
	 */
	@FXML
	private void handleSupport() {
		getHostServices().showDocument("https://www.theUWhackers.com");
	}
	
	/**
	 * Exits the entire Application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			final Scene scene = new Scene(new FXMLLoader().load(new FileInputStream(FXML_PATH + "Launcher.fxml")));
			scene.getStylesheets().add((new File(FXML_PATH + "application.css")).toURI().toURL().toExternalForm());
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
	}
	
	/**
	 * Main method, starting point of the program.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
