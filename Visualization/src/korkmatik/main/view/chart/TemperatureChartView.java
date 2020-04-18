package korkmatik.main.view.chart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import korkmatik.main.controller.chart.TemperatureDataController;
import korkmatik.main.model.SettingsModel;

public class TemperatureChartView extends JPanel {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 3991263383665061901L;
	
	JFreeChart lineChart;
	ChartPanel chartPanel;
	TemperatureDataController controller;
	
	Thread controllerThread;
	
	public TemperatureChartView() {
		/*super(new TemperatureDataController(), 
				"Temperature", 
				SettingsModel.getInstance().getTemperatureType().toString(),
				parent);*/

		this.controller = new TemperatureDataController();
				
		lineChart = ChartFactory.createLineChart(
				"Temperature",
				"Seconds",
				"°C",
				controller.getDataset(),
				PlotOrientation.VERTICAL,
				true, true, false);
	
		chartPanel = new ChartPanel(lineChart);
		add(chartPanel);
		
		JButton buttonStart = new JButton("Start");
		buttonStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controllerThread != null) {
					return;
				}
				
				controllerThread = new Thread(controller);
				controllerThread.start();
			}
		});
		
		JButton buttonStop = new JButton("Stop");
		buttonStop.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controllerThread != null) {
					controller.halt();
				}
			}
		});

		add(buttonStart);
		add(buttonStop);
		setVisible(true);
	}
}
