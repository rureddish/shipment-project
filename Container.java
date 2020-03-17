package M3;



public class Container {
	private double temp;
	private double humidity;
	private double pressure;
	private String journeyID;
	private String containerID;
	
	public Container(String containerID) {
		this.containerID = containerID;
	}
	
	public void setJourneyID(String journeyID) {
		this.journeyID = journeyID;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	
	public double getTemp() {
		return this.temp;
	}
	public double getHumidity() {
		return this.humidity;
	}
	public double getPressure() {
		return this.pressure;
	}
	public String getJourneyID() {
		return this.journeyID;
	}
	public String getContainerID() {
		return this.containerID;
	}
	
}

