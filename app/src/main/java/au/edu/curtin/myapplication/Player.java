package au.edu.curtin.myapplication;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int rowLocation;
    private int colLocation;
    private int cash;
    private double playerHealth;
    private double equipmentMass;
    private ArrayList<Equipment> equipment;

    public Player(int rowLocation, int colLocation, int cash, double playerHealth, double equipmentMass, ArrayList<Equipment> equipment) {
        this.rowLocation = rowLocation;
        this.colLocation = colLocation;
        this.cash = cash;
        this.playerHealth = playerHealth;
        this.equipmentMass = equipmentMass;
        this.equipment = equipment;
    }


    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList<Equipment> equipment) {
        this.equipment = equipment;
    }

    public int getRowLocation() {

        return rowLocation;
    }

    public void setRowLocation(int rowLocation) {
        this.rowLocation = rowLocation;
    }

    public int getColLocation() {
        return colLocation;
    }

    public void setColLocation(int colLocation) {
        this.colLocation = colLocation;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public double getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(double importedPlayerHealth)
    {
        if(importedPlayerHealth > 100)
        {
            this.playerHealth = 100.0;
        }
        else {
            this.playerHealth = importedPlayerHealth;
        }
    }

    public double getEquipmentMass() {
        return equipmentMass;
    }

    public void setEquipmentMass(double equipmentMass) {
        this.equipmentMass = equipmentMass;
    }

    public void addEquipment(Equipment itemToBeAdded) {
        this.equipment.add(itemToBeAdded);
        this.equipmentMass += itemToBeAdded.getMass();
    }

    public void removeEquipment(int indexOfTheRemovedItem) {
        this.equipmentMass -= equipment.get(indexOfTheRemovedItem).getMass();
        this.equipment.remove(indexOfTheRemovedItem);
    }

    //ADD ALL THE ITEMS IN AREA TO PLAYER
    public void addEquipmentListToExist(ArrayList<Item> addedList)
    {
        for (Item selectedItem : addedList) {
            if (selectedItem instanceof Food) {
                setPlayerHealth(getPlayerHealth() + ((Food) selectedItem).getHealth());
            } else {
                addEquipment((Equipment) selectedItem);
            }
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "rowLocation=" + rowLocation +
                ", colLocation=" + colLocation +
                ", cash=" + cash +
                ", playerHealth=" + playerHealth +
                ", equipmentMass=" + equipmentMass +
                ", equipment=" + equipment +
                '}';
    }
}
