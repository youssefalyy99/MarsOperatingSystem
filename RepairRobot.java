/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

/**
 *
 * @author pc
 */
public class RepairRobot extends Robot{
    private static final int DRAIN_RATE = 3;
    private final int sparePartsPerRepair;
    private final int batteryPerRepair;

    public RepairRobot(String name, int id, String currentLocation) {
        super(name, id, currentLocation, DRAIN_RATE);
        this.sparePartsPerRepair = 2;
        this.batteryPerRepair = 5;
    }
    public int getSparePartsPerRepair() {
        return sparePartsPerRepair;
    }

    public int getBatteryPerRepair() {
        return batteryPerRepair;
    }
    // The colony must consume the spare parts BEFORE calling this (all-or-nothing)
    // Robots never touch colony resources.
    public boolean performRepair(Facility f) {
        if (f == null || !isUsable() || !hasEnoughBattery(batteryPerRepair)) {
            return false;   // nothing consumed
        }
        drainBattery(batteryPerRepair);
        f.setStatuesOPERATIONAL();   // change to match your Facility class
        System.out.println(getName() + " repaired " + f.getName() + ".");
        return true;
    }
    @Override
    public boolean canPerform(Mission m) {
        if (m == null || !isUsable()) {
            return false;
        }
        if (m.getType() != MissionType.REPAIR) {
            return false;
        }
        return hasEnoughBattery(batteryPerRepair);
    }

    @Override
    public boolean canHandle(Emergency e) {
        return e.getType() == EmergencyType.OXYGEN_LEAK;
    }

    @Override
    public boolean canOperateIn(Environment env) {
        return true;   // works inside facilities, unaffected by storms
    }

    @Override
    public String getStatusSummary() {
        return super.getStatusSummary()+ " | Repair cost: " + sparePartsPerRepair + " parts, "+ batteryPerRepair + " battery";
    }
}
