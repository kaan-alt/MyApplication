package au.edu.curtin.myapplication;

import java.io.Serializable;

public abstract class Item implements Serializable{
    private String description;
    private int value;
    private boolean usable;
    //TODO add rowLocation and colLocation to every Item for smellO activty, need to change GameData map creation

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Item(int value, String description) {

        this.value = value;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
