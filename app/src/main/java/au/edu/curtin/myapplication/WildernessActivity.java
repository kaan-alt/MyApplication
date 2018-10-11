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

public class WildernessActivity extends AppCompatActivity {

    private static final String PLAYER_HEALTH = "com.MainActivity.playerHealth";
    private static final String PLAYER_CASH = "com.MainActivity.playerCash";
    private static final String PLAYER_EQUIPMENTMASS = "com.MainActivity.playerEquipmentMass";
    private static final String PLAYER_EQUIPMENT = "com.MainActivity.playerEquipment";
    private static final String AREA_ITEMS = "com.MainActivity.areaItems";
    private static final String PLAYER_ROW = "com.MainActivity.rowLocation";
    private static final String PLAYER_COL = "com.MainActivity.colLocation";

    Button leaveButton;
    Button pickNextButton;
    Button pickPrevButton;
    Button dropNextButton;
    Button dropPrevButton;
    Button pickActionButton;
    Button dropActionButton;



    //VARIABLES TO TRACK WHICH ITEM INDEX OF THE ITEM LIST YOU ARE AT
    private int dropCurrentIndex = 0;
    private int pickCurrentIndex =  0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilderness);



        //Buttons
        leaveButton = (Button) findViewById(R.id.leaveButton);
        pickNextButton = (Button) findViewById(R.id.pickNextButton);
        pickPrevButton = (Button) findViewById(R.id.pickPrevButton);
        dropNextButton = (Button) findViewById(R.id.dropNextButton);
        dropPrevButton = (Button) findViewById(R.id.dropPrevButton);
        pickActionButton = (Button) findViewById(R.id.pickActionButton);
        dropActionButton = (Button) findViewById(R.id.dropActionButton);

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

        //wUpdatePlayerUIElements(wildernessPlayer);
        wUpdateDropUI(dropCurrentIndex);
        wUpdatePickUI(pickCurrentIndex);

        pickNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().size() == pickCurrentIndex + 1)
                {
                    pickCurrentIndex = 0;
                } else {
                    pickCurrentIndex++;
                }
                wUpdatePickUI(pickCurrentIndex);
            }
        });

        pickPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pickCurrentIndex == 0)
                {
                    pickCurrentIndex =  GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().size() - 1;
                } else {
                    pickCurrentIndex--;
                }
                wUpdatePickUI(pickCurrentIndex);
            }
        });

        dropNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GameData.getInstance().getPlayer().getEquipment().size() == dropCurrentIndex + 1)
                {
                    dropCurrentIndex = 0;
                } else {
                    dropCurrentIndex++;
                }
                wUpdateDropUI(dropCurrentIndex);
            }
        });

        dropPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dropCurrentIndex == 0)
                {
                    dropCurrentIndex =  GameData.getInstance().getPlayer().getEquipment().size() - 1;
                } else {
                    dropCurrentIndex--;
                }
                wUpdateDropUI(dropCurrentIndex);
            }
        });

        pickActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().get(pickCurrentIndex) instanceof Food)
                {
                    //Can have validation so that health does not exceed 100 later :)
                    GameData.getInstance().getPlayer().setPlayerHealth(GameData.getInstance().getPlayer().getPlayerHealth()
                            + ((Food) GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().remove(pickCurrentIndex)).getHealth());
                    //Reset the buy index back to 0
                    pickCurrentIndex = 0;
                    //wUpdatePlayerUIElements(wildernessPlayer);
                    wUpdatePickUI(pickCurrentIndex);
                }
                else
                {
                    GameData.getInstance().getPlayer().addEquipment((Equipment) GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().remove(pickCurrentIndex));
                    //Reset the buy index back to 0
                    pickCurrentIndex = 0;
                    //wUpdatePlayerUIElements(wildernessPlayer);
                    wUpdatePickUI(pickCurrentIndex);
                    wUpdateDropUI(dropCurrentIndex);
                }
            }
        });

        dropActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().add(GameData.getInstance().getPlayer().getEquipment().get(dropCurrentIndex));
                GameData.getInstance().getPlayer().removeEquipment(dropCurrentIndex);
                //Reset the sell index back to 0
                dropCurrentIndex = 0;
                //wUpdatePlayerUIElements(wildernessPlayer);
                wUpdatePickUI(pickCurrentIndex);
                wUpdateDropUI(dropCurrentIndex);
            }
        });



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
//COPY AND PASTE SO THEY DIFFERENT
    public void wUpdatePickUI(int currentIndex)
    {
        //Text
        EditText buyBoxText = (EditText) findViewById(R.id.displayCurrentPickItem);
        if(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().isEmpty())
        {
            buyBoxText.setText("No Items");
        } else {
            buyBoxText.setText(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().get(currentIndex).getDescription());
        }
    }
    public void wUpdateDropUI(int currentIndex)
    {
        //Text
        EditText sellBoxText = (EditText) findViewById(R.id.displayCurrentDropItem);
        if(GameData.getInstance().getPlayer().getEquipment().isEmpty())
        {
            sellBoxText.setText("No Items");
        } else {
            sellBoxText.setText(GameData.getInstance().getPlayer().getEquipment().get(currentIndex).getDescription());
        }
    }


    /*public void wUpdatePlayerUIElements(Player wildernessPlayer)
    {
        statusFrag.updateStatusBar();
    }*/
}



