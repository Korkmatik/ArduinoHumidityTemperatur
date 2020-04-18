package korkmatik.main.db.service;

import com.fazecast.jSerialComm.SerialPort;

public class SerialCommService {

	private String portName;
	
	public SerialCommService(String serialPortName) {
		portName = serialPortName;
	}
	
	public static SerialPort[] getSerialPorts() {
		return SerialPort.getCommPorts();
	}
	
	public static int getNumberSerialPorts() {
		return SerialPort.getCommPorts().length;
	}
}
