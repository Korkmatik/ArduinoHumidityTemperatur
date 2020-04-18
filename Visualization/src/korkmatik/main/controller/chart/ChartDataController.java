package korkmatik.main.controller.chart;

import org.jfree.data.category.DefaultCategoryDataset;

import korkmatik.main.model.SettingsModel;
import korkmatik.main.service.SerialCommService;

public abstract class ChartDataController extends Thread {

	private static DefaultCategoryDataset dataset;
	private SerialCommService serialCommService;
	
	private String yAxisName;
	
	private int currentX = 0;
	private int lowerX = 0;
	
	public ChartDataController(String yAxisName) {
		String port = SettingsModel.getInstance().getCommPortName();
		serialCommService = new SerialCommService(port);
		
		this.yAxisName = yAxisName;
	}
	
	public DefaultCategoryDataset getDataset()  {
		if (dataset == null) {
			dataset = new DefaultCategoryDataset();
		}

		return dataset;
	}

	public void run() {
		while (true) {
			Float newValue = getValue();

			if (newValue != null) {
				dataset.addValue(newValue, yAxisName, Integer.toString(currentX));
				currentX += 3;

				if (currentX > 30) {
					dataset.removeValue(yAxisName, Integer.toString(lowerX));
					lowerX += 3;
				}
			}

			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public abstract float getValue();

	protected SerialCommService getSerialCommService() {
		return serialCommService;
	}

}
