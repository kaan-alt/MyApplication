package au.edu.curtin.myapplication;

import android.media.audiofx.DynamicsProcessing;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.PI;

public class GameData {
    Area[][] grid;
    private int maxCol;
    private int maxRow;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 10;
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
        this.grid = new Area[HEIGHT][WIDTH];
        Random random = new Random();
        for(int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if(i == 0 && j == 0)
                {
                    int randomGenNum = random.nextInt(11);
                    if (randomGenNum < 5) {
                        Area area = new Area(false, createAllUsableForArea(i, j), i, j);
                        grid[i][j] = area;
                    } else {
                        Area area = new Area(true, createAllUsableForArea(i, j), i, j);
                        grid[i][j] = area;
                    }
                }
                else
                {
                    int randomGenNum = random.nextInt(11);
                    if (randomGenNum < 5) {
                        Area area = new Area(false, createEquipmentListForArea(i, j), i ,j);
                        grid[i][j] = area;
                    } else {
                        Area area = new Area(true, createEquipmentListForArea(i, j),i , j);
                        grid[i][j] = area;
                    }
                }
            }
        }

        maxRow = 9;
        maxCol = 29;
        player = new Player(0,0,200, 100.0, 0.0, new ArrayList<Equipment>());
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

    public Food createSampleFood(int itemRowLocation, int itemColLocation)
    {
        Food sampleFood = new Food(15,"Sample Food", itemRowLocation, itemColLocation,10.0);
        return sampleFood;
    }

    public Food createSamplePoisonFood(int itemRowLocation, int itemColLocation)
    {
        Food sampleFood = new Food(15,"Sample Poison Food", itemRowLocation, itemColLocation,-100.0);
        return sampleFood;
    }

    public Equipment createSampleEquipment(int itemRowLocation, int itemColLocation)
    {
        Equipment sampleEquipment = new Equipment(20, "Sample Equipment", itemRowLocation, itemColLocation,20.0);
        return sampleEquipment;
    }

    public Equipment createJadeMonkey(int itemRowLocation, int itemColLocation)
    {
        Equipment sampleEquipment = new Equipment(20, "jade monkey", itemRowLocation, itemColLocation,20.0);
        return sampleEquipment;
    }
    public Equipment createRoadMap(int itemRowLocation, int itemColLocation)
    {
        Equipment sampleEquipment = new Equipment(20, "the roadmap", itemRowLocation, itemColLocation,20.0);
        return sampleEquipment;
    }
    public Equipment createIceScraper(int itemRowLocation, int itemColLocation)
    {
        Equipment sampleEquipment = new Equipment(20, "the ice scraper", itemRowLocation, itemColLocation,20.0);
        return sampleEquipment;
    }

    public Equipment createSampleEquipment2(int itemRowLocation, int itemColLocation)
    {
        Equipment sampleEquipment = new Equipment(20, "Sample Equipment2", itemRowLocation, itemColLocation,20.0);
        return sampleEquipment;
    }

    public Equipment createSampleUsable(int itemRowLocation, int itemColLocation)
    {
        Equipment sampleEquipment = new Equipment(20, "Usable example", itemRowLocation, itemColLocation,20.0);
        sampleEquipment.setUsable(true);
        return sampleEquipment;
    }


    public Equipment createSmellO(int itemRowLocation, int itemColLocation)
    {
        Equipment sampleEquipment = new Equipment(20, "Smell-O", itemRowLocation, itemColLocation,5.0);
        sampleEquipment.setUsable(true);
        return sampleEquipment;
    }

    public Equipment createiDrive(int itemRowLocation, int itemColLocation)
    {
        Equipment sampleEquipment = new Equipment(20, "iDrive", itemRowLocation, itemColLocation, PI);
        sampleEquipment.setUsable(true);
        return sampleEquipment;
    }

    public Equipment createBen(int itemRowLocation, int itemColLocation)
    {
        Equipment sampleEquipment = new Equipment(20, "Ben", itemRowLocation, itemColLocation, 0.0);
        sampleEquipment.setUsable(true);
        return sampleEquipment;
    }

    public ArrayList<Item> createAllUsableForArea(int itemRowLocation, int itemColLocation)
    {
        ArrayList<Item> equipmentList = new ArrayList<>();
        equipmentList.add(createSmellO(itemRowLocation, itemColLocation));
        equipmentList.add(createiDrive(itemRowLocation, itemColLocation));
        equipmentList.add(createBen(itemRowLocation, itemColLocation));
        equipmentList.add(createJadeMonkey(itemRowLocation, itemColLocation));
        equipmentList.add(createRoadMap(itemRowLocation, itemColLocation));
        equipmentList.add(createIceScraper(itemRowLocation, itemColLocation));
        equipmentList.add(createSamplePoisonFood(itemRowLocation, itemColLocation));
        return equipmentList;
    }

    public ArrayList<Item> createEquipmentListForArea(int itemRowLocation, int itemColLocation)
    {
        ArrayList<Item> equipmentList = new ArrayList<>();
        equipmentList.add(createSampleEquipment(itemRowLocation, itemColLocation));
        equipmentList.add(createSampleFood(itemRowLocation, itemColLocation));
        equipmentList.add(createSampleEquipment2(itemRowLocation, itemColLocation));
        equipmentList.add(createSampleUsable(itemRowLocation, itemColLocation));
        equipmentList.add(createSamplePoisonFood(itemRowLocation, itemColLocation));
        return equipmentList;
    }

    public void reset()
    {
        instance = new GameData();
    }

    public Area get(int i, int j)
    {
        return grid[i][j];
    }

    //TODO once area randomisation is implimented update this aswell ;)
    public void randomiseAreaAgain()
    {
        this.grid = new Area[HEIGHT][WIDTH];
        Random random = new Random();
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (i == 0 && j == 0) {
                    int randomGenNum = random.nextInt(11);
                    if (randomGenNum < 5) {
                        Area area = new Area(false, createAllUsableForArea(i, j), i, j);
                        grid[i][j] = area;
                    } else {
                        Area area = new Area(true, createAllUsableForArea(i, j), i, j);
                        grid[i][j] = area;
                    }
                } else {
                    int randomGenNum = random.nextInt(11);
                    if (randomGenNum < 5) {
                        Area area = new Area(false, createEquipmentListForArea(i, j), i, j);
                        grid[i][j] = area;
                    } else {
                        Area area = new Area(true, createEquipmentListForArea(i, j), i, j);
                        grid[i][j] = area;
                    }
                }
            }
        }

        maxRow = 9;
        maxCol = 29;
    }

}
