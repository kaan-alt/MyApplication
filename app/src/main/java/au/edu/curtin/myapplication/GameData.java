package au.edu.curtin.myapplication;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class GameData {
    Area[][] grid;
    private int maxCol;
    private int maxRow;
    private Player player;
    private static GameData instance;

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

    public GameData(Area[][] grid, int maxCol, int maxRow) {
        this.grid = grid;
        this.maxCol = maxCol;
        this.maxRow = maxRow;
    }

    public Player getPlayer() {
        return player;
    }

    public static GameData getInstance()
    {
        if(instance == null)
        {
             instance = new GameData();
        }
        return instance;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static void setInstance(GameData instance) {
        GameData.instance = instance;
    }

    public GameData() {
        this.grid = new Area[2][3];
        Area town1 = new Area(false, createEquipmentListForArea());
        Area town2 = new Area(true, createEquipmentListForArea());
        Area town3 = new Area(true, createEquipmentListForArea());
        Area town4 = new Area(false, createEquipmentListForArea());
        Area town5 = new Area(true, createEquipmentListForArea());
        Area town6 = new Area(true, createEquipmentListForArea());
        grid[0][0] = town1;
        grid[0][1] = town2;
        grid[0][2] = town3;
        grid[1][0] = town4;
        grid[1][1] = town5;
        grid[1][2] = town6;

        maxRow = 1;
        maxCol = 2;
        player = new Player(0,0,200, 100, 15, new ArrayList<Equipment>());
    }

    public Area[][] getGrid() {

        return grid;
    }

    public void setGrid(Area[][] grid) {
        this.grid = grid;
    }

    @Override
    public String toString() {
        return "GameData{" +
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

    public Player createNewPlayer()
    {
        ArrayList<Equipment> emptyList = new ArrayList<>();
        Player theNewPlayer = new Player(0,0,200,100.0,15.0, emptyList );
        return theNewPlayer;
    }

    public ArrayList<Item> createEquipmentListForArea()
    {
        ArrayList<Item> equipmentList = new ArrayList<>();
        equipmentList.add(createSampleEquipment());
        equipmentList.add(createSampleFood());
        equipmentList.add(createSampleEquipment2());
        return equipmentList;
    }
}
