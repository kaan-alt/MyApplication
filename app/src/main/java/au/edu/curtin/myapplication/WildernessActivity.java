package au.edu.curtin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class WildernessActivity extends AppCompatActivity {

    private static final String PLAYER_HEALTH = "com.MainActivity.playerHealth";
    private static final String PLAYER_CASH = "com.MainActivity.playerCash";
    private static final String PLAYER_EQUIPMENTMASS = "com.MainActivity.playerEquipmentMass";
    private static final String PLAYER_EQUIPMENT = "com.MainActivity.playerEquipment";
    private static final String AREA_ITEMS = "com.MainActivity.areaItems";
    private static final String PLAYER_ROW = "com.MainActivity.rowLocation";
    private static final String PLAYER_COL = "com.MainActivity.colLocation";

    //VARIABLES TO TRACK WHICH ITEM INDEX OF THE ITEM LIST YOU ARE AT
    private int dropCurrentIndex = 0;
    private int pickCurrentIndex =  0;

    public static Intent getIntent(Context c, int rowLocation , int colLocation, int playerCash, double playerHealth, double playerEquipmentMass, ArrayList<Equipment> playerEquipment, ArrayList<Item> areaItems) {
        Intent i = new Intent(c, WildernessActivity.class);
        i.putExtra(PLAYER_ROW, rowLocation);
        i.putExtra(PLAYER_COL, colLocation);
        i.putExtra(PLAYER_CASH, playerCash);
        i.putExtra(PLAYER_HEALTH, playerHealth);
        i.putExtra(PLAYER_EQUIPMENTMASS, playerEquipmentMass);
        i.putExtra(PLAYER_EQUIPMENT, playerEquipment);
        i.putExtra(AREA_ITEMS, areaItems);



        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilderness);



        //Buttons
        Button leaveButton = (Button) findViewById(R.id.leaveButton);
        Button pickNextButton = (Button) findViewById(R.id.pickNextButton);
        Button pickPrevButton = (Button) findViewById(R.id.pickPrevButton);
        Button dropNextButton = (Button) findViewById(R.id.dropNextButton);
        Button dropPrevButton = (Button) findViewById(R.id.dropPrevButton);
        Button pickActionButton = (Button) findViewById(R.id.pickActionButton);
        Button dropActionButton = (Button) findViewById(R.id.dropActionButton);

        //Creating another player object from the intent
        Intent i = getIntent();
        final Player wildernessPlayer = new Player(i.getIntExtra(PLAYER_ROW, 0), i.getIntExtra(PLAYER_COL,0)
                , i.getIntExtra(PLAYER_CASH, 0),i.getDoubleExtra(PLAYER_HEALTH, 0)
                , i.getDoubleExtra(PLAYER_EQUIPMENTMASS, 0), (ArrayList<Equipment>) i.getSerializableExtra(PLAYER_EQUIPMENT));

        final ArrayList<Item> wAreaItems = (ArrayList<Item>) i.getSerializableExtra(AREA_ITEMS);

        wUpdatePlayerUIElements(wildernessPlayer);
        wUpdateDropUI(wildernessPlayer, dropCurrentIndex);
        wUpdatePickUI(wAreaItems, pickCurrentIndex);

        pickNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wAreaItems.size() == pickCurrentIndex + 1)
                {
                    pickCurrentIndex = 0;
                } else {
                    pickCurrentIndex++;
                }
                wUpdatePickUI(wAreaItems, pickCurrentIndex);
            }
        });

        pickPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pickCurrentIndex == 0)
                {
                    pickCurrentIndex =  wAreaItems.size() - 1;
                } else {
                    pickCurrentIndex--;
                }
                wUpdatePickUI(wAreaItems, pickCurrentIndex);
            }
        });

        dropNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wildernessPlayer.getEquipment().size() == dropCurrentIndex + 1)
                {
                    dropCurrentIndex = 0;
                } else {
                    dropCurrentIndex++;
                }
                wUpdateDropUI(wildernessPlayer, dropCurrentIndex);
            }
        });

        dropPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dropCurrentIndex == 0)
                {
                    dropCurrentIndex =  wildernessPlayer.getEquipment().size() - 1;
                } else {
                    dropCurrentIndex--;
                }
                wUpdateDropUI(wildernessPlayer, dropCurrentIndex);
            }
        });

        pickActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(wAreaItems.get(pickCurrentIndex) instanceof Food)
                {
                    //Can have validation so that health does not exceed 100 later :)
                    wildernessPlayer.setPlayerHealth(wildernessPlayer.getPlayerHealth() + ((Food) wAreaItems.remove(pickCurrentIndex)).getHealth());
                    //Reset the buy index back to 0
                    pickCurrentIndex = 0;
                    wUpdatePlayerUIElements(wildernessPlayer);
                    wUpdatePickUI(wAreaItems, pickCurrentIndex);
                }
                else
                {
                    wildernessPlayer.addEquipment((Equipment) wAreaItems.remove(pickCurrentIndex));
                    //Reset the buy index back to 0
                    pickCurrentIndex = 0;
                    wUpdatePlayerUIElements(wildernessPlayer);
                    wUpdatePickUI(wAreaItems, pickCurrentIndex);
                    wUpdateDropUI(wildernessPlayer, dropCurrentIndex);
                }
            }
        });

        dropActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wAreaItems.add(wildernessPlayer.equipment.get(dropCurrentIndex));
                wildernessPlayer.removeEquipment(dropCurrentIndex);
                //Reset the sell index back to 0
                dropCurrentIndex = 0;
                wUpdatePlayerUIElements(wildernessPlayer);
                wUpdatePickUI(wAreaItems, pickCurrentIndex);
                wUpdateDropUI(wildernessPlayer, dropCurrentIndex);
            }
        });



        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnData = new Intent();
                returnData.putExtra(PLAYER_ROW, wildernessPlayer.getRowLocation());
                returnData.putExtra(PLAYER_COL, wildernessPlayer.getColLocation());
                returnData.putExtra(PLAYER_HEALTH, wildernessPlayer.getPlayerHealth());
                returnData.putExtra(PLAYER_CASH, wildernessPlayer.getCash());
                returnData.putExtra(PLAYER_EQUIPMENTMASS, wildernessPlayer.getEquipmentMass());
                returnData.putExtra(PLAYER_EQUIPMENT, wildernessPlayer.getEquipment());
                returnData.putExtra(AREA_ITEMS, wAreaItems);

                setResult(RESULT_OK, returnData);
                finish();
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
//COPY AND PASTE SO THEY DIFFERENT
    public void wUpdatePickUI(ArrayList<Item> mAreaItems, int currentIndex)
    {
        //Text
        EditText buyBoxText = (EditText) findViewById(R.id.displayCurrentPickItem);
        if(mAreaItems.isEmpty())
        {
            buyBoxText.setText("No Items");
        } else {
            buyBoxText.setText(mAreaItems.get(currentIndex).getDescription());
        }
    }
    public void wUpdateDropUI(Player marketPlayer, int currentIndex)
    {
        //Text
        EditText sellBoxText = (EditText) findViewById(R.id.displayCurrentDropItem);
        if(marketPlayer.getEquipment().isEmpty())
        {
            sellBoxText.setText("No Items");
        } else {
            sellBoxText.setText(marketPlayer.getEquipment().get(currentIndex).getDescription());
        }
    }


    public void wUpdatePlayerUIElements(Player marketPlayer) {
        //Text
        EditText healthDisplay = (EditText) findViewById(R.id.wHealthDisplay);
        EditText equipmentMassDisplay = (EditText) findViewById(R.id.wEquipmentMassDisplay);
        EditText cashDisplay = (EditText) findViewById(R.id.wCashDisplay);
        healthDisplay.setText("Health: " + Double.toString(marketPlayer.getPlayerHealth()));
        cashDisplay.setText("Cash: " + Integer.toString(marketPlayer.getCash()));
        equipmentMassDisplay.setText("Mass: " + Double.toString(marketPlayer.getEquipmentMass()));
    }
}



