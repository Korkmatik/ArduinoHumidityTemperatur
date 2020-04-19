package korkmatik.main.view.chart;

import korkmatik.main.controller.chart.HumidityDataController;

public class HumidityChartView extends ChartView {

	private static final long serialVersionUID = -8940310625512188598L;

	public HumidityChartView() {
		super(new HumidityDataController(), "Humidity", "%");
	}
	
	@Override
	protected void startButtonAction() {
		setController(new HumidityDataController());
		getController().start();
	}

}
