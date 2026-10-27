/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author pc
 */
public abstract class Robot extends Agent {
    public enum HealthStatus {
    OPERATIONAL,
    DAMAGED,
    DESTROYED
}
   private static final int MAX_BATTERY = 100;
    private static final int RESERVE = 10;
    private static final int IDLE_RECHARGE = 2;
    private int batteryLevel;
    private HealthStatus healthStatus;
    protected int batteryDrainPerUnit;

    public Robot(String name, int id, String currentLocation, int batteryDrainPerUnit) {
    super(name, id, true, currentLocation); 
    this.batteryLevel = MAX_BATTERY;
    this.healthStatus = HealthStatus.OPERATIONAL;
    this.batteryDrainPerUnit = batteryDrainPerUnit; 
}

    public void setBatteryLevel(int batteryLevel) {
        if (batteryLevel < 0 || batteryLevel > MAX_BATTERY) {
            throw new IllegalArgumentException("Battery must be between 0 and " + MAX_BATTERY);
        }
        this.batteryLevel = batteryLevel;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }
    
    public void drainBattery(int amount) {
    if (amount <= 0) {
        return;
    }
    batteryLevel = Math.max(0, batteryLevel - amount);
}
 public void recharge(int amount) {
    if (amount <= 0) {
        return;
    }
    batteryLevel = Math.min(MAX_BATTERY, batteryLevel + amount);
}
    
    public boolean hasEnoughBattery(int required) {
    return batteryLevel >= required + RESERVE;
}
    public void damage(){
        if (healthStatus == HealthStatus.OPERATIONAL) {
            healthStatus = HealthStatus.DAMAGED;
        }
                }
    public void repair(){
    if (healthStatus == HealthStatus.DAMAGED) {
            healthStatus = HealthStatus.OPERATIONAL;
        }
    }
  public void tick() {
    if (!isAvailable()) {
        drainBattery(batteryDrainPerUnit);
    } else {
        recharge(IDLE_RECHARGE); // charge a little
    }
}
    @Override
    public boolean isUsable(){
        return healthStatus == HealthStatus.OPERATIONAL && batteryLevel > 0;
    }
    @Override
    public String getStatusSummary(){
    String summary = getName() + " (ID " + getId() + ")"+ " | Location: " + getCurrentLocation()+ " | Battery: " + batteryLevel + "/" + MAX_BATTERY + " | Health: " + healthStatus;
        if (batteryLevel < 20) {
            summary += " | LOW BATTERY";
        }
        return summary;
    }
    @Override
    public void display() {
        System.out.println(getStatusSummary());
    }
    public boolean canOperateIn(Environment env){
    
    }
    @Override
    public boolean canPerform(Mission m){}
    @Override
    public boolean canHandle(Emergency e){}
}
