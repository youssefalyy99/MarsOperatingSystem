/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

/**
 *
 * @author Mega Store
 */
public abstract class Agent {
    private boolean Availability;
    private String CurrentLocation;

    public Agent(boolean Availability, String CurrentLocation) {
        this.Availability = Availability;
        this.CurrentLocation = CurrentLocation;
    }

    public boolean isAvailability() {
        return Availability;
    }

    public String getCurrentLocation() {
        return CurrentLocation;
    }
    
}
