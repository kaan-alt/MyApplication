package au.edu.curtin.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Area {
    private boolean town;
    private ArrayList<Item> items;
    private String areaDescription;
    private boolean starred;
    private boolean explored;

    public void setAreaDescription(String areaDescription) {
        this.areaDescription = areaDescription;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public String getAreaDescription() {
        return areaDescription;
    }

    public boolean isStarred() {
        return starred;
    }

    public boolean isExplored() {
        return explored;
    }

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
