package korkmatik.main.view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import korkmatik.main.view.chart.HumidityChartView;
import korkmatik.main.view.chart.TemperatureChartView;

public class VisualizationView extends JFrame {

	private static final long serialVersionUID = -5761651360850869935L;

	public VisualizationView() {
		super("Visualization");

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		mainPanel.add(new TemperatureChartView());
		mainPanel.add(new HumidityChartView());
		
		JScrollPane scrollPanel = new JScrollPane(mainPanel);
		
		add(scrollPanel, BorderLayout.CENTER);
		
		pack();
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
