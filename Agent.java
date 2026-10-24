/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

public abstract class Agent {
    private boolean availability;
    private String currentLocation;

    public Agent(boolean availability, String currentLocation) {
        this.availability = availability;
        this.currentLocation = currentLocation;
    }

    public boolean isAvailable() {
        return availability;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public abstract boolean canPerform(Mission m);

    public abstract boolean canHandle(Emergency e);
}
