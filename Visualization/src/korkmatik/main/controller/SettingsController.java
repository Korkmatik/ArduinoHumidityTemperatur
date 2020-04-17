package korkmatik.main.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import korkmatik.main.model.SettingsModel;
import korkmatik.main.model.Temperature;

public class SettingsController {
	
	private SettingsModel settingsModel;
	
	private HashMap<Boolean, String> saveChoices;
	private boolean isFileSet;

	public SettingsController() {
		settingsModel = new SettingsModel();
		
		saveChoices = new HashMap<Boolean, String>();
		saveChoices.put(true, "Yes");
		saveChoices.put(false, "No");
		
		isFileSet = false;
	}

	public void updateCommPort(String selectedItem) {
		settingsModel.setCommPortName(selectedItem);
		System.out.println("Set comm port to: " + selectedItem);
	}

	public boolean updateSaveChoise(String choice) {
		if (saveChoices.containsValue(choice)) {			
			for (Entry<Boolean, String> entry : saveChoices.entrySet()) {
				if (entry.getValue().equals(choice)) {
					System.out.println("Setting data to: " + entry.getKey());
					settingsModel.setSaveData(entry.getKey());
					return entry.getKey();
				}
			}
		}
		
		return false;
	}
	
	public void updateTemperatureType(String temperature) {
		settingsModel.setTemperatureType(Temperature.valueOf(temperature));
		System.out.println("Temperature set to: " + temperature.toString());
	}

	public String getChoiceYes() {
		return saveChoices.get(true);
	}

	public String getChoiceNo() {
		return saveChoices.get(false);
	}

	public void setFile(File file) {
		settingsModel.setSaveFile(file);
		isFileSet = true;
	}

}
