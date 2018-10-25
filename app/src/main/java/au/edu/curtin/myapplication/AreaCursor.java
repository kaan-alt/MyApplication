package au.edu.curtin.myapplication;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.ArrayList;

import au.edu.curtin.myapplication.AreaSchema.AreaTable;

public class AreaCursor extends CursorWrapper {
    public AreaCursor(Cursor cursor) {
        super(cursor);
    }

    public Area getArea()
    {
        int id = getInt(getColumnIndex(AreaTable.Cols.AREAID));
        int townInt = getInt(getColumnIndex(AreaTable.Cols.TOWN));
        boolean town= false;
        if(townInt == 1)
        {
            town = true;
        }
        String areaDis = getString(getColumnIndex(AreaTable.Cols.AREADESCRIPTION));
        int starredInt = getInt(getColumnIndex(AreaTable.Cols.STARRED));
        boolean starred = false;
        if(starredInt == 1)
        {
            starred = true;
        }
        int exploredString = getInt(getColumnIndex(AreaTable.Cols.EXPLORED));
        boolean explored = false;
        if(exploredString == 1)
        {
            explored = true;
        }
        int currentString = getInt(getColumnIndex(AreaTable.Cols.CURRENTOCCUPIED));
        boolean current = false;
        if(currentString == 1)
        {
            current = true;
        }
        int row = getInt(getColumnIndex(AreaTable.Cols.AREAROW));
        int col = getInt(getColumnIndex(AreaTable.Cols.AREACOL));
        //TODO make sure you load player before items as they have a empty equipmentList
        return new Area(town, new ArrayList<Item>(), areaDis, starred, explored, current, row, col, id);
    }
}
