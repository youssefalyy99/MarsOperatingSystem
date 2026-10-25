package Homework.Lab5HW;

public class Laboratory extends Facility {
    private boolean onFire;
    Laboratory(String name, String id){
        super(id, name);
    }

    public void catchFire() {
        if (onFire) return;
        onFire = true;
        this.status = "UNAVAILABLE";
    }

    public void extinguishFire(){
        onFire = false;
        if (!this.status.equals("DESTROYED")) {
            this.status = "OPERATIONAL";
        }
    }

    public boolean isOnFire(){return onFire; }
}
