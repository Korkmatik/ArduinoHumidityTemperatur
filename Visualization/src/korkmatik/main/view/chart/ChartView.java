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

	private static final long serialVersionUID = 5025131161902713651L;
	
	ChartDataController controller;
	
	public ChartView(ChartDataController controller, String chartName, String yAxisName) {
		this.controller = controller;
		
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
				/*if (getController() != null) {
					getController().removeData();
				}*/
				
				startButtonAction();
			}
		});
		
		JButton buttonStop = new JButton("Stop");
		buttonStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getController() != null && getController().isAlive()) {
					getController().halt();
				}
			}
		});
		
		add(chartPanel);
		add(buttonStart);
		add(buttonStop);
	}
	
	protected ChartDataController getController() {
		return controller;
	}



	protected synchronized void setController(ChartDataController controller) {
		this.controller = controller;
	}



	protected abstract void startButtonAction();
	
}
