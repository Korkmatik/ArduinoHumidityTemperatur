package korkmatik.main.controller.chart;

import java.util.Random;

import korkmatik.main.controller.VisualizationController;

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
	}

}
