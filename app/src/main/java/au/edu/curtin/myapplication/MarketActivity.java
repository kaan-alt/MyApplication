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


        //Fragment manager
        FragmentManager fm = getSupportFragmentManager();
        StatusBarFragment statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragMarket);
        if(statusFrag == null)
        {
            statusFrag = new StatusBarFragment();
            fm.beginTransaction()
                    .add(R.id.statBarFragMarket, statusFrag)
                    .commit();
        }
        //mUpdatePlayerUIElements(marketPlayer);
        mUpdateSellUI(sellCurrentIndex);
        mUpdateBuyUI(buyCurrentIndex);

        buyNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().size() == buyCurrentIndex + 1)
                {
                    buyCurrentIndex = 0;
                } else {
                    buyCurrentIndex++;
                }
                mUpdateBuyUI(buyCurrentIndex);
            }
        });

        buyPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buyCurrentIndex == 0)
                {
                    buyCurrentIndex =  GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().size() - 1;
                } else {
                    buyCurrentIndex--;
                }
                mUpdateBuyUI(buyCurrentIndex);
            }
        });

        sellNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GameData.getInstance().getPlayer().getEquipment().size() == sellCurrentIndex + 1)
                {
                    sellCurrentIndex = 0;
                } else {
                    sellCurrentIndex++;
                }
                mUpdateSellUI(sellCurrentIndex);
            }
        });

        sellPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sellCurrentIndex == 0)
                {
                    sellCurrentIndex =  GameData.getInstance().getPlayer().getEquipment().size() - 1;
                } else {
                    sellCurrentIndex--;
                }
                mUpdateSellUI(sellCurrentIndex);
            }
        });

        buyActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().get(buyCurrentIndex) instanceof Food)
                {
                    GameData.getInstance().getPlayer().setCash(GameData.getInstance().getPlayer().getCash()
                            - GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().get(buyCurrentIndex).getValue());
                    //Can have validation so that health does not exceed 100 later :)
                    GameData.getInstance().getPlayer().setPlayerHealth(GameData.getInstance().getPlayer().getPlayerHealth()
                            + ((Food) GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().remove(buyCurrentIndex)).getHealth());
                    //Reset the buy index back to 0
                    buyCurrentIndex = 0;
                    //mUpdatePlayerUIElements(marketPlayer);
                    mUpdateBuyUI(buyCurrentIndex);
                    refreshStatFrag();
                }
                else
                {
                    GameData.getInstance().getPlayer().setCash(GameData.getInstance().getPlayer().getCash() - GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().get(buyCurrentIndex).getValue());
                    GameData.getInstance().getPlayer().addEquipment((Equipment) GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().remove(buyCurrentIndex));
                    //Reset the buy index back to 0
                    buyCurrentIndex = 0;
                    //mUpdatePlayerUIElements(marketPlayer);
                    mUpdateBuyUI(buyCurrentIndex);
                    mUpdateSellUI(sellCurrentIndex);
                    refreshStatFrag();
                }
            }
        });

        //went from marketplayer.equipment.get() to marketplayer.getEquipment
        sellActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameData.getInstance().getPlayer().setCash(GameData.getInstance().getPlayer().getCash() + (int)(0.75 * (double)GameData.getInstance().getPlayer().getEquipment().get(sellCurrentIndex).getValue()));
                GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().add(GameData.getInstance().getPlayer().getEquipment().get(sellCurrentIndex));
                GameData.getInstance().getPlayer().removeEquipment(sellCurrentIndex);
                //Reset the sell index back to 0
                sellCurrentIndex = 0;
                //mUpdatePlayerUIElements(marketPlayer);
                mUpdateBuyUI(buyCurrentIndex);
                mUpdateSellUI(sellCurrentIndex);
                refreshStatFrag();
            }
        });



        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarketActivity.this, MainActivity.class);
                startActivity(intent);
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

    public void mUpdateBuyUI(int currentIndex)
    {
        //Text
        EditText buyBoxText = (EditText) findViewById(R.id.displayCurrentBuyItem);
        if(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().isEmpty())
        {
            buyBoxText.setText("No Items");
        } else {
            buyBoxText.setText(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().get(currentIndex).getDescription());
        }
    }
    public void mUpdateSellUI(int currentIndex)
    {
        //Text
        EditText sellBoxText = (EditText) findViewById(R.id.displayCurrentSellItem);
        if(GameData.getInstance().getPlayer().getEquipment().isEmpty())
        {
            sellBoxText.setText("No Items");
        } else {
            sellBoxText.setText(GameData.getInstance().getPlayer().getEquipment().get(currentIndex).getDescription());
        }
    }

    public void refreshStatFrag()
    {
        FragmentManager fm = getSupportFragmentManager();
        StatusBarFragment statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragMarket);
        statusFrag = new StatusBarFragment();
        fm.beginTransaction()
                .replace(R.id.statBarFragMarket, statusFrag)
                .commit();
    }


  /*  public void mUpdatePlayerUIElements(Player marketPlayer)
    {
        statusFrag.updateStatusBar();
    }*/
}
