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
    public boolean canHandle(Emergency e) {
        return false;
    }

    @Override
    protected String getRole() {
        return "Engineer";
    }
}
