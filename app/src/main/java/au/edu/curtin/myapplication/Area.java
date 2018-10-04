package au.edu.curtin.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Area {
    boolean town;
    ArrayList<Item> items;

    public Area(boolean town, ArrayList<Item> items) {
        this.town = town;
        this.items = items;
    }

    public boolean isTown() {
        return town;
    }

    public void setTown(boolean town) {
        this.town = town;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Area{" +
                "town=" + town +
                ", items=" + items +
                '}';
    }
}
