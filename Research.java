/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package lab5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Research extends Mission {

    private final String experimentName;
    private final List<String> requiredEquipment;

    public Research(
            int id,
            String name,
            int duration,
            Resources resourceRequest,
            List<Facility> targetFacilities,
            String requiredCapability,
            int minBattery,
            String experimentName,
            List<String> requiredEquipment) {

        super(id, name, duration, resourceRequest,
              targetFacilities, requiredCapability,
              minBattery, MissionType.RESEARCH);

        if (experimentName == null || experimentName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Experiment name cannot be empty.");
        }

        if (requiredEquipment == null) {
            throw new IllegalArgumentException(
                    "Required equipment list cannot be null.");
        }

        this.experimentName = experimentName;
        this.requiredEquipment = new ArrayList<>(requiredEquipment);
    }

    public String getExperimentName() {
        return experimentName;
    }

    public List<String> getRequiredEquipment() {
        return Collections.unmodifiableList(requiredEquipment);
    }

    private Laboratory getLaboratory() {
        for (Facility facility : getTargetFacilities()) {
            if (facility instanceof Laboratory) {
                return (Laboratory) facility;
            }
        }

        return null;
    }

    @Override
    public boolean canStartIn(Environment environment) {
        if (environment == null) {
            return false;
        }

        Laboratory laboratory = getLaboratory();

        if (laboratory == null) {
            return false;
        }

        if (!laboratory.isOperational() || laboratory.isOnFire()) {
            return false;
        }

        for (String equipment : requiredEquipment) {
            if (equipment == null
                    || equipment.trim().isEmpty()
                    || !laboratory.hasEquipment(equipment)) {
                return false;
            }
        }

        return true;
    }

    public boolean execute(Colony colony) {
        if (colony == null) {
            return false;
        }

        if (getState() != MissionStatus.ASSIGNED) {
            return false;
        }

        if (colony.getActiveMission() != this) {
            return false;
        }

        if (!canStartIn(colony.getEnvironment())) {
            failMission(
                    "The laboratory is unavailable or required equipment "
                    + "is missing or damaged.");
            return false;
        }

        Scientist scientist = null;

        for (Agent agent : getAssignedAgents()) {
            if (agent instanceof Scientist && agent.canPerform(this)) {
                scientist = (Scientist) agent;
                break;
            }
        }

        if (scientist == null) {
            failMission(
                    "No assigned scientist has the required research skill.");
            return false;
        }

        startMission();

        System.out.println("========== RESEARCH MISSION ==========");
        System.out.println("Mission ID: " + getId());
        System.out.println("Mission Name: " + getName());
        System.out.println("Experiment: " + experimentName);
        System.out.println("Scientist: " + scientist.getName());
        System.out.println("Laboratory: " + getLaboratory().getName());
        System.out.println("Required Skill: " + getRequiredCapability());
        System.out.println("Required Equipment: " + requiredEquipment);
        System.out.println("Mission Duration: " + getDuration());
        System.out.println("Research experiment has started.");

        return true;
    }
}

