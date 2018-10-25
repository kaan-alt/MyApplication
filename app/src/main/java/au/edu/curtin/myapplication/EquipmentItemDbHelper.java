package au.edu.curtin.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import au.edu.curtin.myapplication.EquipmentItemSchema.EquipmentItemTable;

public class EquipmentItemDbHelper extends SQLiteOpenHelper {

    private static int VERSION = 1;
    private static final String DATABASE_NAME = "equipmentItems.db";

    public EquipmentItemDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + EquipmentItemTable.TABLE_NAME + "(" + " _id integer primary key autoincrement, " +
                EquipmentItemTable.Cols.ITEMID + ", " +
                EquipmentItemTable.Cols.ITEMDESCRIPTION + ", " +
                EquipmentItemTable.Cols.ITEMVALUE + ", " +
                EquipmentItemTable.Cols.USABLE + ", " +
                EquipmentItemTable.Cols.ITEMROW + ", " +
                EquipmentItemTable.Cols.ITEMCOL + ", " +
                EquipmentItemTable.Cols.PLAYEROWNED + ", " +
                EquipmentItemTable.Cols.EQUIPMENTITEMMASS + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int v1, int v2)
    {
    }

}
