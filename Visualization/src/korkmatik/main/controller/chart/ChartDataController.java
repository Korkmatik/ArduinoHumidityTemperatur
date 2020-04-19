package korkmatik.main.controller.chart;

import java.util.concurrent.atomic.AtomicBoolean;

import org.jfree.data.category.DefaultCategoryDataset;

import com.fazecast.jSerialComm.SerialPort;

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
		serialCommService.openPort();
		
		while (true) {
			Float newValue = getValue();

			if (newValue != null) {
				dataset.addValue(newValue, yAxisName, Integer.toString(currentX));
				currentX += SerialCommService.getInterval();

				if (currentX > 30) {
					dataset.removeValue(yAxisName, Integer.toString(lowerX));
					lowerX += SerialCommService.getInterval();
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

	protected SerialCommService getSerialCommService() {
		return serialCommService;
	}

	public void halt() {
		serialCommService.closeComPort();
		stop();
	}

	public void removeData() {
		dataset.clear();
	}

}
