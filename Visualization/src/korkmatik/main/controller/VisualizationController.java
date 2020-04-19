package korkmatik.main.controller;

import java.sql.SQLException;

import korkmatik.main.model.SettingsModel;
import korkmatik.main.service.db.DatabaseService;

public class VisualizationController {

	private SettingsModel settingsModel;
	private DatabaseService dbService;	
	
	public VisualizationController() {
		settingsModel = SettingsModel.getInstance();
	}
	
	public void setup() {
		if (settingsModel.isSaveData()) {
			dbService = new DatabaseService(settingsModel.getSaveFile().getAbsolutePath());
			try {
				dbService.connect();
				dbService.createTablesIfNotExists();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveData(float value, String db) {
		System.out.println("Trying to save data " + value + " to db: " + db);
		switch (db) {
		case DatabaseService.TABLE_HUMIDITY:
			try {
				dbService.saveHumidityValue(value);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case DatabaseService.TABLE_TEMPERATURE:
			try {
				dbService.saveTemperatureValue(value);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	
	public void closeDatabase() {
		try {
			dbService.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
