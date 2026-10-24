package Homework.Lab5HW;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Resources
 */
public class Resources {
    private double oxygen = 0 ;
    private double water = 0;
    private double food = 0;
    private double fuel = 0;
    private double power = 0;
    private double medicalSupplies = 0;
    private double spareParts = 0;

    private Map<String, Double> RequestRec = new HashMap<>();

    public void DisplayResources(){
        System.out.println("==========!   RESOURCES REPORT   !==========");
        System.out.println(">>> Food             : "+this.food);
        System.out.println(">>> Water            : "+this.water);
        System.out.println(">>> Fuel             : "+this.fuel);
        System.out.println(">>> Power            : "+this.power);
        System.out.println(">>> Oxygen           : "+this.oxygen);
        System.out.println(">>> Medical Supplies : "+this.medicalSupplies);
        System.out.println(">>> Spare Parts      : "+this.spareParts);
    }

    public void AddResources(String s, double amount){
        if(amount < 0){throw new IllegalArgumentException("System Message : Invalid Amount Entered . . .");}
        if(s.equals("OXYGEN")){this.oxygen = this.oxygen + amount;}
        else if(s.equals("FOOD")){this.food = this.food + amount; }
        else if(s.equals("WATER")){this.water = this.water + amount; }
        else if(s.equals("FUEL")){this.fuel = this.fuel + amount; }
        else if(s.equals("POWER")){this.power = this.power + amount;}
        else if(s.equals("MEDICAL_SUPPLIES")){this.medicalSupplies = this.medicalSupplies + amount;}
        else if(s.equals("SPARE_PARTS")){this.spareParts = this.spareParts + amount;}
        else {
            System.out.println("System Message : Make Sure You Used One Of The Following Resources Types . . .");
            System.out.println("System Message : [FOOD] [WATER] [FUEL] [OXYGEN] [POWER] [MEDICAL_SUPPLIES] [SPARE_PARTS] ");
            return;
        }
        System.out.println("System Message : "+ s + " Amount Was Added Successfully :) !");
    }

    public void RemoveResources(String s, double amount){
        if(amount < 0){throw new IllegalArgumentException("System Message : Invalid Amount Entered . . .");}
        if(s.equals("OXYGEN") && this.oxygen >= amount){this.oxygen = this.oxygen - amount;}
        else if(s.equals("FOOD") && this.food >= amount){this.food = this.food - amount; }
        else if(s.equals("WATER") && this.water >= amount){this.water = this.water - amount; }
        else if(s.equals("FUEL") && this.fuel >= amount){this.fuel = this.fuel - amount; }
        else if(s.equals("POWER") && this.power >= amount){this.power = this.power - amount;}
        else if(s.equals("MEDICAL_SUPPLIES") && this.medicalSupplies >= amount){this.medicalSupplies = this.medicalSupplies - amount;}
        else if(s.equals("SPARE_PARTS") && this.spareParts >= amount){this.spareParts = this.spareParts - amount;}
        else {
            System.out.println("System Message : Invalid resource type or insufficient resources . . .");
            System.out.println("System Message : Resources Available : [FOOD] [WATER] [FUEL] [OXYGEN] [POWER] [MEDICAL_SUPPLIES] [SPARE_PARTS] ");
            return;
        }
        System.out.println("System Message : "+ s +" Amount Was Taken Successfully :) !");
    }

    public boolean HasAtLeast(String s , double amount){
        if(amount < 0){throw new IllegalArgumentException("System Message : Invalid Amount Entered . . .");}
        if(s.equals("OXYGEN") && this.oxygen >= amount){System.out.println("System Message : " + s + " Is Available :) !");}
        else if(s.equals("FOOD") && this.food >= amount){System.out.println("System Message : " + s + " Is Available :) !");}
        else if(s.equals("WATER") && this.water >= amount){System.out.println("System Message : " + s + " Is Available :) !"); }
        else if(s.equals("FUEL") && this.fuel >= amount){System.out.println("System Message : " + s + " Is Available :) !");}
        else if(s.equals("POWER") && this.power >= amount){System.out.println("System Message : " + s + " Is Available :) !");}
        else if(s.equals("MEDICAL_SUPPLIES") && this.medicalSupplies >= amount){System.out.println("System Message : " + s + " Is Available :) !");}
        else if(s.equals("SPARE_PARTS") && this.spareParts >= amount){System.out.println("System Message : " + s + " Is Available :) !");}
        else {
            System.out.println("System Message : Invalid resource type or insufficient resources . . .");
            System.out.println("System Message : Resources Available : [FOOD] [WATER] [FUEL] [OXYGEN] [POWER] [MEDICAL_SUPPLIES] [SPARE_PARTS] ");
            return false;
        }
        return true;
    }

    public void AddRequirement(String s, double amount){
        if(!s.equals("OXYGEN") && !s.equals("FOOD") && !s.equals("WATER") && !s.equals("FUEL") && !s.equals("POWER") && !s.equals("MEDICAL_SUPPLIES") && !s.equals("SPARE_PARTS")){
            System.out.println("System Message : Invalid resource type . . .");
            System.out.println("System Message : Resources Available : [FOOD] [WATER] [FUEL] [OXYGEN] [POWER] [MEDICAL_SUPPLIES] [SPARE_PARTS] ");
        }else{
            RequestRec.put(s, amount);
        }
        return;
    }

    public void ViewRequirments(){
        for (Map.Entry<String, Double> RequestRec : RequestRec.entrySet()) {
            System.out.println(RequestRec.getKey() + ": " + RequestRec.getValue());
        }
    }

    public Boolean GetRequirments() {
    System.out.println("==========! RESOURCES AFTER TRANSACTION !==========");
    System.out.println("DISCLAMER : THESE ARE NOT FINAL VALUES YET . . .");
    Scanner input = new Scanner(System.in);
    int CanProceed = 1;
    String get = "N";

    for (Map.Entry<String, Double> request : RequestRec.entrySet()) {
        String resource = request.getKey();
        double amount = request.getValue();
        double remaining = 0;
        if (resource.equals("OXYGEN")) remaining = this.oxygen - amount;
        else if (resource.equals("FOOD")) remaining = this.food - amount;
        else if (resource.equals("WATER")) remaining = this.water - amount;
        else if (resource.equals("FUEL")) remaining = this.fuel - amount;
        else if (resource.equals("POWER")) remaining = this.power - amount;
        else if (resource.equals("MEDICAL_SUPPLIES")) remaining = this.medicalSupplies - amount;
        else if (resource.equals("SPARE_PARTS")) remaining = this.spareParts - amount;
        System.out.println(">>> " + resource + ": " + remaining);
        if(remaining < 0){CanProceed = 0;}
    }

    if(CanProceed == 0){
        System.out.println("System Message : Cannot Proceed With Current Requirments . . .");
        System.out.println("System Message : Please Update The Requirments Needed . . .");
        return false;
    }else{
        System.out.println("System Message: ARE YOU SURE YOU WANT TO PROCEED? (Y/N)");
        get = input.nextLine();
        if(get.equals("N")){
            System.out.println("System Message: Requirments Is Put On Hold . . .");
            return false;
        }else if(get.equals("Y")){
                    for (Map.Entry<String, Double> request : RequestRec.entrySet()) {
                    String resource = request.getKey();
                    double amount = request.getValue();
                    if (resource.equals("OXYGEN")){this.oxygen = this.oxygen - amount;}
                    else if (resource.equals("FOOD")){this.food = this.food - amount;}
                    else if (resource.equals("WATER")){this.water = this.water - amount; }
                    else if (resource.equals("FUEL")){ this.fuel = this.fuel - amount;}
                    else if (resource.equals("POWER")){this.power = this.power - amount;}
                    else if (resource.equals("MEDICAL_SUPPLIES")){this.medicalSupplies = this.medicalSupplies - amount;}
                    else if (resource.equals("SPARE_PARTS")){ this.spareParts = this.spareParts - amount; }
                }
                System.out.println("System Message: Requirments Are Taken Successfully :) !");
                RequestRec.clear();
                return true;
        }else{
            System.out.println("System Message: Invalid Input - Please Enter [Y] Or [N] . . .");
            return false;
        }
    }
}

}