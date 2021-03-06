package au.edu.curtin.myapplication;

public class Equipment extends Item{
    private double mass;

    public Equipment(int value, String description, int itemRowLocation, int itemColLocation, double mass) {
        super(value, description, itemRowLocation, itemColLocation);
        this.mass = mass;
    }

    public Equipment(String description, int value, boolean usable, int itemRowLocation, int itemColLocation, boolean playerOwned, int id, double mass) {
        super(description, value, usable, itemRowLocation, itemColLocation, playerOwned, id);
        this.mass = mass;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "mass=" + mass +
                '}';
    }

}

