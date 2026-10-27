/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

import java.util.ArrayList;

/**
 *
 * @author Mega Store
 */
public class Engineer extends CrewMember {

    public Engineer(ArrayList<String> Skills, int FatigueLevel, int HealthStatus, String Name, int id, boolean availability, String currentLocation) {
        super(Skills, FatigueLevel, HealthStatus, Name, id, availability, currentLocation);
    }

    @Override
    public boolean canPerform(Mission m) {
        return false;
    }

    @Override
    protected String getRole() {
        return "Engineer";
    }

    public void RepairLabEquipment(Laboratory lab){
        if(isUsable()){
            lab.repairEquipment();
            increaseFatigue(10);
            setCurrentLocation("LABORATORY");
        }else{
            System.out.println("System Message : Engineer is CANNOT Be Used . . . ");
        }
    }

    public void extinguishLabFire(Laboratory lab){
        if(isUsable()){
            lab.extinguishFire();
            increaseFatigue(10);
            setCurrentLocation("LABORATORY");
        }else{
            System.out.println("System Message : Engineer is CANNOT Be Used . . . ");
        }
    }

    public void sealHabitatLeak(Habitat hab){
        if(isUsable()){
        hab.sealLeak();
        increaseFatigue(10);
        setCurrentLocation("HABITAT");
        }else{
            System.out.println("System Message : Engineer is CANNOT Be Used . . . ");
        }
    }

    public void setLinkComm(CommunicationStation com){
        if(isUsable()){
            com.setLink(true);
            increaseFatigue(10);
            setCurrentLocation("COMMUNICATIONSTATION");
        }else{
            System.out.println("System Message : Engineer is CANNOT Be Used . . . ");
        }
    }
}
