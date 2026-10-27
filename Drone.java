/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

/**
 *
 * @author pc
 */
public class Drone extends Robot{
    private int flightRange;
    private boolean remoteControlled;
    private static final int DRAIN_RATE =4;
    
    public Drone(String name, int id, String currentLocation, int flightRange) {
    super(name, id, currentLocation, DRAIN_RATE);
    this.flightRange = flightRange;
    this.remoteControlled = true;
}

    public int getFlightRange() {
        return flightRange;
    }

    public boolean isRemoteControlled() {
        return remoteControlled;
    }
    
  public void performReconnaissance(String region) {
        System.out.println(getName() + " is performing aerial reconnaissance over " + region + ".");
    }
    
  @Override
    public boolean canPerform(Mission m) {
        if (m == null || !isUsable()) {
            return false;
        }
       // adjust to the mission types your team decides drones can do
        if (m.getType() != MissionType.EXPLORATION && m.getType() != MissionType.RESCUE) {
            return false;
        }
        return hasEnoughBattery(m.getDuration() * DRAIN_RATE);
    }
    @Override
    public boolean canHandle(Emergency e) {
        return false;// document in README: drones don't respond to emergencies
    }

    @Override
    public boolean canOperateIn(Environment env) {
        
        return !remoteControlled || !env.isCommunicationDisrupted();
// remote drones cannot be controlled during communication disruption
    }

    @Override
    public String getStatusSummary() {
        return super.getStatusSummary()+ " | Range: " + flightRange+ " | Remote: " + remoteControlled;
    }
}
