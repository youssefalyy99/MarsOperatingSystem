/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CrewMember extends Agent {

    private static final int MIN_USABLE_HEALTH = 30;
    private static final int MAX_USABLE_FATIGUE = 80;
    private static final int MAX_VALUE = 100;
    private static final int MIN_VALUE = 0;

    private ArrayList<String> Skills;
    private int FatigueLevel;
    private int HealthStatus;

    public CrewMember(ArrayList<String> Skills, int FatigueLevel, int HealthStatus, String Name, int id, boolean availability, String currentLocation) {
        super(Name, id, availability, currentLocation);
        this.Skills = new ArrayList<>(Skills);      // copy 3shan lw had bara el code my2darsh yghyar 
        this.FatigueLevel = clamp(FatigueLevel);    
        this.HealthStatus = clamp(HealthStatus);
    }

    public List<String> getSkills() {
        return Collections.unmodifiableList(Skills);
    }

    public void setSkills(List<String> Skills) {
        this.Skills = new ArrayList<>(Skills);
    }

    public int getFatigueLevel() {
        return FatigueLevel;
    }

    public int getHealthStatus() {
        return HealthStatus;
    }

    public void setFatigueLevel(int FatigueLevel) {
        this.FatigueLevel = clamp(FatigueLevel);
    }

    public void setHealthStatus(int HealthStatus) {
        this.HealthStatus = clamp(HealthStatus);
    }

    private int clamp(int value) {
        return Math.max(MIN_VALUE, Math.min(MAX_VALUE, value));
    }

    public void increaseFatigue(int amount) {
        if (amount < 0) {
            return;
        }
        FatigueLevel = clamp(FatigueLevel + amount);
    }

    public void decreaseFatigue(int amount) {
        if (amount < 0) {
            return;
        }
        FatigueLevel = clamp(FatigueLevel - amount);
    }

    public void heal(int amount) {
        if (amount < 0) {
            return;
        }
        HealthStatus = clamp(HealthStatus + amount);
    }

    public void damage(int amount) {
        if (amount < 0) {
            return;
        }
        HealthStatus = clamp(HealthStatus - amount);
    }

    @Override
    public boolean isUsable() {
        return HealthStatus > MIN_USABLE_HEALTH && FatigueLevel < MAX_USABLE_FATIGUE;
    }

    @Override
    public abstract boolean canPerform(Mission m);

    @Override
    public abstract boolean canHandle(Emergency e);
}
