package korkmatik.main.model;

import java.io.File;

import korkmatik.main.model.Temperature; 

public class SettingsModel {

	private String commPortName;

	private boolean saveData;
	private File saveFile;

	private Temperature temperatureType;

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

}
