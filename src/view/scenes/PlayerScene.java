package view.scenes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import model.Game;
import model.players.Player;
import model.map.MapType;
import model.resources.Resource;
import util.PropertiesManager;

/**
 * This FXML controller handles the player's view of an in-progress game.
 * 
 * @author Joshua Neighbarger | jneigh@uw.edu
 */
public class PlayerScene {

	/** The path to the folder in this project directory that contains all FXML scene files. */
	private static String FXML_PATH;
	
//	private Player player = Game.getInstance().getPlayer();
	
	@FXML private ImageView mapView;
	@FXML private Text moneyLabel;
	@FXML private Text foodLabel;
	@FXML private Text woodLabel;
	@FXML private Text oresLabel;
	@FXML private Text oilLabel;
	private double mouseX;
	private double mouseY;
	
	static {
		FXML_PATH = PropertiesManager.getXML("./config/application.xml").getString("fxml-path");
	}
	
	@FXML
	private void initialize() {
		loadMap();
		loadResources();
	}
	
	/**
	 * Loads all of the player's resources and shows them in the scene.
	 */
	private void loadResources() {
		System.out.println("PlayerScene: How to get player's resources? And how to get Player from Game?");
		Player player = Game.getInstance().getLocalPlayer();
		moneyLabel.setText(String.valueOf(player.resourceCount(Resource.MONEY)));
		foodLabel.setText(String.valueOf(player.resourceCount(Resource.FOOD)));
		woodLabel.setText(String.valueOf(player.resourceCount(Resource.WOOD)));
		oresLabel.setText(String.valueOf(player.resourceCount(Resource.ORES)));
		oilLabel.setText(String.valueOf(player.resourceCount(Resource.OIL)));
	}
	
	/**
	 * Loads the generated map into the scene.
	 */
	private void loadMap() {
		final AnimatedZoomOperator zoomOperator = new AnimatedZoomOperator();
		mapView.setImage(Game.getInstance().getMap(MapType.HEIGHT_MAP).drawMap());
		
//		mapView.setFitWidth(mapView.getScene().getWidth());
		
		mapView.setOnScroll(new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				double zoomFactor = 1.5;
				if (event.getDeltaY() <= 0) {
					zoomFactor = 1 / zoomFactor;
				}
				zoomOperator.zoom(mapView, zoomFactor, event.getSceneX(), event.getSceneY());
			}
		});

		mapView.setOnMousePressed(event -> {
			mouseX = mapView.getX() - event.getScreenX();
		    mouseY = mapView.getY() - event.getScreenY();
        });
		
        mapView.setOnMouseDragged(event -> {
        	mapView.translateXProperty().set(event.getScreenX() + mouseX);
        	mapView.translateYProperty().set(event.getScreenY() + mouseY);
        });
	}
	
	@FXML
	private void handlePauseMenu() {
		final Stage stage = new Stage();
		final FXMLLoader loader = new FXMLLoader();
		try {
			final Scene scene = new Scene(loader.load(new FileInputStream(FXML_PATH + "PauseMenu.fxml")));
			scene.getStylesheets().add((new File(FXML_PATH + "application.css")).toURI().toURL().toExternalForm());
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
		
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
	}
	
	@FXML
	private void handleKeyPressed(final KeyEvent event) {
		System.out.println(event.getCode().getName());
		switch (event.getCode()) {
		case ESCAPE:
			handlePauseMenu();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 
	 * @author NOT JOSHUA - RIPPED FROM WEB
	 *
	 */
	public class AnimatedZoomOperator {

	    private Timeline timeline;

	    public AnimatedZoomOperator() {         
	         this.timeline = new Timeline(60);
	    }

	    public void zoom(Node node, double factor, double x, double y) {    
	        // determine scale
	        double oldScale = node.getScaleX();
	        double scale = oldScale * factor;
	        double f = (scale / oldScale) - 1;

	        // determine offset that we will have to move the node
	        Bounds bounds = node.localToScene(node.getBoundsInLocal());
	        double dx = (x - (bounds.getWidth() / 2 + bounds.getMinX()));
	        double dy = (y - (bounds.getHeight() / 2 + bounds.getMinY()));

	        // timeline that scales and moves the node
	        timeline.getKeyFrames().clear();
	        timeline.getKeyFrames().addAll(
	            new KeyFrame(Duration.millis(200), new KeyValue(node.translateXProperty(), node.getTranslateX() - f * dx)),
	            new KeyFrame(Duration.millis(200), new KeyValue(node.translateYProperty(), node.getTranslateY() - f * dy)),
	            new KeyFrame(Duration.millis(200), new KeyValue(node.scaleXProperty(), scale)),
	            new KeyFrame(Duration.millis(200), new KeyValue(node.scaleYProperty(), scale))
	        );
	        timeline.play();
	    }
	}
	
}
