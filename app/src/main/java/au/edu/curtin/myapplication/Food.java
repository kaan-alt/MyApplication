package au.edu.curtin.myapplication;

public class Food extends Item {
    double health;

    public Food(int value, String description, double health) {
        super(value, description);
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
                ", description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
