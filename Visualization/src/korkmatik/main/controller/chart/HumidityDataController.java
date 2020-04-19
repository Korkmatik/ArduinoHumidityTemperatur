package korkmatik.main.controller.chart;

import java.util.Random;

import korkmatik.main.controller.VisualizationController;
import korkmatik.main.service.db.DatabaseService;

public class HumidityDataController extends ChartDataController {

	public HumidityDataController() {
		super("percent");
	}

	@Override
	public Float getValue() {
		Float value = getSerialComService().getHumidity();
		return value;
	}

	@Override
	protected void saveData(Float newValue) {
		VisualizationController visualizationController = getVisualizationController();
		visualizationController.saveData(newValue, DatabaseService.TABLE_HUMIDITY);
	}

}
