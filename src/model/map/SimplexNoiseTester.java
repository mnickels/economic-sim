package model.map;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import util.PropertiesManager;

/**
 * Test GUI for the SimplexNoise generator class.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public class SimplexNoiseTester extends Application {
	
	/** The path to the folder in this project directory that contains all FXML scene files. */
	private static final String FXML_PATH;
	
	static {
		FXML_PATH = PropertiesManager.getXML("./config/application.xml").getString("fxml-path");
	}
	
	/** The SimplexNoise generator. */
	private SimplexNoise sng;
	/** Currently generated 2D array of simplex noise. */
	private float[][] noise;
	/** Currently displayed scale size. */
	private int currentScale;
	/** Reference to the Stage for resizing purposes. */
	private static Stage primaryStage;
	
	@FXML
	public Slider widthSlider;
	@FXML
	public Label widthLabel;
	@FXML
	public Slider heightSlider;
	@FXML
	public Label heightLabel;
	@FXML
	public Slider scaleSlider;
	
	@FXML
	public ImageView map;
	@FXML
	public TextField seed;
	@FXML
	public CheckBox randomize;
	@FXML
	public Label octavesLabel;
	@FXML
	public Slider octaves;
	@FXML
	public Label roughnessLabel;
	@FXML
	public Slider roughness;
	@FXML
	public Label frequencyLabel;
	@FXML
	public Slider frequency;
	
	/**
	 * Initializes this FX controller.
	 */
	@FXML
	public void initialize() {
		octavesLabel.textProperty().bind(Bindings.format("Ocvtaves: %.0f", octaves.valueProperty()));
        roughnessLabel.textProperty().bind(Bindings.format("Roughness: %.2f", roughness.valueProperty()));
        frequencyLabel.textProperty().bind(Bindings.format("Frequency: %.3f", frequency.valueProperty()));
        widthLabel.textProperty().bind(Bindings.format("%.0f", widthSlider.valueProperty()));
        heightLabel.textProperty().bind(Bindings.format("%.0f", heightSlider.valueProperty()));
        map.setOnMouseClicked((MouseEvent event) -> {
            try {
            	System.out.printf("(%d, %d)\t%f\n", (int) event.getX() / currentScale,
            			(int) event.getY() / currentScale,
            			noise[(int) event.getY() / currentScale][(int) event.getX() / currentScale]);
            } catch (ArrayIndexOutOfBoundsException e) { }
        });
	}
	
	/**
	 * Generates a new map from simplex noise and displays it.
	 */
	@FXML
	public void generateMap() {
		if (randomize.isSelected()) {
			sng = new SimplexNoise();
		} else {
			String s = seed.getText();
			try {
				long l = Long.parseLong(s);
				sng = new SimplexNoise(l);
			} catch (Exception e) {
				sng = new SimplexNoise();
			}
		}
		
		int w = (int) widthSlider.getValue();
		int h = (int) heightSlider.getValue();
		int o = (int) octaves.getValue();
		float r = (float) roughness.getValue();
		float f = (float) frequency.getValue();
		
		noise = sng.generateOctavedSimplexNoise(w, h, o, r, f);
		
		map.setImage(drawNoise(noise, (int) scaleSlider.getValue()));
		primaryStage.sizeToScene();
	}

	/**
	 * Interpret some noise as a heightmap.
	 * @param noise the simplex noise to interpret.
	 * @param scale the scaling to use on the image.
	 * @return An Image with the noise drawn onto it.
	 */
	public Image drawNoise(float[][] noise, int scale) {
		currentScale = scale;
		WritableImage img = new WritableImage(noise[0].length * scale, noise.length * scale);
		PixelWriter px = img.getPixelWriter();
		for (int y = 0; y < noise.length; y++) {
			for (int x = 0; x < noise[y].length; x++) {
				for (int j = 0; j < scale; j++) {
					for (int i = 0; i < scale; i++) {
						px.setColor(x * scale + i, y * scale + j, pickColor(noise[y][x]));
					}
				}
			}
		}
		return img;
	}
	
	/**
	 * Determines the color to assign to a value of noise.
	 * @param f the noise value to interpret.
	 * @return A Color representing this value's height.
	 */
	private Color pickColor(float f) {
		if (f < -1) return Color.RED;
		if (f < -0.6) return Color.DARKBLUE;
		if (f < -0.2) return Color.BLUE;
		if (f < 0.1) return Color.CYAN;
		if (f < 0.2) return Color.LIGHTYELLOW;
		if (f < 0.3) return Color.GREENYELLOW;
		if (f < 0.5) return Color.GREEN;
		if (f < 0.7) return Color.DIMGRAY;
		if (f < 0.8) return Color.LIGHTSLATEGRAY;
		if (f <= 1) return Color.WHITE;
		return Color.RED;
	}

	@Override
	public void start(Stage stage) {
		primaryStage = stage;
		try {
			final Scene scene = new Scene(new FXMLLoader().load(new FileInputStream(FXML_PATH + "NoiseTester.fxml")));
			scene.getStylesheets().add((new File(FXML_PATH + "application.css")).toURI().toURL().toExternalForm());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.show();

		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
	}
	
	/**
	 * Launch point for the tester Application.
	 * @param args command line arguments (unused in this implementation).
	 */
	public static void main(String[] args){
		launch(args);
	}

}
