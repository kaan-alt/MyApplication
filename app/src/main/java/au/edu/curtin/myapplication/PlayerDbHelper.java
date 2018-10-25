package au.edu.curtin.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import au.edu.curtin.myapplication.PlayerSchema.PlayerTable;

public class PlayerDbHelper extends SQLiteOpenHelper{

    private static int VERSION = 1;
    private static final String DATABASE_NAME = "player.db";

    public PlayerDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + PlayerTable.TABLE_NAME + "(" +
                PlayerTable.Cols.PLAYERID +
                " INTEGER PRIMARY KEY" + ", " +
                PlayerTable.Cols.ROWLOCATION +
                " INTEGER" + ", " +
                PlayerTable.Cols.COLLOCATION +
                " INTEGER" + ", " +
                PlayerTable.Cols.CASH +
                " INTEGER" + ", " +
                PlayerTable.Cols.PLAYERHEALTH +
                " DOUBLE" + ", " +
                PlayerTable.Cols.EQUIPMENTMASS +
                " DOUBLE" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2)
    {
    }
}
