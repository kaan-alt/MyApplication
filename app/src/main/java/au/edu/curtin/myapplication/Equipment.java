package au.edu.curtin.myapplication;

public class Equipment extends Item{
    private double mass;

    public Equipment(int value, String description, double mass) {
        super(value, description);
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

