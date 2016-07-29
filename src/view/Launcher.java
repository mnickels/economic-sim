package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * The launcher for the program.
 * 
 * @author Mike Nickels | mnickels@uw.edu
 * @author Joshua Neighbarger | jneigh@uw.edu
 */
public final class Launcher extends Application {

	/** The path to the folder in this project directory that contains all FXML scene files. */
	private static final String FXML_PATH = "./res/fxml/";
	/** The top-most component in the Launcher. Used to give the scene to each of the controller methods. */
	@FXML private GridPane root;
	
	/**
	 * Launches the Game UI.
	 */
	@FXML
	private void handleLaunchGame() {
		Platform.runLater( () -> new Game().start(new Stage()) );
		((Stage) root.getScene().getWindow()).close();
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
		primaryStage.initStyle(StageStyle.UNDECORATED);
		try {
			final FileInputStream fxml = new FileInputStream(FXML_PATH + "Launcher.fxml");
			primaryStage.setScene(new Scene(new FXMLLoader().load(fxml)));
			fxml.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
