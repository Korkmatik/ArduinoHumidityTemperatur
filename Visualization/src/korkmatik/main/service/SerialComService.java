package korkmatik.main.service;

import java.io.IOException;
import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import korkmatik.main.model.SettingsModel;
import korkmatik.utils.Converter;

public class SerialComService {

	public static final String HUMIDITY_PROTOCOL = "HUMI";
	public static final String TEMPERATURE_PROTOCOL = "TEMP";
	public static final int INTERVAL = 3; // Seconds
	
	private String portName;
	private SerialPort port;
	
	private volatile Float humidity;
	private volatile Float temperature;
	
	private Converter converter;
	
	public SerialComService(String serialPortName) {
		portName = serialPortName;
		port = null;
		
		humidity = null;
		temperature = null;
		
		converter = SettingsModel.getInstance().getConverter();
	}
	
	public static SerialPort[] getSerialPorts() {
		return SerialPort.getCommPorts();
	}
	
	public static int getNumberSerialPorts() {
		return SerialPort.getCommPorts().length;
	}
	
	public boolean openPort() {
		SerialPort ports[] = getSerialPorts();
		for(int i = 0; i < getNumberSerialPorts(); i++) {
			if (ports[i].getSystemPortName().equals(portName)) {
				port = ports[i];
				break;
			}
		}
		
		if (port == null) {
			return false;
		}
		
		if (port.isOpen()) {
			return false;
		}

		port.openPort();
		
		port.addDataListener(new SerialPortDataListener() {
			
			@Override
			public void serialEvent(SerialPortEvent event) {
				if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
					return;
				}
				
				InputStream comPortStream = port.getInputStream();
				
				// Reading data
				int readOneByteASCII;
				String message = "";
				char data;
				
				System.out.println("Reading data");
				do {
					try {
						readOneByteASCII = comPortStream.read();
						data = (char)readOneByteASCII;
						System.out.print(data);
						message += data;
					} catch (IOException e) {
						data = '\0';
					}
				} while (data != ')');
				
				// Parsing data
				message = message.replace("(", "");
				message = message.replace(")", "");
				String[] messageParts = message.split("#");
				
				String type = messageParts[0];
				String numberValue = messageParts[1];
				
				System.out.println("Type: " + type);
				System.out.println("Number: " + numberValue);
				
				float value;
				try {
					value = Float.parseFloat(numberValue);
				} catch (NumberFormatException e) {
					return;
				}
				
				
				switch(type) {
				case SerialComService.TEMPERATURE_PROTOCOL:
					temperature = value;
					break;
				case SerialComService.HUMIDITY_PROTOCOL:
					humidity = value;
					break;
				default:
					break;
				}
			}
			
			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
			}
		});
		
		return true;
	}
	
	public Float getHumidity() {
		Float retVal = humidity;
		humidity = null;
		return retVal;
	}
	
	public Float getTemperature() {
		Float retVal = null;
		
		if (temperature != null) {
			retVal = converter.convert(temperature);
			temperature = null;
		}
		
		return retVal;
	}
	
	public boolean closeComPort() {
		if (!port.isOpen()) {
			return false;
		}
		
		port.closePort();
		return true;
	}

	public static int getInterval() {
		return SerialComService.INTERVAL;
	}
}
