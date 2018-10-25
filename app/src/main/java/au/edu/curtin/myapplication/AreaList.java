package au.edu.curtin.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import au.edu.curtin.myapplication.AreaSchema.AreaTable;

public class AreaList {
    private List<Area> areas = new ArrayList<>();
    private SQLiteDatabase db;


    public AreaList(Context context)
    {
        this.db = new PlayerDbHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public void load()
    {
        AreaCursor cursor = new AreaCursor(db.query(AreaTable.TABLE_NAME, null, null, null, null, null, null));
        try
        {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                add(cursor.getArea());
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
        return areas.size();
    }

    public Area get(int i)
    {
        return areas.get(i);
    }

    public int add(Area newArea)
    {
        ContentValues cv = new ContentValues();
        cv.put(AreaTable.Cols.AREAID, newArea.getId());
        cv.put(AreaTable.Cols.AREADESCRIPTION, newArea.getAreaDescription());
        cv.put(AreaTable.Cols.STARRED, newArea.isStarred());
        cv.put(AreaTable.Cols.EXPLORED, newArea.isExplored());
        cv.put(AreaTable.Cols.CURRENTOCCUPIED, newArea.isCurrentOccupied());
        cv.put(AreaTable.Cols.AREAROW, newArea.getAreaRow());
        cv.put(AreaTable.Cols.AREACOL, newArea.getAreaCol());
        db.insert(AreaTable.TABLE_NAME, null, cv);
        return areas.size() - 1; // Return insertion point
    }

    public void edit(Area newArea)
    {
        ContentValues cv = new ContentValues();
        cv.put(AreaTable.Cols.AREAID, newArea.getId());
        cv.put(AreaTable.Cols.AREADESCRIPTION, newArea.getAreaDescription());
        cv.put(AreaTable.Cols.STARRED, newArea.isStarred());
        cv.put(AreaTable.Cols.EXPLORED, newArea.isExplored());
        cv.put(AreaTable.Cols.CURRENTOCCUPIED, newArea.isCurrentOccupied());
        cv.put(AreaTable.Cols.AREAROW, newArea.getAreaRow());
        cv.put(AreaTable.Cols.AREACOL, newArea.getAreaCol());
        //ONLY ONE ENTRY IN DB FOR PLAYER THEREFORE DONT NEED A PRIMARY KEY
        String[] whereValue = { String.valueOf(newArea.getId()) };
        db.update(AreaTable.TABLE_NAME, cv, AreaTable.Cols.AREAID + " = ?", whereValue);
    }

    public void remove(Area rmArea)
    {
        areas.remove(rmArea);
        String[] whereValue = { String.valueOf(rmArea.getId()) };
        db.delete(AreaTable.TABLE_NAME, AreaTable.Cols.AREAID + " = ?" , whereValue);
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
