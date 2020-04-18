package korkmatik.main.view.chart;

import korkmatik.main.controller.chart.TemperatureDataController;
import korkmatik.main.model.SettingsModel;

public class TemperatureChartView extends ChartView {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 3991263383665061901L;
	
	public TemperatureChartView() {
		super(new TemperatureDataController(), 
				"Temperature", 
				SettingsModel.getInstance().getTemperatureType().toString());
	}
}
