package lab5;

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

    private final ArrayList<Agent> assignedAgents;
    private final Resources resourceRequest;

    private final ArrayList<Facility> targetFacilities;
    private final String requiredCapability;
    private final int minBattery;

    public Mission(int id, String name, int duration, Resources resourceRequest,
            List<Facility> targetFacilities, String requiredCapability, int minBattery) {

        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0.");
        }
        if (minBattery < 0) {
            throw new IllegalArgumentException("Minimum battery cannot be negative.");
        }

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

        // every new mission starts the same way
        this.progress = 0;
        this.state = MissionStatus.PENDING;
        this.failureReason = null;
        this.assignedAgents = new ArrayList<>();
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

    public boolean assignAgent(Agent agent, List<Mission> allMissions) {
        if (agent == null || allMissions == null) {
            return false;
        }

        if (state != MissionStatus.PENDING) {
            return false;
        }

        if (!agent.isReady() || !agent.canPerform(this)) {
            return false;
        }

        for (Mission m : allMissions) {
            if (m == null || m == this) {
                continue;
            }

            if (m.getState() == MissionStatus.ASSIGNED
                    || m.getState() == MissionStatus.IN_PROGRESS) {
                if (m.getAssignedAgents().contains(agent)) {
                    return false;
                }
            }
        }

        if (assignedAgents.contains(agent)) {
            return false;
        }

        assignedAgents.add(agent);
        return true;
    }

    public void acceptMission() {
        if (state == MissionStatus.PENDING && !assignedAgents.isEmpty()) {
            state = MissionStatus.ASSIGNED;

            for (Agent agent : assignedAgents) {
                agent.setAvailability(false);
            }
        }
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
            for (Agent agent : assignedAgents) {
                agent.setAvailability(true);
            }
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
                state = MissionStatus.COMPLETED;
                for (Agent agent : assignedAgents) {
                    agent.setAvailability(true);
                }
            }
        }

    }
}
