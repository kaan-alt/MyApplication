package au.edu.curtin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MARKET = 1;
    private static final int REQUEST_CODE_WILDERNESS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        //Buttons
        Button northButton = (Button) findViewById(R.id.northButton);
        Button eastButton = (Button) findViewById(R.id.eastButton);
        Button southButton = (Button) findViewById(R.id.southButton);
        Button westButton = (Button) findViewById(R.id.westButton);
        Button optionButton = (Button) findViewById(R.id.optionButton);
        final Button restartButton = (Button) findViewById(R.id.restartButton);

        //Creating player and map
        final Player thePlayer = createNewPlayer();
        final GameMap theGameMap = new GameMap();

        updateUIElements(thePlayer, theGameMap);

        eastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((thePlayer.getColLocation() + 1) > theGameMap.maxCol) {
                    throw new IllegalArgumentException("Cant go anymore east!");
                } else {
                    thePlayer.setColLocation(thePlayer.getColLocation() + 1);
                    updatePlayerHealth(thePlayer);
                    updateUIElements(thePlayer, theGameMap);
                }
            }
        });

        westButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((thePlayer.getColLocation() - 1) < 0) {
                    throw new IllegalArgumentException("Cant go anymore west!");
                } else {
                    thePlayer.setColLocation(thePlayer.getColLocation() - 1);
                    updatePlayerHealth(thePlayer);
                    updateUIElements(thePlayer, theGameMap);
                }
            }
        });

        southButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((thePlayer.getRowLocation() + 1) > theGameMap.maxRow) {
                    throw new IllegalArgumentException("Cant go anymore south!");
                } else {
                    thePlayer.setRowLocation(thePlayer.getRowLocation() + 1);
                    updatePlayerHealth(thePlayer);
                    updateUIElements(thePlayer, theGameMap);
                }
            }
        });

        northButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((thePlayer.getRowLocation() - 1) < 0) {
                    throw new IllegalArgumentException("Cant go anymore north!");
                } else {
                    thePlayer.setRowLocation(thePlayer.getRowLocation() -1);
                    updatePlayerHealth(thePlayer);
                    updateUIElements(thePlayer, theGameMap);
                }
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Player restartPlayer = createNewPlayer();
                updateAllPlayerFeilds(thePlayer, restartPlayer);
                updateUIElements(thePlayer, theGameMap);
            }
        });

        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(theGameMap.getGrid()[thePlayer.getRowLocation()][thePlayer.getColLocation()].isTown()) {
                    Intent intent = MarketActivity.getIntent(MainActivity.this, thePlayer.getRowLocation(), thePlayer.getColLocation(), thePlayer.getCash(), thePlayer.getPlayerHealth(), thePlayer.getEquipmentMass(), thePlayer.getEquipment(), getCurrentLocationItems(thePlayer, theGameMap));
                    startActivityForResult(intent, REQUEST_CODE_MARKET);
                }
                else
                {
                    Intent intent = WildernessActivity.getIntent(MainActivity.this, thePlayer.getRowLocation(), thePlayer.getColLocation(), thePlayer.getCash(), thePlayer.getPlayerHealth(), thePlayer.getEquipmentMass(), thePlayer.getEquipment(), getCurrentLocationItems(thePlayer, theGameMap));
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
            //Buttons
            Button northButton = (Button) findViewById(R.id.northButton);
            Button eastButton = (Button) findViewById(R.id.eastButton);
            Button southButton = (Button) findViewById(R.id.southButton);
            Button westButton = (Button) findViewById(R.id.westButton);
            Button optionButton = (Button) findViewById(R.id.optionButton);
            final Button restartButton = (Button) findViewById(R.id.restartButton);

            final GameMap theGameMap = new GameMap();
            final Player thePlayer = createNewPlayer();
            thePlayer.setRowLocation(MarketActivity.getMPlayerRow(returnedData));
            thePlayer.setColLocation(MarketActivity.getMPlayerCol(returnedData));
            thePlayer.setCash(MarketActivity.getMPlayerCash(returnedData));
            thePlayer.setPlayerHealth(MarketActivity.getMPlayerHealth(returnedData));
            thePlayer.setEquipmentMass(MarketActivity.getMPlayerMass(returnedData));
            thePlayer.setEquipment(MarketActivity.getMPlayerEquipment(returnedData));
            //!!!Not sure if the area's items are actually persistent
            theGameMap.getGrid()[thePlayer.getRowLocation()][thePlayer.getColLocation()].setItems(MarketActivity.getMAreaEquipment(returnedData));
            updateUIElements(thePlayer,theGameMap);

            eastButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((thePlayer.getColLocation() + 1) > theGameMap.maxCol) {
                        throw new IllegalArgumentException("Cant go anymore east!");
                    } else {
                        thePlayer.setColLocation(thePlayer.getColLocation() + 1);
                        updatePlayerHealth(thePlayer);
                        updateUIElements(thePlayer, theGameMap);
                    }
                }
            });

            westButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((thePlayer.getColLocation() - 1) < 0) {
                        throw new IllegalArgumentException("Cant go anymore west!");
                    } else {
                        thePlayer.setColLocation(thePlayer.getColLocation() - 1);
                        updatePlayerHealth(thePlayer);
                        updateUIElements(thePlayer, theGameMap);
                    }
                }
            });

            southButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((thePlayer.getRowLocation() + 1) > theGameMap.maxRow) {
                        throw new IllegalArgumentException("Cant go anymore south!");
                    } else {
                        thePlayer.setRowLocation(thePlayer.getRowLocation() + 1);
                        updatePlayerHealth(thePlayer);
                        updateUIElements(thePlayer, theGameMap);
                    }
                }
            });

            northButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((thePlayer.getRowLocation() - 1) < 0) {
                        throw new IllegalArgumentException("Cant go anymore north!");
                    } else {
                        thePlayer.setRowLocation(thePlayer.getRowLocation() -1);
                        updatePlayerHealth(thePlayer);
                        updateUIElements(thePlayer, theGameMap);
                    }
                }
            });

            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Player restartPlayer = createNewPlayer();
                    updateAllPlayerFeilds(thePlayer, restartPlayer);
                    updateUIElements(thePlayer, theGameMap);
                }
            });

            optionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(theGameMap.getGrid()[thePlayer.getRowLocation()][thePlayer.getColLocation()].isTown()) {
                        Intent intent = MarketActivity.getIntent(MainActivity.this, thePlayer.getRowLocation(), thePlayer.getColLocation(), thePlayer.getCash(), thePlayer.getPlayerHealth(), thePlayer.getEquipmentMass(), thePlayer.getEquipment(), getCurrentLocationItems(thePlayer, theGameMap));
                        startActivityForResult(intent, REQUEST_CODE_MARKET);
                    }
                    else
                    {
                        Intent intent = WildernessActivity.getIntent(MainActivity.this, thePlayer.getRowLocation(), thePlayer.getColLocation(), thePlayer.getCash(), thePlayer.getPlayerHealth(), thePlayer.getEquipmentMass(), thePlayer.getEquipment(), getCurrentLocationItems(thePlayer, theGameMap));
                        startActivityForResult(intent, REQUEST_CODE_WILDERNESS);
                    }
                }
            });

        }
        else if(resultCode == RESULT_OK &&
                requestCode == REQUEST_CODE_WILDERNESS)
        {
            //Buttons
            Button northButton = (Button) findViewById(R.id.northButton);
            Button eastButton = (Button) findViewById(R.id.eastButton);
            Button southButton = (Button) findViewById(R.id.southButton);
            Button westButton = (Button) findViewById(R.id.westButton);
            Button optionButton = (Button) findViewById(R.id.optionButton);
            final Button restartButton = (Button) findViewById(R.id.restartButton);

            final GameMap theGameMap = new GameMap();
            final Player thePlayer = createNewPlayer();
            thePlayer.setRowLocation(WildernessActivity.getWPlayerRow(returnedData));
            thePlayer.setColLocation(WildernessActivity.getWPlayerCol(returnedData));
            thePlayer.setCash(WildernessActivity.getWPlayerCash(returnedData));
            thePlayer.setPlayerHealth(WildernessActivity.getWPlayerHealth(returnedData));
            thePlayer.setEquipmentMass(WildernessActivity.getWPlayerMass(returnedData));
            thePlayer.setEquipment(WildernessActivity.getWPlayerEquipment(returnedData));
            //!!!Not sure if the area's items are actually persistent
            theGameMap.getGrid()[thePlayer.getRowLocation()][thePlayer.getColLocation()].setItems(WildernessActivity.getWAreaEquipment(returnedData));
            updateUIElements(thePlayer,theGameMap);

            eastButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((thePlayer.getColLocation() + 1) > theGameMap.maxCol) {
                        throw new IllegalArgumentException("Cant go anymore east!");
                    } else {
                        thePlayer.setColLocation(thePlayer.getColLocation() + 1);
                        updatePlayerHealth(thePlayer);
                        updateUIElements(thePlayer, theGameMap);
                    }
                }
            });

            westButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((thePlayer.getColLocation() - 1) < 0) {
                        throw new IllegalArgumentException("Cant go anymore west!");
                    } else {
                        thePlayer.setColLocation(thePlayer.getColLocation() - 1);
                        updatePlayerHealth(thePlayer);
                        updateUIElements(thePlayer, theGameMap);
                    }
                }
            });

            southButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((thePlayer.getRowLocation() + 1) > theGameMap.maxRow) {
                        throw new IllegalArgumentException("Cant go anymore south!");
                    } else {
                        thePlayer.setRowLocation(thePlayer.getRowLocation() + 1);
                        updatePlayerHealth(thePlayer);
                        updateUIElements(thePlayer, theGameMap);
                    }
                }
            });

            northButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((thePlayer.getRowLocation() - 1) < 0) {
                        throw new IllegalArgumentException("Cant go anymore north!");
                    } else {
                        thePlayer.setRowLocation(thePlayer.getRowLocation() -1);
                        updatePlayerHealth(thePlayer);
                        updateUIElements(thePlayer, theGameMap);
                    }
                }
            });

            restartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Player restartPlayer = createNewPlayer();
                    updateAllPlayerFeilds(thePlayer, restartPlayer);
                    updateUIElements(thePlayer, theGameMap);
                }
            });

            optionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(theGameMap.getGrid()[thePlayer.getRowLocation()][thePlayer.getColLocation()].isTown()) {
                        Intent intent = MarketActivity.getIntent(MainActivity.this, thePlayer.getRowLocation(), thePlayer.getColLocation(), thePlayer.getCash(), thePlayer.getPlayerHealth(), thePlayer.getEquipmentMass(), thePlayer.getEquipment(), getCurrentLocationItems(thePlayer, theGameMap));
                        startActivityForResult(intent, REQUEST_CODE_MARKET);
                    }
                    else
                    {
                        Intent intent = WildernessActivity.getIntent(MainActivity.this, thePlayer.getRowLocation(), thePlayer.getColLocation(), thePlayer.getCash(), thePlayer.getPlayerHealth(), thePlayer.getEquipmentMass(), thePlayer.getEquipment(), getCurrentLocationItems(thePlayer, theGameMap));
                        startActivityForResult(intent, REQUEST_CODE_WILDERNESS);
                    }
                }
            });
        }

    }

    public void updateUIElements(Player thePlayer, GameMap theGameMap)
    {
        //Text
        EditText descriptionDisplay = (EditText) findViewById(R.id.descriptionDisplay);
        EditText townDisplay = (EditText) findViewById(R.id.townDisplay);
        EditText healthDisplay = (EditText) findViewById(R.id.healthDisplay);
        EditText equipmentMassDisplay = (EditText) findViewById(R.id.equipmentMassDisplay);
        EditText cashDisplay = (EditText) findViewById(R.id.cashDisplay);
        healthDisplay.setText("Health: " + Double.toString(thePlayer.getPlayerHealth()));
        cashDisplay.setText("Cash: " + Integer.toString(thePlayer.getCash()));
        equipmentMassDisplay.setText("Mass: " + Double.toString(thePlayer.getEquipmentMass()));
        descriptionDisplay.setText("Col: "+ Integer.toString(thePlayer.getColLocation()) + ", "+ "Row: " + Integer.toString(thePlayer.getRowLocation()));



        if(theGameMap.grid[thePlayer.getRowLocation()][thePlayer.getColLocation()].isTown())
        {
            townDisplay.setText("Town");
        }
        else
        {
            townDisplay.setText("Wilderness");
        }
    }

    public ArrayList<Item> getCurrentLocationItems(Player thePlayer, GameMap theGameMap)
    {
        ArrayList<Item> theLocationsItems = theGameMap.getGrid()[thePlayer.getRowLocation()][thePlayer.getColLocation()].getItems();
        return theLocationsItems;
    }

    public void updatePlayerHealth(Player thePlayer)
    {
        thePlayer.setPlayerHealth(Math.max(0.0, thePlayer.getPlayerHealth() - 5.0 - (thePlayer.equipmentMass / 2.0)));
    }

    public Player createNewPlayer()
    {
        ArrayList<Equipment> emptyList = new ArrayList<>();
        Player theNewPlayer = new Player(0,0,200,100.0,15.0, emptyList );
        return theNewPlayer;
    }

    public void updateAllPlayerFeilds(Player thePlayer, Player restartPlayer)
    {
        thePlayer.setPlayerHealth(restartPlayer.getPlayerHealth());
        thePlayer.setColLocation(restartPlayer.getColLocation());
        thePlayer.setRowLocation(restartPlayer.getRowLocation());
        thePlayer.setCash(restartPlayer.getCash());
        thePlayer.setEquipmentMass(restartPlayer.getEquipmentMass());
    }
}
