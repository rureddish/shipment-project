package dk.dtu.gbar.gitlab.shipment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ContainerStatus {
    private ArrayList<Double> tempHistory;
    private ArrayList<Double> humidityHistory;
    private ArrayList<Double> pressureHistory; 
    private ArrayList<String> dateHistory;
    private DateTimeFormatter dateFormat;
    
	public ContainerStatus() {
		tempHistory = new ArrayList<Double>();
		humidityHistory = new ArrayList<Double>();
		pressureHistory = new ArrayList<Double>();
		dateHistory = new ArrayList<String>();
		dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	}
	
	
	public void updateTemp(double temp) {
		tempHistory.add(temp);
	}
	
	public void updateHumidity(double humidity) {
		humidityHistory.add(humidity);
	}
	
	public void updatePressure(double pressure) {
		pressureHistory.add(pressure);
	}
	
	public void updateDate() {
	    dateHistory.add(LocalDateTime.now().format(dateFormat));
	}


	
	////Getters & Setters
	public ArrayList<Double> getTempHistory() {
		return tempHistory;
	}


	public ArrayList<Double> getHumidityHistory() {
		return humidityHistory;
	}


	public ArrayList<Double> getPressureHistory() {
		return pressureHistory;
	}


	public ArrayList<String> getDateHistory() {
		return dateHistory;
	}
	


	



}
