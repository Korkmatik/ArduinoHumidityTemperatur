package korkmatik.main.view.chart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import korkmatik.main.controller.chart.ChartDataController;

public abstract class ChartView extends JPanel {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 5025131161902713651L;
	
	
	public ChartView(ChartDataController controller, String chartName, String yAxisName) {	
		JFreeChart lineChart = ChartFactory.createLineChart(
				chartName,
				"Seconds",
				yAxisName,
				controller.getDataset(),
				PlotOrientation.VERTICAL,
				true, true, false);
	
		ChartPanel chartPanel = new ChartPanel(lineChart);
		
		JButton buttonStart = new JButton("Start");
		buttonStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller != null && !controller.isAlive()) {
					controller.start();
				}
			}
		});
		
		JButton buttonStop = new JButton("Stop");
		buttonStop.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller != null && controller.isAlive()) {
					controller.stop();
				}
			}
		});
		
		add(chartPanel);
		add(buttonStart);
		add(buttonStop);
	}
}
