package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.List;

public class ContainerStatus {
    private List temp = new ArrayList<Double>();
    private List humidity = new ArrayList<Double>();
    private List pressure = new ArrayList<Double>();
    private List time = new ArrayList<Double>();

    public void statusUpdate(double newtemp, double newhumidity, double newpressure, int newtime) {
        temp.add(newtemp);
        humidity.add(newhumidity);
        pressure.add(newpressure);
        time.add(newtime);
    }

    public void displayStatus() {
        printTemperature();
        printHumidity();
        printPressure();
        printTime();
    }


    private void printTime() {
        System.out.print("Time(Min):      ");
        for (int i = 0; i < time.size(); i++) {
            System.out.print(time.get(i) + "     ");
        }
        System.out.println();
    }


    private void printTemperature() {
        System.out.print("Temperature(C): ");
        for (int i = 0; i < temp.size(); i++) {
            System.out.print(temp.get(i) + "  ");
        }
        System.out.println();
    }


    private void printHumidity() {
        System.out.print("Humidity:       ");
        for (int i = 0; i < humidity.size(); i++) {
            System.out.print(humidity.get(i) + "  ");
        }
        System.out.println();
    }


    private void printPressure() {
        System.out.print("Pressure:       ");
        for (int i = 0; i < pressure.size(); i++) {
            System.out.print(pressure.get(i) + " ");
        }
        System.out.println();
    }


}
