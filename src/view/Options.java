package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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

	private static final ObservableList<int[]> SUPPORTED_RESOLUTIONS = FXCollections.observableArrayList();
	
	private static String FXML_PATH;
	private static int[] RESOLUTION;

	@FXML private BorderPane root;
	@FXML private ComboBox resolutionComboBox;
	
	static {
		final XMLProperties prop = PropertiesManager.getXML("./config/display.xml");
		RESOLUTION = prop.getIntArray("resolution");
		
		FXML_PATH = PropertiesManager.getXML("./config/application.xml").getString("fxml-path");
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
		loadResolutions();
		primaryStage.setTitle("Options");
		primaryStage.show();
	}
	
	private void loadResolutions() {
		final ArrayList<int[]> resList = new ArrayList<>();
		resList.add(new int[]{800, 600});
		resList.add(new int[]{1024, 768});
		resList.add(new int[]{1024, 1080});
		resList.add(new int[]{1280, 800});
		resList.add(new int[]{1280, 1024});
		resList.add(new int[]{1440, 1050});
		resList.add(new int[]{1600, 1200});
		resList.add(new int[]{1920, 1080});
		resList.add(new int[]{1920, 1200});
		resList.add(new int[]{2560, 1080});
		resList.add(new int[]{2560, 1200});
		resList.add(new int[]{2560, 1600});
		
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int[] res = new int[]{(int) screenSize.getWidth(), (int) screenSize.getHeight()};
		if (resList.contains(res)) {
			SUPPORTED_RESOLUTIONS.addAll(resList.subList(0, resList.indexOf(res)));
		} else {
			SUPPORTED_RESOLUTIONS.addAll(resList);
		}
		if (! SUPPORTED_RESOLUTIONS.contains(RESOLUTION)) {
			SUPPORTED_RESOLUTIONS.add(RESOLUTION);
			System.err.println("Unsupported native resolution detected.");
		}
		
		resolutionComboBox.setItems(SUPPORTED_RESOLUTIONS);
		resolutionComboBox.setValue(RESOLUTION);
	}
	
	@FXML
	private void handleCancel() {
		((Stage) root.getScene().getWindow()).close();
	}
	
}
