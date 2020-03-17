package M3;

import java.util.ArrayList;
import java.util.List;

public class ContainerStatus {
	
	
	private List<Double> tempTime = new ArrayList<Double>();
	private List<Double> humidityTime = new ArrayList<Double>();
	private List<Double> pressureTime = new ArrayList<Double>();
	private List<Integer> time = new ArrayList<Integer>();
	private Container container;
	
	public ContainerStatus(Container container) {
		this.container = container;
	}
	
	
	public void statusUpdate(double temp, double humidity, double pressure) {
		container.setTemp(temp);
		container.setHumidity(humidity);
		container.setPressure(pressure);
	}
	
	public void increment() {
		tempTime.add(container.getTemp());
		humidityTime.add(container.getHumidity());
		pressureTime.add(container.getPressure());
		time.add(time.size());
	}
	
	public void displayStatus() {
		System.out.print("Temperature(C): ");
		for(int i = 0; i<tempTime.size(); i++) {
			System.out.print(tempTime.get(i)+"  ");
		}
		System.out.println();
		
		
		System.out.print("Humidity:       ");
		for(int i = 0; i<humidityTime.size(); i++) {
			System.out.print(humidityTime.get(i) + "  ");
		}
		System.out.println();
		
		
		System.out.print("Pressure:       ");
		for(int i = 0; i<pressureTime.size(); i++) {
			System.out.print(pressureTime.get(i) + " ");
		}
		System.out.println();
		
		
		System.out.print("Time(Min):      ");
		for(int i = 0; i<time.size(); i++) {
			System.out.print(time.get(i)+"     ");
		}
		System.out.println();
	}

	
	
}
