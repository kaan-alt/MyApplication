package au.edu.curtin.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import au.edu.curtin.myapplication.FoodItemSchema.FoodItemTable;

public class FoodItemDbHelper extends SQLiteOpenHelper {
    private static int VERSION = 1;
    private static final String DATABASE_NAME = "foodItems.db";

    public FoodItemDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + FoodItemTable.TABLE_NAME + "(" + " _id integer primary key autoincrement, " +
                FoodItemTable.Cols.ITEMID + ", " +
                FoodItemTable.Cols.ITEMDESCRIPTION + ", " +
                FoodItemTable.Cols.ITEMVALUE + ", " +
                FoodItemTable.Cols.USABLE + ", " +
                FoodItemTable.Cols.ITEMROW + ", " +
                FoodItemTable.Cols.ITEMCOL + ", " +
                FoodItemTable.Cols.PLAYEROWNED + ", " +
                FoodItemTable.Cols.FOODHEALTH + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2)
    {
    }

}
