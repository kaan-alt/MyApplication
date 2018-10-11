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

    private static final int REQUEST_CODE_MARKET = 1;
    private static final int REQUEST_CODE_WILDERNESS = 2;

    private Button northButton;
    private Button eastButton;
    private Button southButton;
    private Button westButton;
    private Button optionButton;
    private EditText descriptionDisplay;
    private EditText townDisplay;

    //Creating player and map
    final GameData theGameData = new GameData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

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

        if(theGameData == null)
        {
            GameData.setInstance(new GameData());
        }



        updateUIElements();



        eastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((GameData.getInstance().getPlayer().getColLocation() + 1) > GameData.getInstance().getMaxCol()) {
                    throw new IllegalArgumentException("Cant go anymore east!");
                } else {
                    GameData.getInstance().getPlayer().setColLocation(GameData.getInstance().getPlayer().getColLocation() + 1);
                    updatePlayerHealth();
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setExplored(true);
                    updateUIElements();
                }
            }
        });

        westButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((GameData.getInstance().getPlayer().getColLocation() - 1) < 0) {
                    throw new IllegalArgumentException("Cant go anymore west!");
                } else {
                    GameData.getInstance().getPlayer().setColLocation(GameData.getInstance().getPlayer().getColLocation() - 1);
                    updatePlayerHealth();
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setExplored(true);
                    updateUIElements();
                }
            }
        });

        southButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((GameData.getInstance().getPlayer().getRowLocation() + 1) > GameData.getInstance().getMaxRow()) {
                    throw new IllegalArgumentException("Cant go anymore south!");
                } else {
                    GameData.getInstance().getPlayer().setRowLocation(GameData.getInstance().getPlayer().getRowLocation() + 1);
                    updatePlayerHealth();
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setExplored(true);
                    updateUIElements();
                }
            }
        });

        northButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((GameData.getInstance().getPlayer().getRowLocation() - 1) < 0) {
                    throw new IllegalArgumentException("Cant go anymore north!");
                } else {
                    GameData.getInstance().getPlayer().setRowLocation(GameData.getInstance().getPlayer().getRowLocation() -1);
                    updatePlayerHealth();
                    GameData.getInstance().getGrid()[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setExplored(true);
                    updateUIElements();
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


    }


    public void updateUIElements()
    {
        //Text
        descriptionDisplay = (EditText) findViewById(R.id.descriptionDisplay);


        //update location description
        descriptionDisplay.setText("Col: "+ Integer.toString(GameData.getInstance().getPlayer().getColLocation()) + ", "+ "Row: " + Integer.toString(GameData.getInstance().getPlayer().getRowLocation()));


    }

    public ArrayList<Item> getCurrentLocationItems(Player thePlayer, GameData theGameData)
    {
        ArrayList<Item> theLocationsItems = theGameData.getGrid()[thePlayer.getRowLocation()][thePlayer.getColLocation()].getItems();
        return theLocationsItems;
    }

    public void updatePlayerHealth()
    {
        GameData.getInstance().getPlayer().setPlayerHealth
                (Math.max(0.0, GameData.getInstance().getPlayer().getPlayerHealth() - 5.0 - (GameData.getInstance().getPlayer().getEquipmentMass() / 2.0)));
    }

    public Player createNewPlayer()
    {
        ArrayList<Equipment> emptyList = new ArrayList<>();
        Player theNewPlayer = new Player(0,0,200,100.0,15.0, emptyList );
        return theNewPlayer;
    }

}
