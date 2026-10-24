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
   
    private static final int RESERVE = 10;
    private int batteryLevel;
    private String healthStatus;
    protected int batteryDrainPerUnit;

    public Robot(String name, int id, String currentLocation, int batteryDrainPerUnit) {
    super(name, id, true, currentLocation); 
    this.batteryLevel = 100;
    this.healthStatus = "Operational";
    this.batteryDrainPerUnit = batteryDrainPerUnit; 
}

    public void setBatteryLevel(int batteryLevel) {
        if(batteryLevel<=100 && batteryLevel >=0)
        this.batteryLevel = batteryLevel;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public String getHealthStatus() {
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
    batteryLevel = Math.min(100, batteryLevel + amount);
}
    
    public boolean hasEnoughBattery(int required) {
    return batteryLevel >= required + RESERVE;
}
    public void damage(){
        healthStatus="Damaged";
                }
    public void repair(){
    healthStatus="Operational";
    }
  public void tick() {
    if (!isAvailable()) {
        drainBattery(batteryDrainPerUnit);
    } else {
        recharge(2);// charge a little
    }
}
    @Override
    public boolean isUsable(){}
    @Override
    public String getStatusSummary(){}
    @Override
    public void display() {
        System.out.println(getStatusSummary());
    }
    public boolean canOperateIn(Environment e){
    
    }
    @Override
    public boolean canPerform(Mission m){}
    @Override
    public boolean canHandle(Emergency e){}
}
