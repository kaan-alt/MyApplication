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
        sqLiteDatabase.execSQL("create table " + PlayerTable.TABLE_NAME + "(" + " _id integer primary key autoincrement, " +
                PlayerTable.Cols.PLAYERID + ", " +
                PlayerTable.Cols.ROWLOCATION + ", " +
                PlayerTable.Cols.COLLOCATION + ", " +
                PlayerTable.Cols.CASH + ", " +
                PlayerTable.Cols.PLAYERHEALTH + ", " +
                PlayerTable.Cols.EQUIPMENTMASS + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2)
    {
    }
}
