package korkmatik.main.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import com.fazecast.jSerialComm.SerialPort;

import korkmatik.main.model.SettingsModel;
import korkmatik.main.model.Temperature;
import korkmatik.main.service.SerialCommService;

public class SettingsController {
	
	private SettingsModel settingsModel;
	
	private HashMap<Boolean, String> saveChoices;

	public SettingsController() {
		settingsModel = SettingsModel.getInstance();
		
		saveChoices = new HashMap<Boolean, String>();
		saveChoices.put(true, "Yes");
		saveChoices.put(false, "No");
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
	}

	public String[] getSerialPortNames() {
		int numberPorts = SerialCommService.getNumberSerialPorts();
		String[] portNames = new String[numberPorts];
		
		int i = 0; 
		for (SerialPort p : SerialCommService.getSerialPorts()) {
			System.out.println(p.getSystemPortName());
			portNames[i] = p.getSystemPortName();
			i += 1;
		}

		return portNames;
	}
}
