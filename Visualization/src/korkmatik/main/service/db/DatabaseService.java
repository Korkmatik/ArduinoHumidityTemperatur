package korkmatik.main.service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class DatabaseService {

	private static final String CONNECTION_STRING = "jdbc:sqlite:";
	public static final String TABLE_HUMIDITY = "humidity";
	public static final String TABLE_TEMPERATURE = "temperature";
	
	private boolean isOpen;
	
	private String path;
	
	private Connection connection;
	
	public DatabaseService(String path) {
		this.path = path;
		isOpen = false;
	}
	
	public void connect() throws SQLException {
		String url = DatabaseService.CONNECTION_STRING + path; 
		connection = DriverManager.getConnection(url);
		isOpen = true;
	}
	
	public void close() throws SQLException {
		connection.close();
		isOpen = false;
		System.out.println("Database closed");
	}
	
	public void createTablesIfNotExists() throws SQLException {
		String sqlTemperature = String.format(
				"CREATE TABLE IF NOT EXISTS %s (id integer PRIMARY KEY, dataTime datetime, value REAL)",
				DatabaseService.TABLE_TEMPERATURE);
		
		String sqlHumidity = String.format(
				"CREATE TABLE IF NOT EXISTS %s (id integer PRIMARY KEY, dataTime datetime, value REAL)",
				DatabaseService.TABLE_HUMIDITY);
		
		Statement stmt = connection.createStatement();
		stmt.execute(sqlTemperature);
		stmt.execute(sqlHumidity);
		stmt.close();
	}
	
	public void saveTemperatureValue(float value) throws SQLException {
		 save(value, DatabaseService.TABLE_TEMPERATURE);
	}
	
	public void saveHumidityValue(float value) throws SQLException {
		 save(value, DatabaseService.TABLE_HUMIDITY);
	}
	
	public boolean isOpen() {
		return isOpen;
	}

	private void save(float value, String table) throws SQLException {
		String sql = String.format("INSERT INTO %s (dataTime, value) VALUES (?,?)", table);
		
		PreparedStatement pstmt = connection.prepareStatement(sql);
		pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
		pstmt.setFloat(2, value);
		pstmt.close();
		
		System.out.println("Saved data");
	}
}
