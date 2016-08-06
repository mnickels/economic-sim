package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.PropertiesManager;
import util.XMLProperties;

/**
 * Configures all of the options for the graphics and load paths of the application.
 * 
 * @author Joshua Neighbarger | jneigh@uw.edu
 */
public final class Options extends Application {

	private static String FXML_PATH;
	private static int[] RESOLUTION;

	@FXML private BorderPane root;
	
	static {
		FXML_PATH = PropertiesManager.getXML("./config/application.xml").getString("fxml-path");
		
		final XMLProperties prop = PropertiesManager.getXML("./config/display.xml");
		RESOLUTION = prop.getIntArray("resolution");
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			final Scene scene = new Scene(new FXMLLoader().load(new FileInputStream(FXML_PATH + "Options.fxml")));
			scene.getStylesheets().add((new File(FXML_PATH + "application.css")).toURI().toURL().toExternalForm());
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		primaryStage.setTitle("Options");
		primaryStage.show();
	}
	
	@FXML
	private void handleCancel() {
		((Stage) root.getScene().getWindow()).close();
	}
	
}
