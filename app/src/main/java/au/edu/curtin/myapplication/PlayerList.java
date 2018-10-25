package au.edu.curtin.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import au.edu.curtin.myapplication.PlayerSchema.PlayerTable;

public class PlayerList {
    private List<Player> players = new ArrayList<>();
    private SQLiteDatabase db;


    public PlayerList(Context context)
    {
        this.db = new PlayerDbHelper(context.getApplicationContext()).getWritableDatabase();
    }

    public void load()
    {
        PlayerCursor cursor = new PlayerCursor(db.query(PlayerTable.TABLE_NAME, null, null, null, null, null, null));
        try
        {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                add(cursor.getPlayer());
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
        return players.size();
    }

    public Player get(int i)
    {
        return players.get(i);
    }

    public int add(Player newPlayer)
    {
        players.add(newPlayer);
        ContentValues cv = new ContentValues();
        cv.put(PlayerTable.Cols.PLAYERID, newPlayer.getPlayerId());
        cv.put(PlayerTable.Cols.ROWLOCATION, newPlayer.getRowLocation());
        cv.put(PlayerTable.Cols.COLLOCATION, newPlayer.getColLocation());
        cv.put(PlayerTable.Cols.CASH, newPlayer.getCash());
        cv.put(PlayerTable.Cols.PLAYERHEALTH, newPlayer.getPlayerHealth());
        cv.put(PlayerTable.Cols.EQUIPMENTMASS, newPlayer.getEquipmentMass());
        db.insert(PlayerTable.TABLE_NAME, null, cv);
        return players.size() - 1; // Return insertion point
    }

    public void edit(Player newPlayer)
    {
        ContentValues cv = new ContentValues();
        cv.put(PlayerTable.Cols.PLAYERID, newPlayer.getPlayerId());
        cv.put(PlayerTable.Cols.ROWLOCATION, newPlayer.getRowLocation());
        cv.put(PlayerTable.Cols.COLLOCATION, newPlayer.getColLocation());
        cv.put(PlayerTable.Cols.CASH, newPlayer.getCash());
        cv.put(PlayerTable.Cols.PLAYERHEALTH, newPlayer.getPlayerHealth());
        cv.put(PlayerTable.Cols.EQUIPMENTMASS, newPlayer.getEquipmentMass());
        //ONLY ONE ENTRY IN DB FOR PLAYER THEREFORE DONT NEED A PRIMARY KEY
        String[] whereValue = { String.valueOf(newPlayer.getPlayerId()) };
        db.update(PlayerTable.TABLE_NAME, cv, PlayerTable.Cols.PLAYERID + " = ?", whereValue);
    }

    public void remove(Player rmPlayer)
    {
        players.remove(rmPlayer);
        String[] whereValue = { String.valueOf(rmPlayer.getPlayerId()) };
        db.delete(PlayerTable.TABLE_NAME, PlayerTable.Cols.PLAYERID + " = ?" , whereValue);
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
