package au.edu.curtin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MarketActivity extends AppCompatActivity {

    private static final String PLAYER_HEALTH = "com.MainActivity.playerHealth";
    private static final String PLAYER_CASH = "com.MainActivity.playerCash";
    private static final String PLAYER_EQUIPMENTMASS = "com.MainActivity.playerEquipmentMass";
    private static final String PLAYER_EQUIPMENT = "com.MainActivity.playerEquipment";
    private static final String AREA_ITEMS = "com.MainActivity.areaItems";
    private static final String PLAYER_ROW = "com.MainActivity.rowLocation";
    private static final String PLAYER_COL = "com.MainActivity.colLocation";

    Button leaveButton;
    Button buyNextButton;
    Button buyPrevButton;
    Button sellNextButton;
    Button sellPrevButton;
    Button buyActionButton;
    Button sellActionButton;

    //VARIABLES TO TRACK WHICH ITEM INDEX OF THE ITEM LIST YOU ARE AT
    private int sellCurrentIndex = 0;
    private int buyCurrentIndex =  0;

    //THIS COULD BE ILLEGAL
    StatusBarFragment statusFrag;


    public static Intent getIntent(Context c, int rowLocation , int colLocation, int playerCash, double playerHealth, double playerEquipmentMass, ArrayList<Equipment> playerEquipment, ArrayList<Item> areaItems) {
        Intent i = new Intent(c, MarketActivity.class);
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
        setContentView(R.layout.activity_market);



        //Buttons
        leaveButton = (Button) findViewById(R.id.leaveButton);
        buyNextButton = (Button) findViewById(R.id.buyNextButton);
        buyPrevButton = (Button) findViewById(R.id.buyPrevButton);
        sellNextButton = (Button) findViewById(R.id.sellNextButton);
        sellPrevButton = (Button) findViewById(R.id.sellPrevButton);
        buyActionButton = (Button) findViewById(R.id.buyActionButton);
        sellActionButton = (Button) findViewById(R.id.sellActionButton);

        //Creating another player object from the intent
        Intent i = getIntent();
        final Player marketPlayer = new Player(i.getIntExtra(PLAYER_ROW, 0), i.getIntExtra(PLAYER_COL,0)
                , i.getIntExtra(PLAYER_CASH, 0),i.getDoubleExtra(PLAYER_HEALTH, 0)
                , i.getDoubleExtra(PLAYER_EQUIPMENTMASS, 0), (ArrayList<Equipment>) i.getSerializableExtra(PLAYER_EQUIPMENT));

        final ArrayList<Item> mAreaItems = (ArrayList<Item>) i.getSerializableExtra(AREA_ITEMS);

        //Fragment manager
        FragmentManager fm = getSupportFragmentManager();
        statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragNavigation);
        if(statusFrag == null)
        {
            statusFrag = new StatusBarFragment();
            fm.beginTransaction()
                    .add(R.id.statBarFragNavigation, statusFrag)
                    .commit();
        }
        mUpdatePlayerUIElements(marketPlayer);
        mUpdateSellUI(marketPlayer, sellCurrentIndex);
        mUpdateBuyUI(mAreaItems,  buyCurrentIndex);

        buyNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAreaItems.size() == buyCurrentIndex + 1)
                {
                    buyCurrentIndex = 0;
                } else {
                    buyCurrentIndex++;
                }
                mUpdateBuyUI(mAreaItems, buyCurrentIndex);
            }
        });

        buyPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buyCurrentIndex == 0)
                {
                    buyCurrentIndex =  mAreaItems.size() - 1;
                } else {
                    buyCurrentIndex--;
                }
                mUpdateBuyUI(mAreaItems, buyCurrentIndex);
            }
        });

        sellNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(marketPlayer.getEquipment().size() == sellCurrentIndex + 1)
                {
                    sellCurrentIndex = 0;
                } else {
                    sellCurrentIndex++;
                }
                mUpdateSellUI(marketPlayer, sellCurrentIndex);
            }
        });

        sellPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sellCurrentIndex == 0)
                {
                    sellCurrentIndex =  marketPlayer.getEquipment().size() - 1;
                } else {
                    sellCurrentIndex--;
                }
                mUpdateSellUI(marketPlayer, sellCurrentIndex);
            }
        });

        buyActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAreaItems.get(buyCurrentIndex) instanceof Food)
                {
                    marketPlayer.setCash(marketPlayer.getCash() - mAreaItems.get(buyCurrentIndex).getValue());
                    //Can have validation so that health does not exceed 100 later :)
                    marketPlayer.setPlayerHealth(marketPlayer.getPlayerHealth() + ((Food) mAreaItems.remove(buyCurrentIndex)).getHealth());
                    //Reset the buy index back to 0
                    buyCurrentIndex = 0;
                    mUpdatePlayerUIElements(marketPlayer);
                    mUpdateBuyUI(mAreaItems, buyCurrentIndex);
                }
                else
                {
                    marketPlayer.setCash(marketPlayer.getCash() - mAreaItems.get(buyCurrentIndex).getValue());
                    marketPlayer.addEquipment((Equipment) mAreaItems.remove(buyCurrentIndex));
                    //Reset the buy index back to 0
                    buyCurrentIndex = 0;
                    mUpdatePlayerUIElements(marketPlayer);
                    mUpdateBuyUI(mAreaItems, buyCurrentIndex);
                    mUpdateSellUI(marketPlayer,sellCurrentIndex);
                }
            }
        });

        //went from marketplayer.equipment.get() to marketplayer.getEquipment
        sellActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marketPlayer.setCash(marketPlayer.getCash() + (int)(0.75 * (double)marketPlayer.getEquipment().get(sellCurrentIndex).getValue()));
                mAreaItems.add(marketPlayer.getEquipment().get(sellCurrentIndex));
                marketPlayer.removeEquipment(sellCurrentIndex);
                //Reset the sell index back to 0
                sellCurrentIndex = 0;
                mUpdatePlayerUIElements(marketPlayer);
                mUpdateBuyUI(mAreaItems, buyCurrentIndex);
                mUpdateSellUI(marketPlayer, sellCurrentIndex);
            }
        });



        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnData = new Intent();
                returnData.putExtra(PLAYER_ROW, marketPlayer.getRowLocation());
                returnData.putExtra(PLAYER_COL, marketPlayer.getColLocation());
                returnData.putExtra(PLAYER_HEALTH, marketPlayer.getPlayerHealth());
                returnData.putExtra(PLAYER_CASH, marketPlayer.getCash());
                returnData.putExtra(PLAYER_EQUIPMENTMASS, marketPlayer.getEquipmentMass());
                returnData.putExtra(PLAYER_EQUIPMENT, marketPlayer.getEquipment());
                returnData.putExtra(AREA_ITEMS, mAreaItems);

                setResult(RESULT_OK, returnData);
                finish();
            }
        });



    }

    public static int getMPlayerRow(Intent intent)
    {
        return intent.getIntExtra(PLAYER_ROW,0);
    }

    public static int getMPlayerCol(Intent intent)
    {
        return intent.getIntExtra(PLAYER_COL,0);
    }

    public static double getMPlayerHealth(Intent intent)
    {
        return intent.getDoubleExtra(PLAYER_HEALTH,0);
    }

    public static int getMPlayerCash(Intent intent)
    {
        return intent.getIntExtra(PLAYER_CASH, 0);
    }

    public static double getMPlayerMass(Intent intent)
    {
        return intent.getDoubleExtra(PLAYER_EQUIPMENTMASS, 0);
    }

    public static ArrayList<Equipment> getMPlayerEquipment(Intent intent)
    {
        return (ArrayList<Equipment>)intent.getSerializableExtra(PLAYER_EQUIPMENT);
    }

    public static ArrayList<Item> getMAreaEquipment(Intent intent)
    {
        return (ArrayList<Item>)intent.getSerializableExtra(AREA_ITEMS);
    }

    public void mUpdateBuyUI(ArrayList<Item> mAreaItems, int currentIndex)
    {
        //Text
        EditText buyBoxText = (EditText) findViewById(R.id.displayCurrentBuyItem);
        if(mAreaItems.isEmpty())
        {
            buyBoxText.setText("No Items");
        } else {
            buyBoxText.setText(mAreaItems.get(currentIndex).getDescription());
        }
    }
    public void mUpdateSellUI(Player marketPlayer, int currentIndex)
    {
        //Text
        EditText sellBoxText = (EditText) findViewById(R.id.displayCurrentSellItem);
        if(marketPlayer.getEquipment().isEmpty())
        {
            sellBoxText.setText("No Items");
        } else {
            sellBoxText.setText(marketPlayer.getEquipment().get(currentIndex).getDescription());
        }
    }


    public void mUpdatePlayerUIElements(Player marketPlayer)
    {
        statusFrag.updateStatusBarPlayer(marketPlayer);
    }
}
