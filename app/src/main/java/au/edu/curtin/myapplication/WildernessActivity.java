package au.edu.curtin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class WildernessActivity extends AppCompatActivity implements PickWildernessFragment.OnPickWildernessViewFragmentLis, UserWildernessFragment.OnUserWildernessViewFragmentLis {

    private static final String PLAYER_HEALTH = "com.MainActivity.playerHealth";
    private static final String PLAYER_CASH = "com.MainActivity.playerCash";
    private static final String PLAYER_EQUIPMENTMASS = "com.MainActivity.playerEquipmentMass";
    private static final String PLAYER_EQUIPMENT = "com.MainActivity.playerEquipment";
    private static final String AREA_ITEMS = "com.MainActivity.areaItems";
    private static final String PLAYER_ROW = "com.MainActivity.rowLocation";
    private static final String PLAYER_COL = "com.MainActivity.colLocation";

    Button leaveButton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilderness);



        //Buttons
        leaveButton = (Button) findViewById(R.id.leaveButton);


        //Fragment manager
        FragmentManager fm = getSupportFragmentManager();
        StatusBarFragment statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragWilderness);
        if(statusFrag == null)
        {
            statusFrag = new StatusBarFragment();
            fm.beginTransaction()
                    .add(R.id.statBarFragWilderness, statusFrag)
                    .commit();
        }
        PickWildernessFragment pickFrag = (PickWildernessFragment) fm.findFragmentById(R.id.pickWildernessFrag);
        if(pickFrag == null)
        {
            pickFrag = new PickWildernessFragment();
            fm.beginTransaction()
                    .add(R.id.pickWildernessFrag, pickFrag)
                    .commit();
        }
        UserWildernessFragment userWildernessFrag = (UserWildernessFragment) fm.findFragmentById(R.id.userInventWildernessFrag);
        if(userWildernessFrag == null)
        {
            userWildernessFrag = new UserWildernessFragment();
            fm.beginTransaction()
                    .add(R.id.userInventWildernessFrag, userWildernessFrag)
                    .commit();
        }





        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WildernessActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    public static int getWPlayerRow(Intent intent)
    {
        return intent.getIntExtra(PLAYER_ROW,0);
    }

    public static int getWPlayerCol(Intent intent)
    {
        return intent.getIntExtra(PLAYER_COL,0);
    }

    public static double getWPlayerHealth(Intent intent)
    {
        return intent.getDoubleExtra(PLAYER_HEALTH,0);
    }
    public static int getWPlayerCash(Intent intent)
    {
        return intent.getIntExtra(PLAYER_CASH, 0);
    }
    public static double getWPlayerMass(Intent intent)
    {
        return intent.getDoubleExtra(PLAYER_EQUIPMENTMASS, 0);
    }
    public static ArrayList<Equipment> getWPlayerEquipment(Intent intent)
    {
        return (ArrayList<Equipment>)intent.getSerializableExtra(PLAYER_EQUIPMENT);
    }
    public static ArrayList<Item> getWAreaEquipment(Intent intent)
    {
        return (ArrayList<Item>)intent.getSerializableExtra(AREA_ITEMS);
    }



    /*public void wUpdatePlayerUIElements(Player wildernessPlayer)
    {
        statusFrag.updateStatusBar();
    }*/

    public void wildernessReplaceAllFragments()
    {
        //Fragment manager
        FragmentManager fm = getSupportFragmentManager();
        StatusBarFragment statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragWilderness);
        statusFrag = new StatusBarFragment();
        fm.beginTransaction()
                    .replace(R.id.statBarFragWilderness, statusFrag)
                    .commit();

        PickWildernessFragment pickFrag = (PickWildernessFragment) fm.findFragmentById(R.id.pickWildernessFrag);
        pickFrag = new PickWildernessFragment();
        fm.beginTransaction()
                    .replace(R.id.pickWildernessFrag, pickFrag)
                    .commit();

        UserWildernessFragment userWildernessFrag = (UserWildernessFragment) fm.findFragmentById(R.id.userInventWildernessFrag);
        userWildernessFrag = new UserWildernessFragment();
        fm.beginTransaction()
                    .replace(R.id.userInventWildernessFrag, userWildernessFrag)
                    .commit();
    }
}



