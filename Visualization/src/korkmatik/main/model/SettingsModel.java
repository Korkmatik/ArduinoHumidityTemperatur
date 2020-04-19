package korkmatik.main.model;

import java.io.File;
import java.util.ArrayList;

import korkmatik.main.model.Temperature;
import korkmatik.utils.CelsiusToCelsiusConverter;
import korkmatik.utils.CelsiusToFahrenheitConverter;
import korkmatik.utils.CelsiusToKelvinConverter;
import korkmatik.utils.Converter; 

public class SettingsModel {

	private String commPortName;

	private boolean saveData;
	private File saveFile;

	private Temperature temperatureType;

	private static SettingsModel instance = null;
	
	
	public static SettingsModel getInstance() {
		if (instance == null) {
			instance = new SettingsModel();
		}
		
		return instance;
	}
	
	public String getCommPortName() {
		return commPortName;
	}

	public void setCommPortName(String commPortName) {
		this.commPortName = commPortName;
	}

	public boolean isSaveData() {
		return saveData;
	}

	public void setSaveData(boolean saveData) {
		this.saveData = saveData;
	}

	public Temperature getTemperatureType() {
		return temperatureType;
	}

	public void setTemperatureType(Temperature temperatureType) {
		this.temperatureType = temperatureType;
	}

	public File getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(File saveFile) {
		this.saveFile = saveFile;
	}
	
	public Converter getConverter() {
		Converter converter;
		switch (temperatureType) {
		case CELSIUS:
			converter = new CelsiusToCelsiusConverter();
			break;
		case KELVIN:
			converter = new CelsiusToKelvinConverter();
			break;
		case FAHRENHEIT:
			converter = new CelsiusToFahrenheitConverter();
			break;
		default:
			converter = null;
			break;
		}
		
		return converter;
	}

	private SettingsModel() { }
}
