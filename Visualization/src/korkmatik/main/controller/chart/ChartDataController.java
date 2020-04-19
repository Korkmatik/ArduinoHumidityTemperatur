package korkmatik.main.controller.chart;

import org.jfree.data.category.DefaultCategoryDataset;

import korkmatik.main.model.SettingsModel;
import korkmatik.main.service.SerialComService;

public abstract class ChartDataController extends Thread {

	private static DefaultCategoryDataset dataset;
	private SerialComService serialComService;
	
	private String yAxisName;
	
	private int currentX = 0;
	private int lowerX = 0;
	
	
	public ChartDataController(String yAxisName) {
		String port = SettingsModel.getInstance().getCommPortName();
		serialComService = new SerialComService(port);
		
		this.yAxisName = yAxisName;
	}
	
	public DefaultCategoryDataset getDataset()  {
		if (dataset == null) {
			dataset = new DefaultCategoryDataset();
		}

		return dataset;
	}

	public void run() {
		serialComService.openPort();
		
		while (true) {
			Float newValue = getValue();

			if (newValue != null) {
				dataset.addValue(newValue, yAxisName, Integer.toString(currentX));
				currentX += SerialComService.getInterval();

				if (currentX > 30) {
					dataset.removeValue(yAxisName, Integer.toString(lowerX));
					lowerX += SerialComService.getInterval();
				}
			}

			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public abstract Float getValue();

	protected SerialComService getSerialComService() {
		return serialComService;
	}

	public void halt() {
		serialComService.closeComPort();
		stop();
	}

	public void removeData() {
		dataset.clear();
	}

}
