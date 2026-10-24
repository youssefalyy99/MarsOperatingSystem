/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

/**
 *
 * @author Mega Store
 */
public abstract class CrewMember extends Agent {

    private String Name;
    private String Skills;
    private String FatigueLevel;
    private int IdentificationNumber;
    private String HealthStatus;

    public CrewMember(String Name, String Skills, String FatigueLevel, int IdentificationNumber, String HealthStatus, boolean Availability, String CurrentLocation) {
        super(Availability, CurrentLocation);
        this.Name = Name;
        this.Skills = Skills;
        this.FatigueLevel = FatigueLevel;
        this.IdentificationNumber = IdentificationNumber;
        this.HealthStatus = HealthStatus;
    }

    public String getName() {
        return Name;
    }

    public String getSkills() {
        return Skills;
    }

    public String getFatigueLevel() {
        return FatigueLevel;
    }

    public int getIdentificationNumber() {
        return IdentificationNumber;
    }

    public String getHealthStatus() {
        return HealthStatus;
    }

    @Override
    public abstract boolean canPerform(Mission m);

    @Override
    public abstract boolean canHandle(Emergency e);
    
}
