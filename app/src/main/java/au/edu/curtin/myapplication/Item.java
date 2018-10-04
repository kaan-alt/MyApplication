package au.edu.curtin.myapplication;

import java.io.Serializable;

public abstract class Item implements Serializable{
    String description;
    int value;

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
