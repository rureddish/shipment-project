package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.List;

public class ContainerStatus {
    private List<Double> temp = new ArrayList<>();
    private List<Double> humidity = new ArrayList<>();
    private List<Double> pressure = new ArrayList<>();
    private List<Integer> time = new ArrayList<>();    
   

	public void statusUpdate(double newtemp, double newhumidity, double newpressure, Integer newtime) {
        temp.add(newtemp);
        humidity.add(newhumidity);
        pressure.add(newpressure);
        time.add(newtime);
    }



}
