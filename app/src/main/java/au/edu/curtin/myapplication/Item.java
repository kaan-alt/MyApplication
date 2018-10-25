package au.edu.curtin.myapplication;

import java.io.Serializable;

public abstract class Item implements Serializable{
    private String description;
    private int value;
    private boolean usable;
    private int itemRowLocation;
    private int itemColLocation;
    private boolean playerOwned;

    public final int id;
    private static int nextId = 0;

    public static int getNextId() {
        return nextId;
    }

    public int getId() {
        return id;
    }

    public boolean isPlayerOwned() {
        return playerOwned;
    }

    public void setPlayerOwned(boolean playerOwned) {
        this.playerOwned = playerOwned;
    }

    public int getItemRowLocation() {
        return itemRowLocation;
    }

    public void setItemRowLocation(int itemRowLocation) {
        this.itemRowLocation = itemRowLocation;
    }

    public int getItemColLocation() {
        return itemColLocation;
    }

    public void setItemColLocation(int itemColLocation) {
        this.itemColLocation = itemColLocation;
    }

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

    public Item(int value, String description, int importedRow, int importedCol) {

        this.value = value;
        this.description = description;
        this.itemRowLocation = importedRow;
        this.itemColLocation = importedCol;

        this.id = nextId;
        nextId++;
    }

    public Item(String description, int value, boolean usable, int itemRowLocation, int itemColLocation, boolean playerOwned, int id) {
        this.description = description;
        this.value = value;
        this.usable = usable;
        this.itemRowLocation = itemRowLocation;
        this.itemColLocation = itemColLocation;
        this.playerOwned = playerOwned;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
