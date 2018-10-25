package au.edu.curtin.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import au.edu.curtin.myapplication.FoodItemSchema.FoodItemTable;
public class FoodItemList {
    private List<Food> foodItems = new ArrayList<>();
    private SQLiteDatabase db;


    public FoodItemList(Context context)
    {
        this.db = new FoodItemDbHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public void load()
    {
        FoodItemCursor cursor = new FoodItemCursor(db.query(FoodItemTable.TABLE_NAME, null, null, null, null, null, null));
        try
        {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                add(cursor.getFoodItem());
                cursor.moveToNext();
            }
        }
        finally
        {
            cursor.close();
        }
    }

    public int size()
    {
        return foodItems.size();
    }

    public Food get(int i)
    {
        return foodItems.get(i);
    }

    public int add(Food newFood)
    {
        ContentValues cv = new ContentValues();
        cv.put(FoodItemTable.Cols.ITEMID, newFood.getId());
        cv.put(FoodItemTable.Cols.ITEMDESCRIPTION, newFood.getDescription());
        cv.put(FoodItemTable.Cols.ITEMVALUE, newFood.getValue());
        cv.put(FoodItemTable.Cols.USABLE, newFood.isUsable());
        cv.put(FoodItemTable.Cols.ITEMROW, newFood.getItemRowLocation());
        cv.put(FoodItemTable.Cols.ITEMCOL, newFood.getItemColLocation());
        cv.put(FoodItemTable.Cols.PLAYEROWNED, newFood.isPlayerOwned());
        cv.put(FoodItemTable.Cols.FOODHEALTH, newFood.getHealth());
        db.insert(FoodItemTable.TABLE_NAME, null, cv);
        return foodItems.size() - 1; // Return insertion point
    }

    public void edit(Food newFood)
    {
        ContentValues cv = new ContentValues();
        cv.put(FoodItemTable.Cols.ITEMID, newFood.getId());
        cv.put(FoodItemTable.Cols.ITEMDESCRIPTION, newFood.getDescription());
        cv.put(FoodItemTable.Cols.ITEMVALUE, newFood.getValue());
        cv.put(FoodItemTable.Cols.USABLE, newFood.isUsable());
        cv.put(FoodItemTable.Cols.ITEMROW, newFood.getItemRowLocation());
        cv.put(FoodItemTable.Cols.ITEMCOL, newFood.getItemColLocation());
        cv.put(FoodItemTable.Cols.PLAYEROWNED, newFood.isPlayerOwned());
        cv.put(FoodItemTable.Cols.FOODHEALTH, newFood.getHealth());
        //ONLY ONE ENTRY IN DB FOR PLAYER THEREFORE DONT NEED A PRIMARY KEY
        String[] whereValue = { String.valueOf(newFood.getId()) };
        db.update(FoodItemTable.TABLE_NAME, cv, FoodItemTable.Cols.ITEMID + " = ?", whereValue);
    }

    public void remove(Food rmFood)
    {
        foodItems.remove(rmFood);
        String[] whereValue = { String.valueOf(rmFood.getId()) };
        db.delete(FoodItemTable.TABLE_NAME, FoodItemTable.Cols.ITEMID + " = ?" , whereValue);
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
