package au.edu.curtin.myapplication;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;

import au.edu.curtin.myapplication.PlayerSchema.PlayerTable;

public class PlayerCursor extends CursorWrapper {

    public PlayerCursor(Cursor cursor) {
        super(cursor);
    }

    public Player getPlayer()
    {
        int id = getInt(getColumnIndex(PlayerTable.Cols.PLAYERID));
        int row = getInt(getColumnIndex(PlayerTable.Cols.ROWLOCATION));
        int col = getInt(getColumnIndex(PlayerTable.Cols.COLLOCATION));
        int cash = getInt(getColumnIndex(PlayerTable.Cols.CASH));
        double health = getDouble(getColumnIndex(PlayerTable.Cols.PLAYERHEALTH));
        double equipMass = getDouble(getColumnIndex(PlayerTable.Cols.EQUIPMENTMASS));
        //TODO make sure you load player before items as they have a empty equipmentList
        return new Player(row, col, cash, health, equipMass, new ArrayList<Equipment>());
    }
}
