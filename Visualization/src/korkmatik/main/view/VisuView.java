package korkmatik.main.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import korkmatik.main.view.chart.TemperatureChartView;

public class VisuView extends JFrame {

	private static final long serialVersionUID = -5761651360850869935L;

	public VisuView() {
		super("Visualization");
		
		JPanel mainPanel = new JPanel();
		
		mainPanel.add(new TemperatureChartView());
		
		add(mainPanel, BorderLayout.CENTER);
		
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
