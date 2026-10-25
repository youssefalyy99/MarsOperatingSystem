package Homework.Lab5HW;

public class Habitat extends Facility{
    Habitat(String id, String name){
        super(id, name);
    }

    public void applyOxygenLeak(double amount){
        R1.LeakOxygen(amount);
    }

    public void getOxygen(){
        R1.getOxygenLevel();
    }

}
