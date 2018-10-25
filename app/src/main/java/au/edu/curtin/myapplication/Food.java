package au.edu.curtin.myapplication;

public class Food extends Item {
    private double health;

    public Food(int value, String description, int itemRowLocation, int itemColLocation, double health) {
        super(value, description, itemRowLocation, itemColLocation);
        this.health = health;
    }

    public Food(String description, int value, boolean usable, int itemRowLocation, int itemColLocation, boolean playerOwned, int id, double health) {
        super(description, value, usable, itemRowLocation, itemColLocation, playerOwned, id);
        this.health = health;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Food{" +
                "health=" + health +
                '}';
    }
}
