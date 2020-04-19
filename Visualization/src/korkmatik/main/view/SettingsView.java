package korkmatik.main.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import korkmatik.main.controller.SettingsController;
import korkmatik.main.model.Temperature;

public class SettingsView {

	private SettingsController controller;

	private JFrame frame;
	private JLabel fileLabel;
	private JButton buttonFileChooser;

	public SettingsView() {
		controller = new SettingsController();
	}

	public void start() {
		createWindow();
	}

	private String[] loadCommPorts() {		
		return controller.getSerialPortNames();
	}

	private void createWindow() {
		frame = new JFrame("Settings");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel centerPanel = new JPanel();
		
		createCommPortComboBox();
		createSaveDataDialog(centerPanel);
		createTemperatureDialog(centerPanel);
		createAcceptButton(centerPanel);
		
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));		
		
		frame.add(centerPanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void createAcceptButton(JPanel centerPanel) {
		JButton acceptButton = new JButton("OK");
		acceptButton.addActionListener(new AcceptButtonActionListener());
		centerPanel.add(acceptButton);
	}

	private void createCommPortComboBox() {
		JComboBox<String> commConnections = new JComboBox<String>(loadCommPorts());
		commConnections.addActionListener(new ComboBoxActionListener());
		frame.add(commConnections, BorderLayout.PAGE_START);
		
		controller.updateCommPort(commConnections.getSelectedItem().toString());
	}

	private void createSaveDataDialog(JPanel centerPannel) {
		JLabel label = new JLabel("Would you like to save the data?");
		
		// Creating yes and no radio buttons and grouping them
		JRadioButton radioBtnYes = new JRadioButton("Yes");
		radioBtnYes.setName(controller.getChoiceYes());
		radioBtnYes.setSelected(false);
		radioBtnYes.addActionListener(new SaveRadioButtonActionListener());
		
		JRadioButton radioBtnNo = new JRadioButton("No");
		radioBtnNo.setName(controller.getChoiceNo());
		radioBtnNo.setSelected(true);
		radioBtnNo.addActionListener(new SaveRadioButtonActionListener());
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(radioBtnYes);
		btnGroup.add(radioBtnNo);
		
		buttonFileChooser = new JButton("Choose file");
		buttonFileChooser.addActionListener(new FileChooserButtonActionListener());
		buttonFileChooser.setEnabled(false);

		fileLabel = new JLabel("File: ");
		
		centerPannel.add(label);
		centerPannel.add(radioBtnYes);
		centerPannel.add(radioBtnNo);
		centerPannel.add(buttonFileChooser);
		centerPannel.add(fileLabel);
	}
	
	private void createTemperatureDialog(JPanel centerPanel) {
		JLabel temperatureLabel = new JLabel("How should be the temperature displayed?");
		
		JRadioButton radioCelsius = new JRadioButton("Celsius");
		radioCelsius.setName(Temperature.CELSIUS.toString());
		radioCelsius.setSelected(true);
		radioCelsius.addActionListener(new TemperatureActionListener());
		
		JRadioButton radioFahrenheit = new JRadioButton("Fahrenheit");
		radioFahrenheit.setName(Temperature.FAHRENHEIT.toString());
		radioFahrenheit.addActionListener(new TemperatureActionListener());
		
		JRadioButton radioKelvin = new JRadioButton("Kelvin");
		radioKelvin.setName(Temperature.KELVIN.toString());
		radioKelvin.addActionListener(new TemperatureActionListener());
		
		ButtonGroup tempGroup = new ButtonGroup();
		tempGroup.add(radioCelsius);
		tempGroup.add(radioFahrenheit);
		tempGroup.add(radioKelvin);
		
		centerPanel.add(temperatureLabel);
		centerPanel.add(radioCelsius);
		centerPanel.add(radioFahrenheit);
		centerPanel.add(radioKelvin);
		
		controller.updateTemperatureType(Temperature.CELSIUS.toString());
	}

	private class ComboBoxActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<String> cb = (JComboBox<String>)e.getSource();
			controller.updateCommPort((String)cb.getSelectedItem());		
		}
	}

	private class SaveRadioButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton rb = (JRadioButton) e.getSource();
			buttonFileChooser.setEnabled(controller.updateSaveChoise(rb.getName()));
		}
		
	}

	private class FileChooserButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton)e.getSource();
			
			JFileChooser fc = new JFileChooser();
			int returnValue = fc.showSaveDialog(button.getParent());
			
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				System.out.println("File to save: " + file.getName());
				controller.setFile(file);
				fileLabel.setText("File: " + file.getName());
			}
		}

	}
	
	private class TemperatureActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {	
			JRadioButton rb = (JRadioButton)e.getSource();
			controller.updateTemperatureType(rb.getName());			
		}
		
	}
	
	private class AcceptButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO: try to connect to serial port and display error if cant connect
			
			VisualizationView next = new VisualizationView();
			frame.dispose();
		}
		
	}
}
