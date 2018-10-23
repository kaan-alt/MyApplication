package au.edu.curtin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.Fragment;
import android.widget.TextView;


import java.util.ArrayList;

public class StatusBarFragment extends Fragment {

    private EditText healthDisplay;
    private EditText equipmentMassDisplay;
    private EditText cashDisplay;
    private Button restartButt;
    private TextView winLoseTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater li, ViewGroup parent, Bundle b) {
        View view = li.inflate(R.layout.fragment_status_bar, parent, false);

        healthDisplay = (EditText) view.findViewById(R.id.healthDisplayT);
        equipmentMassDisplay = (EditText) view.findViewById(R.id.equipmentMassDisplayT);
        cashDisplay = (EditText) view.findViewById(R.id.cashDisplayT);
        winLoseTextView = (TextView) view.findViewById(R.id.winLoseTextView);
        restartButt = (Button) view.findViewById(R.id.restartButton);

        if(validateWinCon())
        {
            healthDisplay.setVisibility(View.GONE);
            cashDisplay.setVisibility(View.GONE);
            equipmentMassDisplay.setVisibility(View.GONE);
            winLoseTextView.setText("WINNER!");
        }
        else if(validateLoseCon())
        {
            healthDisplay.setVisibility(View.GONE);
            cashDisplay.setVisibility(View.GONE);
            equipmentMassDisplay.setVisibility(View.GONE);
            winLoseTextView.setText("LOSER!");
        }
        else
        {
            String health = ("Health: " + Double.toString(GameData.getInstance().getPlayer().getPlayerHealth()));
            String cash = ("Cash: " + Integer.toString(GameData.getInstance().getPlayer().getCash()));
            String mass = ("Mass: " + Double.toString(GameData.getInstance().getPlayer().getEquipmentMass()));
            healthDisplay.setText(health);
            cashDisplay.setText(cash);
            equipmentMassDisplay.setText(mass);
            winLoseTextView.setVisibility(View.GONE);
        }


        restartButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameData.getInstance().reset();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

   /* public void updateStatusBar()
    {
        String health1 = "Health:";
        String health = ("Health: " + Double.toString(GameData.getInstance().getPlayer().getPlayerHealth()));
        String cash = ("Cash: " + Integer.toString(GameData.getInstance().getPlayer().getCash()));
        String mass = ("Mass: " + Double.toString(GameData.getInstance().getPlayer().getEquipmentMass()));
        healthDisplay.setText(health);
        cashDisplay.setText(cash);
        equipmentMassDisplay.setText(mass);
    }*/

   public boolean validateWinCon()
   {
       boolean con1 = false, con2 = false, con3 = false, winCon = false;
       for(Equipment e : GameData.getInstance().getPlayer().getEquipment())
       {
           if(e.getDescription().equals("jade monkey"))
           {
                con1 = true;
           }
           if(e.getDescription().equals("the roadmap"))
           {
               con2 = true;
           }
           if(e.getDescription().equals("the ice scraper"))
           {
               con3 = true;
           }
       }
       if(con1 && con2 && con3) {
           winCon = true;
       }
       return winCon;
   }

   public boolean validateLoseCon()
   {
       boolean loseCon = false;
       if(GameData.getInstance().getPlayer().getPlayerHealth() == 0.0)
       {
           loseCon = true;
       }
       return loseCon;
   }


}
