package au.edu.curtin.myapplication;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    Area[][] grid;
    int maxCol;
    int maxRow;

    public int getMaxCol() {
        return maxCol;
    }

    public void setMaxCol(int maxCol) {
        this.maxCol = maxCol;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    public GameMap(Area[][] grid, int maxCol, int maxRow) {
        this.grid = grid;
        this.maxCol = maxCol;
        this.maxRow = maxRow;
    }


    public GameMap() {
        this.grid = new Area[2][3];
        ArrayList<Item> equipmentList = new ArrayList<>();
        equipmentList.add(createSampleEquipment());
        equipmentList.add(createSampleFood());
        equipmentList.add(createSampleEquipment2());
        Area town1 = new Area(false, equipmentList);
        Area town2 = new Area(true, equipmentList);
        Area town3 = new Area(true, equipmentList);
        Area town4 = new Area(false, equipmentList);
        Area town5 = new Area(true, equipmentList);
        Area town6 = new Area(true, equipmentList);
        grid[0][0] = town1;
        grid[0][1] = town2;
        grid[0][2] = town3;
        grid[1][0] = town4;
        grid[1][1] = town5;
        grid[1][2] = town6;

        maxRow = 2;
        maxCol = 1;
    }

    public Area[][] getGrid() {

        return grid;
    }

    public void setGrid(Area[][] grid) {
        this.grid = grid;
    }

    @Override
    public String toString() {
        return "GameMap{" +
                "grid=" + Arrays.toString(grid) +
                '}';
    }

    public Food createSampleFood()
    {
        Food sampleFood = new Food(15,"Sample Food", 10.0);
        return sampleFood;
    }

    public Equipment createSampleEquipment()
    {
        Equipment sampleEquipment = new Equipment(20, "Sample Equipment", 20.0);
        return sampleEquipment;
    }

    public Equipment createSampleEquipment2()
    {
        Equipment sampleEquipment = new Equipment(20, "Sample Equipment2", 20.0);
        return sampleEquipment;
    }
}
