package au.edu.curtin.myapplication;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PlayerList playerList;

    private Button northButton;
    private Button eastButton;
    private Button southButton;
    private Button westButton;
    private Button optionButton;
    private Button overviewButton;
    private EditText descriptionDisplay;

    //Creating player and map
    final GameData theGameData = new GameData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load in database
        playerList = new PlayerList(getApplicationContext());
        playerList.load();

        //Check if its the first time opening the app
        try {
            //If there is a Player in database already updaptePlayer in memory
            //When main activity is call from another activty, check to see if playerInstance was altered. If so update database
            playerList.get(0);
            if(!playerList.get(0).equals(GameData.getInstance().getPlayer()))
            {
                GameData.getInstance().getPlayer().setPlayerId(0);
                playerList.edit(GameData.getInstance().getPlayer());
            }
            updatePlayerInMemory();
        }
        catch(IndexOutOfBoundsException e){
            GameData.getInstance().getPlayer().setPlayerId(0);
            playerList.add(GameData.getInstance().getPlayer());
        }


        setContentView(R.layout.activity_navigation);

        //Fragments
        FragmentManager fm = getSupportFragmentManager();
        StatusBarFragment statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragNavigation);
        AreaInfoFragment infoFrag = (AreaInfoFragment) fm.findFragmentById(R.id.areaInfoFragNavigation);
        if(statusFrag == null)
        {
            statusFrag = new StatusBarFragment();
            fm.beginTransaction()
                    .add(R.id.statBarFragNavigation, statusFrag)
                    .commit();
        }
        if(infoFrag == null)
        {
            infoFrag = new AreaInfoFragment();
            fm.beginTransaction()
                    .add(R.id.areaInfoFragNavigation, infoFrag)
                    .commit();
        }

        //Buttons
        northButton = (Button) findViewById(R.id.northButton);
        eastButton = (Button) findViewById(R.id.eastButton);
        southButton = (Button) findViewById(R.id.southButton);
        westButton = (Button) findViewById(R.id.westButton);
        optionButton = (Button) findViewById(R.id.optionButton);
        overviewButton = (Button) findViewById(R.id.overviewButton);

        if(theGameData == null)
        {
            GameData.setInstance(new GameData());
        }



        updateUIElements();

        //Starting location booleans updated
        GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setExplored(true);
        GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setCurrentOccupied(true);




        eastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((GameData.getInstance().getPlayer().getColLocation() + 1) > GameData.getInstance().getMaxCol()) {
                    throw new IllegalArgumentException("Cant go anymore east!");
                } else {
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setCurrentOccupied(false);
                    GameData.getInstance().getPlayer().setColLocation(GameData.getInstance().getPlayer().getColLocation() + 1);
                    updatePlayerHealth();
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setExplored(true);
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setCurrentOccupied(true);
                    updateUIElements();
                    refreshStatusFrag();
                    refreshInfoFrag();

                    //Update player database
                    GameData.getInstance().getPlayer().setPlayerId(0);
                    playerList.edit(GameData.getInstance().getPlayer());
                }
            }
        });

        westButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((GameData.getInstance().getPlayer().getColLocation() - 1) < 0) {
                    throw new IllegalArgumentException("Cant go anymore west!");
                } else {
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setCurrentOccupied(false);
                    GameData.getInstance().getPlayer().setColLocation(GameData.getInstance().getPlayer().getColLocation() - 1);
                    updatePlayerHealth();
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setExplored(true);
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setCurrentOccupied(true);
                    updateUIElements();
                    refreshStatusFrag();
                    refreshInfoFrag();

                    //Update player database
                    GameData.getInstance().getPlayer().setPlayerId(0);
                    playerList.edit(GameData.getInstance().getPlayer());
                }
            }
        });

        southButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((GameData.getInstance().getPlayer().getRowLocation() + 1) > GameData.getInstance().getMaxRow()) {
                    throw new IllegalArgumentException("Cant go anymore south!");
                } else {
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setCurrentOccupied(false);
                    GameData.getInstance().getPlayer().setRowLocation(GameData.getInstance().getPlayer().getRowLocation() + 1);
                    updatePlayerHealth();
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setExplored(true);
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setCurrentOccupied(true);
                    updateUIElements();
                    refreshStatusFrag();
                    refreshInfoFrag();

                    //Update player database
                    GameData.getInstance().getPlayer().setPlayerId(0);
                    playerList.edit(GameData.getInstance().getPlayer());
                }
            }
        });

        northButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((GameData.getInstance().getPlayer().getRowLocation() - 1) < 0) {
                    throw new IllegalArgumentException("Cant go anymore north!");
                } else {
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setCurrentOccupied(false);
                    GameData.getInstance().getPlayer().setRowLocation(GameData.getInstance().getPlayer().getRowLocation() -1);
                    updatePlayerHealth();
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setExplored(true);
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setCurrentOccupied(true);
                    updateUIElements();
                    refreshStatusFrag();
                    refreshInfoFrag();

                    //Update player database
                    GameData.getInstance().getPlayer().setPlayerId(0);
                    playerList.edit(GameData.getInstance().getPlayer());
                }
            }
        });



        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].isTown()) {
                    Intent intent = new Intent(MainActivity.this, MarketActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(MainActivity.this, WildernessActivity.class);
                    startActivity(intent);
                }
            }
        });

        overviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OverviewActivity.class);
                startActivity(intent);
            }
        });


    }


    public void updateUIElements()
    {
        //Text
        descriptionDisplay = (EditText) findViewById(R.id.descriptionDisplay);


        //update location description
        descriptionDisplay.setText("Col: "+ Integer.toString(GameData.getInstance().getPlayer().getColLocation()) + ", "+ "Row: " + Integer.toString(GameData.getInstance().getPlayer().getRowLocation()));


    }


    //Formula to update player health
    public void updatePlayerHealth()
    {
        GameData.getInstance().getPlayer().setPlayerHealth
                (Math.max(0.0, GameData.getInstance().getPlayer().getPlayerHealth() - 5.0 - (GameData.getInstance().getPlayer().getEquipmentMass() / 2.0)));
    }

    //Refreshes the status fragment
    public void refreshStatusFrag()
    {
        FragmentManager fm = getSupportFragmentManager();
        StatusBarFragment statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragNavigation);
        statusFrag = new StatusBarFragment();
        fm.beginTransaction()
                .replace(R.id.statBarFragNavigation, statusFrag)
                .commit();
    }

    public void refreshInfoFrag()
    {
        FragmentManager fm = getSupportFragmentManager();
        AreaInfoFragment infoFrag = (AreaInfoFragment) fm.findFragmentById(R.id.areaInfoFragNavigation);
        infoFrag = new AreaInfoFragment();
        fm.beginTransaction()
                .replace(R.id.areaInfoFragNavigation, infoFrag)
                .commit();
    }

    //When player loaded from database, need to be placed into memory
    public void updatePlayerInMemory()
    {
        GameData.getInstance().getPlayer().setRowLocation(playerList.get(0).getRowLocation());
        GameData.getInstance().getPlayer().setColLocation(playerList.get(0).getColLocation());
        GameData.getInstance().getPlayer().setCash(playerList.get(0).getCash());
        GameData.getInstance().getPlayer().setPlayerHealth(playerList.get(0).getPlayerHealth());
        GameData.getInstance().getPlayer().setEquipmentMass(playerList.get(0).getEquipmentMass());
    }

}
