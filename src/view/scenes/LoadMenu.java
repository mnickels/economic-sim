package view.scenes;

import java.io.File;
import java.sql.Date;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import view.StageController;

public class LoadMenu {
	
	private static String SAVE_DIR;
	private static String SAVE_EXT;
	
	@FXML private Text slot1;
	@FXML private Text slot2;
	@FXML private Text slot3;
	@FXML private Text slot4;
	@FXML private Text slot5;
	
	static {
		SAVE_DIR = "./data/";
		SAVE_EXT = ".dat";
	}
	
	@FXML
	private void initialize() {
		loadSaves();
	}
	
	private void loadSaves() {
		System.out.println("LoadMenu: Need to implement save structure.");
		final File directory = new File(SAVE_DIR);
		if (! directory.isDirectory()) {
			throw new IllegalStateException("The specified savegame directory cannot be loaded as a directory.");
		}
		for (final File save : directory.listFiles()) {
			if (save.getName().equals("save_01" + SAVE_EXT)) {
				slot1.setText(new Date(save.lastModified()).toString());
			} else if (save.getName().equals("save_02" + SAVE_EXT)) {
				slot2.setText(new Date(save.lastModified()).toString());
			} else if (save.getName().equals("save_03" + SAVE_EXT)) {
				slot3.setText(new Date(save.lastModified()).toString());
			} else if (save.getName().equals("save_04" + SAVE_EXT)) {
				slot4.setText(new Date(save.lastModified()).toString());
			} else if (save.getName().equals("save_05" + SAVE_EXT)) {
				slot5.setText(new Date(save.lastModified()).toString());
			} else {
				System.err.println("Incompatable save found: " + save.getName());
			}
		}
	}
	
	private void deleteSave(final String saveName) {
		final File save = new File(SAVE_DIR + saveName + SAVE_EXT);
		if (save.exists()) {
			save.delete();
		}
	}
	
	@FXML
	private void handleBack() {
		StageController.setScene("MainMenu");
	}

	@FXML
	private void handleDeleteSave1() {
		deleteSave("save_01");
		slot1.setText("Slot 1");
	}
	
	@FXML
	private void handleDeleteSave2() {
		deleteSave("save_02");
		slot1.setText("Slot 2");
	}
	
	@FXML
	private void handleDeleteSave3() {
		deleteSave("save_03");
		slot1.setText("Slot 3");
	}
	
	@FXML
	private void handleDeleteSave4() {
		deleteSave("save_04");
		slot1.setText("Slot 4");
	}
	
	@FXML
	private void handleDeleteSave5() {
		deleteSave("save_05");
		slot1.setText("Slot 5");
	}
	
}
