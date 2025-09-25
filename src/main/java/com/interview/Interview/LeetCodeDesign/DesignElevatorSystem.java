package com.interview.Interview.LeetCodeDesign;

import java.util.List;

public class DesignElevatorSystem {

    public static void main(String[] args) {

    }
}

class ElevatorHandler {

    private List<Elevator> elevators;
    private PhysicalElevator physicalElevator;

    public ElevatorHandler(List<Elevator> elevators, PhysicalElevator physicalElevator) {
        this.elevators = elevators;
        this.physicalElevator = physicalElevator;
    }

    public void requestFloor(int floor) {
        Elevator chosen = selectElevator(floor);
        if(chosen == null) {
            System.out.println("no available elevator");
            return;
        }

        //decide direction
        if(floor < chosen.getCurrentFloor())  {
            chosen.setTargetFloor(floor);
            chosen.setState(State.MOVING_UP);
            physicalElevator.startMovingDown(chosen);
        } else if(floor > chosen.getCurrentFloor()) {
            chosen.setTargetFloor(floor);
            chosen.setState(State.MOVING_DOWN);
            physicalElevator.startMovingUp(chosen);
        } else {
            System.out.println("Elevator "+chosen.getId()+" already at floor "+floor);
        }
    }

    private Elevator selectElevator(int floor) {

        Elevator best = null;
        int best_distance = Integer.MAX_VALUE;
        for (Elevator e : elevators) {
            if(e.getState().equals(State.IDLE)) {
                int distance = floor - e.getCurrentFloor();
                best_distance = Math.min(distance, best_distance);
                best = e;
            }
        }
        return best;
    }
}

//this is just low level hardware with command, you can tell it when to move but not how
interface PhysicalElevator {

    void startMovingUp(Elevator e);
    void startMovingDown(Elevator e);

}

class Elevator {
    int id;
    int currentFloor;
    int targetFloor;
    State state;

    Elevator() {
        this.currentFloor = 0;
        this.state = State.IDLE;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public State getState() {
        return state;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
    }

    public void setState(State state) {
        this.state = state;
    }
}

enum State {
    MOVING_UP,
    MOVING_DOWN,
    IDLE
}
