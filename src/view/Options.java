package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.PropertiesManager;
import util.XMLProperties;

/**
 * Configures all of the options for the graphics and load paths of the application.
 * 
 * @author Joshua Neighbarger | jneigh@uw.edu
 */
public final class Options extends Application {

	private static final ObservableList<String> SUPPORTED_RESOLUTIONS = FXCollections.observableArrayList();
	
	private static String FXML_PATH;
	private static String RESOLUTION;
	private static Boolean FULLSCREEN;

	@FXML private ComboBox<String> resolutionComboBox;
	@FXML private CheckBox fullscreenCheckBox;
	
	static {
		final XMLProperties prop = PropertiesManager.getXML("./config/display.xml");
		RESOLUTION = prop.getString("resolution");
		FULLSCREEN = prop.getBoolean("fullscreen");
		
		FXML_PATH = PropertiesManager.getXML("./config/application.xml").getString("fxml-path");
	}

	public static void main(final String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			final FXMLLoader loader = new FXMLLoader();
			final Scene scene = new Scene(loader.load(new FileInputStream(FXML_PATH + "Options.fxml")));
			scene.getStylesheets().add((new File(FXML_PATH + "application.css")).toURI().toURL().toExternalForm());
			primaryStage.setScene(scene);
			((Options) loader.getController()).loadResolutions();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("Options");
		primaryStage.show();
	}
	
	private void loadResolutions() {
		final ArrayList<String> resList = new ArrayList<>();
		resList.add("800, 600");
		resList.add("1024, 768");
		resList.add("1024, 1080");
		resList.add("1280, 800");
		resList.add("1280, 1024");
		resList.add("1440, 1050");
		resList.add("1600, 1200");
		resList.add("1920, 1080");
		resList.add("1920, 1200");
		resList.add("2560, 1080");
		resList.add("2560, 1200");
		resList.add("2560, 1600");
		
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
		SUPPORTED_RESOLUTIONS.sort((a, b) -> {
			return Integer.parseInt(a.replaceAll("\\s", "").split(",")[0]) 
					- Integer.parseInt(b.replaceAll("\\s", "").split(",")[0]);
		});
		resolutionComboBox.setItems(SUPPORTED_RESOLUTIONS);
		resolutionComboBox.setValue(RESOLUTION);
		fullscreenCheckBox.setSelected(FULLSCREEN);
	}
	
	@FXML
	private void handleCancel() {
		((Stage) resolutionComboBox.getScene().getWindow()).close();
	}
	
	@FXML
	private void handleOkay() {
		XMLProperties prop = PropertiesManager.getXML("./config/display.xml");
		prop.setProperty("resolution", resolutionComboBox.getSelectionModel().getSelectedItem());
		prop.setProperty("fullscreen", fullscreenCheckBox.isSelected());
		
		PropertiesManager.putXML("./config/display.xml");
		
		((Stage) resolutionComboBox.getScene().getWindow()).close();
	}
	
	@FXML
	private void handleMinimizeWindow() {
		
	}

	@FXML
	private void handleMaximizeWindow() {
		
	}
	
	@FXML
	private void handleCloseWindow() {
		
	}
	
}
