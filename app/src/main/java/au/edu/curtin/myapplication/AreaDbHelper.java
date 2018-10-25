package au.edu.curtin.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import au.edu.curtin.myapplication.AreaSchema.AreaTable;

public class AreaDbHelper extends SQLiteOpenHelper {
    private static int VERSION = 1;
    private static final String DATABASE_NAME = "areas.db";

    public AreaDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + AreaTable.TABLE_NAME + "(" + " _id integer primary key autoincrement, " +
                AreaTable.Cols.AREAID + ", " +
                AreaTable.Cols.TOWN + ", " +
                AreaTable.Cols.AREADESCRIPTION + ", " +
                AreaTable.Cols.STARRED + ", " +
                AreaTable.Cols.EXPLORED + ", " +
                AreaTable.Cols.CURRENTOCCUPIED + ", " +
                AreaTable.Cols.AREAROW + ", " +
                AreaTable.Cols.AREACOL +  ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2)
    {
    }
}
