/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

/**
 *
 * @author pc
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public abstract class Mission {

    private final int id;
    private final String name;
    private final int duration;
    private int progress;
    private MissionStatus state;
    private String failureReason;
    private MissionType type;
    
    private final ArrayList<Agent> assignedAgents;
    private final Resources resourceRequest;

    private final ArrayList<Facility> targetFacilities;
    private final String requiredCapability;
    private final int minBattery;

    public Mission(int id, String name, int duration, Resources resourceRequest,
            List<Facility> targetFacilities, String requiredCapability, int minBattery,MissionType type) {

        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0.");
        }
        if (minBattery < 0) {
            throw new IllegalArgumentException("Minimum battery cannot be negative.");
        }
        
       this.type = type;
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.resourceRequest = resourceRequest;
        this.requiredCapability = requiredCapability;
        this.minBattery = minBattery;

        if (targetFacilities == null) {
            this.targetFacilities = new ArrayList<>();
        } else {
            this.targetFacilities = new ArrayList<>(targetFacilities);
        }

        this.progress = 0;
        this.state = MissionStatus.PENDING;
        this.failureReason = null;
        this.assignedAgents = new ArrayList<>();
    }

    public MissionType getType() {
        return missionType;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getProgress() {
        return progress;
    }

    public MissionStatus getState() {
        return state;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public List<Agent> getAssignedAgents() {
        return Collections.unmodifiableList(assignedAgents);
    }

    public Resources getResourceRequest() {
        return resourceRequest;
    }

    public List<Facility> getTargetFacilities() {
        return Collections.unmodifiableList(targetFacilities);
    }

    public String getRequiredCapability() {
        return requiredCapability;
    }

    public int getMinBattery() {
        return minBattery;
    }

    public boolean isActive() {
        if (state == MissionStatus.IN_PROGRESS || state == MissionStatus.ASSIGNED) {
            return true;
        }
        return false;
    }

    public boolean isFinished() {
        if (state == MissionStatus.FAILED || state == MissionStatus.COMPLETED) {
            return true;
        }
        return false;
    }

    public void releaseAgents() {
        for (Agent agent : assignedAgents) {
            agent.setAvailability(true);
        }
    }

    public void complete() {
        if (state != MissionStatus.IN_PROGRESS) {
            return;
        }
        state = MissionStatus.COMPLETED;
        releaseAgents();
    }

    public boolean assignAgents(List<Agent> agents) {
    if (state != MissionStatus.PENDING) {
        return false;                      
    }

    if (agents == null || agents.isEmpty()) {
        failMission("No agents were provided for this mission.");
        return false;
    }

    
    for (Agent agent : agents) {
        if (agent == null) {
            failMission("A required agent is missing.");
            return false;
        }
        if (!agent.isReady()) {
            failMission("Agent " + agent.getName() + " is not available or not in usable condition.");
            return false;
        }
        if (!agent.canPerform(this)) {
            failMission("Agent " + agent.getName() + " does not possess the required capability.");
            return false;
        }
    }

    
    for (Agent agent : agents) {
        if (!assignedAgents.contains(agent)) {   
            assignedAgents.add(agent);
            agent.setAvailability(false);
        }
    }
    state = MissionStatus.ASSIGNED;
    return true;
}

    public void failMission(String reason) {
        if (state == MissionStatus.COMPLETED
                || state == MissionStatus.FAILED) {
            return;
        }

        boolean wasAccepted = (state == MissionStatus.ASSIGNED
                || state == MissionStatus.IN_PROGRESS);

        state = MissionStatus.FAILED;
        failureReason = reason;

        if (wasAccepted) {
            releaseAgents();
        }
    }

    public void startMission() {
        if (state == MissionStatus.ASSIGNED) {
            state = MissionStatus.IN_PROGRESS;
        }
    }

    public void advanceTime() {
        if (state == MissionStatus.IN_PROGRESS) {
            progress++;
            if (progress >= duration) {
               complete();
            }
        }
    }
    
    public abstract boolean canStartIn(Environment environment);

}
