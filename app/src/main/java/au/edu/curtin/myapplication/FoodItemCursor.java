package au.edu.curtin.myapplication;

import android.database.Cursor;
import android.database.CursorWrapper;

import au.edu.curtin.myapplication.FoodItemSchema.FoodItemTable;
public class FoodItemCursor extends CursorWrapper {
    public FoodItemCursor(Cursor cursor) {
        super(cursor);
    }

    public Food getFoodItem()
    {
        //TODO boolean returns a int = 1 if TRUE int = 0 if false
        int id = getInt(getColumnIndex(FoodItemTable.Cols.ITEMID));
        String itemDis = getString(getColumnIndex(FoodItemTable.Cols.ITEMDESCRIPTION));
        int value = getInt(getColumnIndex(FoodItemTable.Cols.ITEMVALUE));
        int usableString = getInt(getColumnIndex(FoodItemTable.Cols.USABLE));
        boolean usable = false;
        if(usableString == 1)
        {
            usable = true;
        }
        int row = getInt(getColumnIndex(FoodItemTable.Cols.ITEMROW));
        int col = getInt(getColumnIndex(FoodItemTable.Cols.ITEMCOL));
        int ownedInt = getInt(getColumnIndex(FoodItemTable.Cols.PLAYEROWNED));
        boolean owned = false;
        if(ownedInt == 1)
        {
            owned = true;
        }
        double foodHealth= getInt(getColumnIndex(FoodItemTable.Cols.FOODHEALTH));

        //TODO make sure you load player before items as they have a empty equipmentList
        return new Food(itemDis, value, usable, row, col, owned, id, foodHealth);
    }
}
