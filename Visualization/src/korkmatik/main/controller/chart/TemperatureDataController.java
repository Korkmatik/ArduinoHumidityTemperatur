package korkmatik.main.controller.chart;

import java.util.Random;

import org.jfree.data.category.DefaultCategoryDataset;

import korkmatik.main.model.SettingsModel;
import korkmatik.main.service.SerialCommService;

public class TemperatureDataController extends ChartDataController {
	
	Random random = new Random();	
	
	public TemperatureDataController() { 
		super("temperature");
	}

	@Override
	public float getValue() {
		// TODO: get value from service
		return random.nextFloat();
	}

}
