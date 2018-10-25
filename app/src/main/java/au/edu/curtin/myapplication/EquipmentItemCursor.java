package au.edu.curtin.myapplication;

import android.database.Cursor;
import android.database.CursorWrapper;

import au.edu.curtin.myapplication.EquipmentItemSchema.EquipmentItemTable;

public class EquipmentItemCursor extends CursorWrapper {
    public EquipmentItemCursor(Cursor cursor) {
        super(cursor);
    }

    public Equipment getEquipmentItem()
    {
        //TODO boolean returns a int = 1 if TRUE int = 0 if false
        int id = getInt(getColumnIndex(EquipmentItemTable.Cols.ITEMID));
        String itemDis = getString(getColumnIndex(EquipmentItemTable.Cols.ITEMDESCRIPTION));
        int value = getInt(getColumnIndex(EquipmentItemTable.Cols.ITEMVALUE));
        int usableString = getInt(getColumnIndex(EquipmentItemTable.Cols.USABLE));
        boolean usable = false;
        if(usableString == 1)
        {
            usable = true;
        }
        int row = getInt(getColumnIndex(EquipmentItemTable.Cols.ITEMROW));
        int col = getInt(getColumnIndex(EquipmentItemTable.Cols.ITEMCOL));
        int ownedInt = getInt(getColumnIndex(EquipmentItemTable.Cols.PLAYEROWNED));
        boolean owned = false;
        if(ownedInt == 1)
        {
            owned = true;
        }
        int mass = getInt(getColumnIndex(EquipmentItemTable.Cols.EQUIPMENTITEMMASS));

        //TODO make sure you load player before items as they have a empty equipmentList
        return new Equipment(itemDis, value, usable, row, col, owned, id, mass);
    }
}
