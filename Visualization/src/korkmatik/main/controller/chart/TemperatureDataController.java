package korkmatik.main.controller.chart;

import java.util.Random;

import org.jfree.data.category.DefaultCategoryDataset;

import korkmatik.main.db.service.SerialCommService;
import korkmatik.main.model.SettingsModel;

public class TemperatureDataController extends Thread {
	
	/*Random random = new Random();	
	
	public TemperatureDataController() { 
		super("temperature");
	}

	@Override
	public float getValue() {
		// TODO: get value from service
		return random.nextFloat();
	}*/
	
	private DefaultCategoryDataset dataset;
	private SerialCommService serialCommService;
	
	private String yAxisName;
	
	private volatile boolean exit; 
	
	Random random = new Random();
	private int i = 0;
	private int j = 0;
	
	public TemperatureDataController() {
		String port = SettingsModel.getInstance().getCommPortName();
		serialCommService = new SerialCommService(port);
		
		dataset = new DefaultCategoryDataset();
		
		for (; i < 3; i++) {
			dataset.addValue(random.nextFloat(), "temperature", Integer.toString(i));
		}
	}
	
	public DefaultCategoryDataset getDataset()  {	
		return dataset;
	}
	
	@Override
	public void run() {
		exit = false;
		
		while (!exit) {
			// TODO: Get real values instead
			dataset.addValue(random.nextFloat(), "temperature", Integer.toString(i));
			i += 1;

			if (i > 10) {
				dataset.removeValue("temperature", Integer.toString(j));
				j += 1;
			}			

			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void halt() {
		exit = true;
	}
}
