package au.edu.curtin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OverviewActivity extends AppCompatActivity {



    Button leaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        //Buttons
        leaveButton = (Button) findViewById(R.id.leaveButton);


        FragmentManager fm = getSupportFragmentManager();
        StatusBarFragment statusFrag = (StatusBarFragment) fm.findFragmentById(R.id.statBarFragOverview);
        AreaInfoFragment infoFrag = (AreaInfoFragment) fm.findFragmentById(R.id.areaInfoFragOverview);
        GraphicalViewFragment graphicFrag = (GraphicalViewFragment) fm.findFragmentById(R.id.graphicViewFrag);
        if(statusFrag == null)
        {
            statusFrag = new StatusBarFragment();
            fm.beginTransaction()
                    .add(R.id.statBarFragOverview, statusFrag)
                    .commit();
        }
        if(infoFrag == null)
        {
            infoFrag = new AreaInfoFragment();
            fm.beginTransaction()
                    .add(R.id.areaInfoFragOverview, infoFrag)
                    .commit();
        }
        if(graphicFrag == null)
        {
            graphicFrag= new GraphicalViewFragment();
            fm.beginTransaction()
                    .add(R.id.graphicViewFrag, graphicFrag)
                    .commit();
        }


        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OverviewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
