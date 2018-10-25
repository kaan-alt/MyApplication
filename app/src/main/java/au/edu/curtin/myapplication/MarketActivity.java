package au.edu.curtin.myapplication;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MarketActivity extends AppCompatActivity implements BuyMarketFragment.OnBuyMarketViewFragmentLis, UserMarketFragment.OnUserMarketViewFragmentLis{

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
        UserMarketFragment userMarketFrag = (UserMarketFragment) fm.findFragmentById(R.id.userInventMarketFrag);
        if(userMarketFrag == null)
        {
            userMarketFrag = new UserMarketFragment();
            fm.beginTransaction()
                    .add(R.id.userInventMarketFrag, userMarketFrag)
                    .commit();
        }



        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MarketActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



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

        UserMarketFragment userMarketFrag = (UserMarketFragment) fm.findFragmentById(R.id.userInventMarketFrag);
        userMarketFrag = new UserMarketFragment();
        fm.beginTransaction()
                    .add(R.id.userInventMarketFrag, userMarketFrag)
                    .commit();

    }
}
