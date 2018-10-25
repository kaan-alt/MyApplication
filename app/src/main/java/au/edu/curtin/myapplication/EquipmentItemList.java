package au.edu.curtin.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import au.edu.curtin.myapplication.EquipmentItemSchema.EquipmentItemTable;

public class EquipmentItemList {
    private List<Equipment> equipmentItems = new ArrayList<>();
    private SQLiteDatabase db;


    public EquipmentItemList(Context context)
    {
        this.db = new EquipmentItemDbHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public void load()
    {
        EquipmentItemCursor cursor = new EquipmentItemCursor(db.query(EquipmentItemTable.TABLE_NAME, null, null, null, null, null, null));
        try
        {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                add(cursor.getEquipmentItem());
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
        return equipmentItems.size();
    }

    public Equipment get(int i)
    {
        return equipmentItems.get(i);
    }

    public int add(Equipment newEquipment)
    {
        ContentValues cv = new ContentValues();
        cv.put(EquipmentItemTable.Cols.ITEMID, newEquipment.getId());
        cv.put(EquipmentItemTable.Cols.ITEMDESCRIPTION, newEquipment.getDescription());
        cv.put(EquipmentItemTable.Cols.ITEMVALUE, newEquipment.getValue());
        cv.put(EquipmentItemTable.Cols.USABLE, newEquipment.isUsable());
        cv.put(EquipmentItemTable.Cols.ITEMROW, newEquipment.getItemRowLocation());
        cv.put(EquipmentItemTable.Cols.ITEMCOL, newEquipment.getItemColLocation());
        cv.put(EquipmentItemTable.Cols.PLAYEROWNED, newEquipment.isPlayerOwned());
        cv.put(EquipmentItemTable.Cols.EQUIPMENTITEMMASS, newEquipment.getMass());
        db.insert(EquipmentItemTable.TABLE_NAME, null, cv);
        return equipmentItems.size() - 1; // Return insertion point
    }

    public void edit(Equipment newEquipment)
    {
        ContentValues cv = new ContentValues();
        cv.put(EquipmentItemTable.Cols.ITEMID, newEquipment.getId());
        cv.put(EquipmentItemTable.Cols.ITEMDESCRIPTION, newEquipment.getDescription());
        cv.put(EquipmentItemTable.Cols.ITEMVALUE, newEquipment.getValue());
        cv.put(EquipmentItemTable.Cols.USABLE, newEquipment.isUsable());
        cv.put(EquipmentItemTable.Cols.ITEMROW, newEquipment.getItemRowLocation());
        cv.put(EquipmentItemTable.Cols.ITEMCOL, newEquipment.getItemColLocation());
        cv.put(EquipmentItemTable.Cols.PLAYEROWNED, newEquipment.isPlayerOwned());
        cv.put(EquipmentItemTable.Cols.EQUIPMENTITEMMASS, newEquipment.getMass());
        //ONLY ONE ENTRY IN DB FOR PLAYER THEREFORE DONT NEED A PRIMARY KEY
        String[] whereValue = { String.valueOf(newEquipment.getId()) };
        db.update(EquipmentItemTable.TABLE_NAME, cv, EquipmentItemTable.Cols.ITEMID + " = ?", whereValue);
    }

    public void remove(Equipment rmEquipment)
    {
        equipmentItems.remove(rmEquipment);
        String[] whereValue = { String.valueOf(rmEquipment.getId()) };
        db.delete(EquipmentItemTable.TABLE_NAME, EquipmentItemTable.Cols.ITEMID + " = ?" , whereValue);
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
