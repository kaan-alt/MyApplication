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

public class MarketActivity extends AppCompatActivity implements BuyMarketFragment.OnBuyMarketViewFragmentLis{

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
        setContentView(R.layout.activity_market);



        //Buttons
        leaveButton = (Button) findViewById(R.id.leaveButton);


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
        BuyMarketFragment buyFrag = (BuyMarketFragment) fm.findFragmentById(R.id.buyMarketFrag);
        if(buyFrag == null)
        {
            buyFrag = new BuyMarketFragment();
            fm.beginTransaction()
                    .add(R.id.buyMarketFrag, buyFrag)
                    .commit();
        }



        //went from marketplayer.equipment.get() to marketplayer.getEquipment
        /*sellActionButton.setOnClickListener(new View.OnClickListener() {
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
        });*/



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


    public void  marketReplaceAllFragments()
    {
        FragmentManager fm = getSupportFragmentManager();
        StatusBarFragment statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragMarket);
        statusFrag = new StatusBarFragment();
        fm.beginTransaction()
                    .replace(R.id.statBarFragMarket, statusFrag)
                    .commit();

        BuyMarketFragment buyFrag = (BuyMarketFragment) fm.findFragmentById(R.id.buyMarketFrag);
        buyFrag = new BuyMarketFragment();
        fm.beginTransaction()
                    .replace(R.id.buyMarketFrag, buyFrag)
                    .commit();

    }
    /*public void mUpdateBuyUI(int currentIndex)
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
    }*/


  /*  public void mUpdatePlayerUIElements(Player marketPlayer)
    {
        statusFrag.updateStatusBar();
    }*/
}
