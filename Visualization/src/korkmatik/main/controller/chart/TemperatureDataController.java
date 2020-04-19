package korkmatik.main.controller.chart;

import java.util.Random;

import org.jfree.data.category.DefaultCategoryDataset;

import korkmatik.main.model.SettingsModel;
import korkmatik.main.service.SerialComService;

public class TemperatureDataController extends ChartDataController {
	
	Random random = new Random();	
	
	public TemperatureDataController() { 
		super("temperature");
	}

	@Override
	public Float getValue() {
		 Float value = getSerialComService().getTemperature();
		 return value;
	}

	@Override
	protected void saveData(Float newValue) {
		// TODO Auto-generated method stub
		
	}

}
