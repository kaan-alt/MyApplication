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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        FragmentManager fm = getSupportFragmentManager();
        StatusBarFragment statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragNavigation);
        if(statusFrag == null)
        {
            statusFrag = new StatusBarFragment();
            fm.beginTransaction()
                    .add(R.id.statBarFragNavigation, statusFrag)
                    .commit();
        }

        //Buttons
        northButton = (Button) findViewById(R.id.northButton);
        eastButton = (Button) findViewById(R.id.eastButton);
        southButton = (Button) findViewById(R.id.southButton);
        westButton = (Button) findViewById(R.id.westButton);
        optionButton = (Button) findViewById(R.id.optionButton);



        //Creating player and map
        final GameData theGameData = new GameData();
        theGameData.setInstance(theGameData);

        updateUIElements(theGameData);



        eastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((theGameData.getInstance().getPlayer().getColLocation() + 1) > theGameData.getMaxCol()) {
                    throw new IllegalArgumentException("Cant go anymore east!");
                } else {
                    theGameData.getInstance().getPlayer().setColLocation(theGameData.getInstance().getPlayer().getColLocation() + 1);
                    updatePlayerHealth(theGameData);
                    updateUIElements(theGameData);
                }
            }
        });

        westButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((theGameData.getInstance().getPlayer().getColLocation() - 1) < 0) {
                    throw new IllegalArgumentException("Cant go anymore west!");
                } else {
                    theGameData.getInstance().getPlayer().setColLocation(theGameData.getInstance().getPlayer().getColLocation() - 1);
                    updatePlayerHealth(theGameData);
                    updateUIElements(theGameData);
                }
            }
        });

        southButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((theGameData.getInstance().getPlayer().getRowLocation() + 1) > theGameData.getMaxRow()) {
                    throw new IllegalArgumentException("Cant go anymore south!");
                } else {
                    theGameData.getInstance().getPlayer().setRowLocation(theGameData.getInstance().getPlayer().getRowLocation() + 1);
                    updatePlayerHealth(theGameData);
                    updateUIElements(theGameData);
                }
            }
        });

        northButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((theGameData.getInstance().getPlayer().getRowLocation() - 1) < 0) {
                    throw new IllegalArgumentException("Cant go anymore north!");
                } else {
                    theGameData.getInstance().getPlayer().setRowLocation(theGameData.getInstance().getPlayer().getRowLocation() -1);
                    updatePlayerHealth(theGameData);
                    updateUIElements(theGameData);
                }
            }
        });



        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(theGameData.getGrid()[theGameData.getInstance().getPlayer().getRowLocation()][theGameData.getInstance().getPlayer().getColLocation()].isTown()) {
                    Intent intent = MarketActivity.getIntent(MainActivity.this, theGameData.getInstance().getPlayer().getRowLocation(), theGameData.getInstance().getPlayer().getColLocation(), theGameData.getInstance().getPlayer().getCash(), theGameData.getInstance().getPlayer().getPlayerHealth(), theGameData.getInstance().getPlayer().getEquipmentMass(), theGameData.getInstance().getPlayer().getEquipment(), getCurrentLocationItems(theGameData.getInstance().getPlayer(), theGameData));
                    startActivityForResult(intent, REQUEST_CODE_MARKET);
                }
                else
                {
                    Intent intent = WildernessActivity.getIntent(MainActivity.this, theGameData.getInstance().getPlayer().getRowLocation(), theGameData.getInstance().getPlayer().getColLocation(), theGameData.getInstance().getPlayer().getCash(), theGameData.getInstance().getPlayer().getPlayerHealth(), theGameData.getInstance().getPlayer().getEquipmentMass(), theGameData.getInstance().getPlayer().getEquipment(), getCurrentLocationItems(theGameData.getInstance().getPlayer(), theGameData));
                    startActivityForResult(intent, REQUEST_CODE_WILDERNESS);
                }
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnedData)
    {
        if(resultCode == RESULT_OK &&
                requestCode == REQUEST_CODE_MARKET)
        {
            FragmentManager fm = getSupportFragmentManager();
            StatusBarFragment statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragNavigation);
            if(statusFrag == null)
            {
                statusFrag = new StatusBarFragment();
                fm.beginTransaction()
                        .add(R.id.statBarFragNavigation, statusFrag)
                        .commit();
            }

            //Buttons
            northButton = (Button) findViewById(R.id.northButton);
            eastButton = (Button) findViewById(R.id.eastButton);
            southButton = (Button) findViewById(R.id.southButton);
            westButton = (Button) findViewById(R.id.westButton);
            optionButton = (Button) findViewById(R.id.optionButton);



            //Creating player and map
            final GameData theGameData = new GameData();

            updateUIElements(theGameData);



            eastButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((theGameData.getInstance().getPlayer().getColLocation() + 1) > theGameData.getMaxCol()) {
                        throw new IllegalArgumentException("Cant go anymore east!");
                    } else {
                        theGameData.getInstance().getPlayer().setColLocation(theGameData.getInstance().getPlayer().getColLocation() + 1);
                        updatePlayerHealth(theGameData);
                        updateUIElements(theGameData);
                    }
                }
            });

            westButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((theGameData.getInstance().getPlayer().getColLocation() - 1) < 0) {
                        throw new IllegalArgumentException("Cant go anymore west!");
                    } else {
                        theGameData.getInstance().getPlayer().setColLocation(theGameData.getInstance().getPlayer().getColLocation() - 1);
                        updatePlayerHealth(theGameData);
                        updateUIElements(theGameData);
                    }
                }
            });

            southButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((theGameData.getInstance().getPlayer().getRowLocation() + 1) > theGameData.getMaxRow()) {
                        throw new IllegalArgumentException("Cant go anymore south!");
                    } else {
                        theGameData.getInstance().getPlayer().setRowLocation(theGameData.getInstance().getPlayer().getRowLocation() + 1);
                        updatePlayerHealth(theGameData);
                        updateUIElements(theGameData);
                    }
                }
            });

            northButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((theGameData.getInstance().getPlayer().getRowLocation() - 1) < 0) {
                        throw new IllegalArgumentException("Cant go anymore north!");
                    } else {
                        theGameData.getInstance().getPlayer().setRowLocation(theGameData.getInstance().getPlayer().getRowLocation() -1);
                        updatePlayerHealth(theGameData);
                        updateUIElements(theGameData);
                    }
                }
            });



            optionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(theGameData.getGrid()[theGameData.getInstance().getPlayer().getRowLocation()][theGameData.getInstance().getPlayer().getColLocation()].isTown()) {
                        Intent intent = MarketActivity.getIntent(MainActivity.this, theGameData.getInstance().getPlayer().getRowLocation(), theGameData.getInstance().getPlayer().getColLocation(), theGameData.getInstance().getPlayer().getCash(), theGameData.getInstance().getPlayer().getPlayerHealth(), theGameData.getInstance().getPlayer().getEquipmentMass(), theGameData.getInstance().getPlayer().getEquipment(), getCurrentLocationItems(theGameData.getInstance().getPlayer(), theGameData));
                        startActivityForResult(intent, REQUEST_CODE_MARKET);
                    }
                    else
                    {
                        Intent intent = WildernessActivity.getIntent(MainActivity.this, theGameData.getInstance().getPlayer().getRowLocation(), theGameData.getInstance().getPlayer().getColLocation(), theGameData.getInstance().getPlayer().getCash(), theGameData.getInstance().getPlayer().getPlayerHealth(), theGameData.getInstance().getPlayer().getEquipmentMass(), theGameData.getInstance().getPlayer().getEquipment(), getCurrentLocationItems(theGameData.getInstance().getPlayer(), theGameData));
                        startActivityForResult(intent, REQUEST_CODE_WILDERNESS);
                    }
                }
            });

        }
        else if(resultCode == RESULT_OK &&
                requestCode == REQUEST_CODE_WILDERNESS)
        {
            FragmentManager fm = getSupportFragmentManager();
            StatusBarFragment statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragNavigation);
            if(statusFrag == null)
            {
                statusFrag = new StatusBarFragment();
                fm.beginTransaction()
                        .add(R.id.statBarFragNavigation, statusFrag)
                        .commit();
            }

            //Buttons
            northButton = (Button) findViewById(R.id.northButton);
            eastButton = (Button) findViewById(R.id.eastButton);
            southButton = (Button) findViewById(R.id.southButton);
            westButton = (Button) findViewById(R.id.westButton);
            optionButton = (Button) findViewById(R.id.optionButton);



            //Creating player and map
            final GameData theGameData = new GameData();

            updateUIElements(theGameData);



            eastButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((theGameData.getInstance().getPlayer().getColLocation() + 1) > theGameData.getMaxCol()) {
                        throw new IllegalArgumentException("Cant go anymore east!");
                    } else {
                        theGameData.getInstance().getPlayer().setColLocation(theGameData.getInstance().getPlayer().getColLocation() + 1);
                        updatePlayerHealth(theGameData);
                        updateUIElements(theGameData);
                    }
                }
            });

            westButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((theGameData.getInstance().getPlayer().getColLocation() - 1) < 0) {
                        throw new IllegalArgumentException("Cant go anymore west!");
                    } else {
                        theGameData.getInstance().getPlayer().setColLocation(theGameData.getInstance().getPlayer().getColLocation() - 1);
                        updatePlayerHealth(theGameData);
                        updateUIElements(theGameData);
                    }
                }
            });

            southButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((theGameData.getInstance().getPlayer().getRowLocation() + 1) > theGameData.getMaxRow()) {
                        throw new IllegalArgumentException("Cant go anymore south!");
                    } else {
                        theGameData.getInstance().getPlayer().setRowLocation(theGameData.getInstance().getPlayer().getRowLocation() + 1);
                        updatePlayerHealth(theGameData);
                        updateUIElements(theGameData);
                    }
                }
            });

            northButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((theGameData.getInstance().getPlayer().getRowLocation() - 1) < 0) {
                        throw new IllegalArgumentException("Cant go anymore north!");
                    } else {
                        theGameData.getInstance().getPlayer().setRowLocation(theGameData.getInstance().getPlayer().getRowLocation() -1);
                        updatePlayerHealth(theGameData);
                        updateUIElements(theGameData);
                    }
                }
            });



            optionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(theGameData.getGrid()[theGameData.getInstance().getPlayer().getRowLocation()][theGameData.getInstance().getPlayer().getColLocation()].isTown()) {
                        Intent intent = MarketActivity.getIntent(MainActivity.this, theGameData.getInstance().getPlayer().getRowLocation(), theGameData.getInstance().getPlayer().getColLocation(), theGameData.getInstance().getPlayer().getCash(), theGameData.getInstance().getPlayer().getPlayerHealth(), theGameData.getInstance().getPlayer().getEquipmentMass(), theGameData.getInstance().getPlayer().getEquipment(), getCurrentLocationItems(theGameData.getInstance().getPlayer(), theGameData));
                        startActivityForResult(intent, REQUEST_CODE_MARKET);
                    }
                    else
                    {
                        Intent intent = WildernessActivity.getIntent(MainActivity.this, theGameData.getInstance().getPlayer().getRowLocation(), theGameData.getInstance().getPlayer().getColLocation(), theGameData.getInstance().getPlayer().getCash(), theGameData.getInstance().getPlayer().getPlayerHealth(), theGameData.getInstance().getPlayer().getEquipmentMass(), theGameData.getInstance().getPlayer().getEquipment(), getCurrentLocationItems(theGameData.getInstance().getPlayer(), theGameData));
                        startActivityForResult(intent, REQUEST_CODE_WILDERNESS);
                    }
                }
            });
        }

    }

    public void updateUIElements(GameData theGameData)
    {
        //Text
        descriptionDisplay = (EditText) findViewById(R.id.descriptionDisplay);
        townDisplay = (EditText) findViewById(R.id.townDisplay);

        //update location description
        descriptionDisplay.setText("Col: "+ Integer.toString(theGameData.getInstance().getPlayer().getColLocation()) + ", "+ "Row: " + Integer.toString(theGameData.getInstance().getPlayer().getRowLocation()));

        //update if 'town or wild' string
        if(theGameData.grid[theGameData.getInstance().getPlayer().getRowLocation()][theGameData.getInstance().getPlayer().getColLocation()].isTown())
        {
            townDisplay.setText("Town");
        }
        else
        {
            townDisplay.setText("Wilderness");
        }
    }

    public ArrayList<Item> getCurrentLocationItems(Player thePlayer, GameData theGameData)
    {
        ArrayList<Item> theLocationsItems = theGameData.getGrid()[thePlayer.getRowLocation()][thePlayer.getColLocation()].getItems();
        return theLocationsItems;
    }

    public void updatePlayerHealth(GameData theGameData)
    {
        theGameData.getInstance().getPlayer().setPlayerHealth
                (Math.max(0.0, theGameData.getInstance().getPlayer().getPlayerHealth() - 5.0 - (theGameData.getInstance().getPlayer().getEquipmentMass() / 2.0)));
    }

    public Player createNewPlayer()
    {
        ArrayList<Equipment> emptyList = new ArrayList<>();
        Player theNewPlayer = new Player(0,0,200,100.0,15.0, emptyList );
        return theNewPlayer;
    }

}
