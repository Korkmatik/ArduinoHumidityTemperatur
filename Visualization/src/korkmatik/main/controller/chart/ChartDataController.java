package korkmatik.main.controller.chart;

import org.jfree.data.category.DefaultCategoryDataset;

import korkmatik.main.db.service.SerialCommService;
import korkmatik.main.model.SettingsModel;

public abstract class ChartDataController extends Thread {

	private static DefaultCategoryDataset dataset;
	private SerialCommService serialCommService;
	
	private String yAxisName;
	
	private int i = 0;
	private int j = 0;
	
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
			// TODO: Get real values instead
			dataset.addValue(getValue(), yAxisName, Integer.toString(i));
			i += 3;

			if (i > 30) {
				dataset.removeValue(yAxisName, Integer.toString(j));
				j += 3;
			}			

			try {
				sleep(1000);
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
