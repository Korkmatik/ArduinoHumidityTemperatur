package korkmatik.main.controller.chart;

import java.util.Random;

public class HumidityDataController extends ChartDataController {

	public HumidityDataController() {
		super("percent");
	}

	@Override
	public Float getValue() {
		Random random = new Random();
		return random.nextFloat();
	}

}
