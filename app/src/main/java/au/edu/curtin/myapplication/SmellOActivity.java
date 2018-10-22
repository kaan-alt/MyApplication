package au.edu.curtin.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class SmellOActivity extends AppCompatActivity {

    Button leaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smell_o);

        //Buttons
        leaveButton = (Button) findViewById(R.id.leaveButton);


        //Fragment manager
        FragmentManager fm = getSupportFragmentManager();
        SmellOListFragment listFrag = (SmellOListFragment) fm.findFragmentById(R.id.listViewFrag);
        if (listFrag == null) {
            listFrag = new SmellOListFragment();
            fm.beginTransaction()
                    .add(R.id.listViewFrag, listFrag)
                    .commit();

        }

        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SmellOActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
