package korkmatik.main.controller.chart;

import java.util.Random;

import org.jfree.data.category.DefaultCategoryDataset;

import korkmatik.main.controller.VisualizationController;
import korkmatik.main.model.SettingsModel;
import korkmatik.main.service.SerialComService;
import korkmatik.main.service.db.DatabaseService;

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
		VisualizationController visualizationController = getVisualizationController();
		visualizationController.saveData(newValue, DatabaseService.TABLE_TEMPERATURE);
	}

}
