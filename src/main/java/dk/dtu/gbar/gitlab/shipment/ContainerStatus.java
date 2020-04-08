package dk.dtu.gbar.gitlab.shipment;

public class ContainerStatus {
    private double temp;
    private double humidity;
    private double pressure; 
//    private Date date;
    
	public ContainerStatus(double temp, double humidity, double pressure) {
		this.temp = temp;
		this.humidity = humidity;
		this.pressure = pressure;
//		this.date = date;
	}

	public double getTemp() {
		return temp;
	}
//
//	public void setTemp(double temp) {
//		this.temp = temp;
//	}
//
	public double getHumidity() {
		return humidity;
	}
//
//	public void setHumidity(double humidity) {
//		this.humidity = humidity;
//	}
//
	public double getPressure() {
		return pressure;
	}
//
//	public void setPressure(double pressure) {
//		this.pressure = pressure;
//	}



}
