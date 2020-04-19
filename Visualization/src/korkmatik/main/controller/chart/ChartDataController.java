package korkmatik.main.controller.chart;

import org.jfree.data.category.DefaultCategoryDataset;

import korkmatik.main.controller.VisualizationController;
import korkmatik.main.model.SettingsModel;
import korkmatik.main.service.SerialComService;

public abstract class ChartDataController extends Thread {

	private static DefaultCategoryDataset dataset;
	private SerialComService serialComService;
	
	private String yAxisName;
	
	private int currentX = 0;
	private int lowerX = 0;
	
	private VisualizationController visualizationController;
	private SettingsModel settingsModel;
	
	public ChartDataController(String yAxisName) {
		settingsModel = SettingsModel.getInstance();
		String port = settingsModel.getCommPortName();
		serialComService = new SerialComService(port);
		
		this.yAxisName = yAxisName;
		
		if (settingsModel.isSaveData()) {
			visualizationController = new VisualizationController();
			visualizationController.setup();
		}
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
				
				if (settingsModel.isSaveData()) {
					saveData(newValue);
				}
			}

			try {
				sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected abstract void saveData(Float newValue);

	public abstract Float getValue();

	protected SerialComService getSerialComService() {
		return serialComService;
	}

	public void halt() {
		serialComService.closeComPort();
		visualizationController.closeDatabase();
		stop();
	}

	public void removeData() {
		dataset.clear();
	}
	
	protected VisualizationController getVisualizationController() {
		return this.visualizationController;
	}

}
