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

public class WildernessActivity extends AppCompatActivity implements PickWildernessFragment.OnPickWildernessViewFragmentLis, UserWildernessFragment.OnUserWildernessViewFragmentLis {

    Button leaveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wilderness);



        //Buttons
        leaveButton = (Button) findViewById(R.id.leaveButton);


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
        PickWildernessFragment pickFrag = (PickWildernessFragment) fm.findFragmentById(R.id.pickWildernessFrag);
        if(pickFrag == null)
        {
            pickFrag = new PickWildernessFragment();
            fm.beginTransaction()
                    .add(R.id.pickWildernessFrag, pickFrag)
                    .commit();
        }
        UserWildernessFragment userWildernessFrag = (UserWildernessFragment) fm.findFragmentById(R.id.userInventWildernessFrag);
        if(userWildernessFrag == null)
        {
            userWildernessFrag = new UserWildernessFragment();
            fm.beginTransaction()
                    .add(R.id.userInventWildernessFrag, userWildernessFrag)
                    .commit();
        }





        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WildernessActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    public void wildernessReplaceAllFragments()
    {
        //Fragment manager
        FragmentManager fm = getSupportFragmentManager();
        StatusBarFragment statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragWilderness);
        statusFrag = new StatusBarFragment();
        fm.beginTransaction()
                    .replace(R.id.statBarFragWilderness, statusFrag)
                    .commit();

        PickWildernessFragment pickFrag = (PickWildernessFragment) fm.findFragmentById(R.id.pickWildernessFrag);
        pickFrag = new PickWildernessFragment();
        fm.beginTransaction()
                    .replace(R.id.pickWildernessFrag, pickFrag)
                    .commit();

        UserWildernessFragment userWildernessFrag = (UserWildernessFragment) fm.findFragmentById(R.id.userInventWildernessFrag);
        userWildernessFrag = new UserWildernessFragment();
        fm.beginTransaction()
                    .replace(R.id.userInventWildernessFrag, userWildernessFrag)
                    .commit();
    }
}



