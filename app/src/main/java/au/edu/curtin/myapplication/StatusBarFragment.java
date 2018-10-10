package au.edu.curtin.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.Fragment;


import java.util.ArrayList;

public class StatusBarFragment extends Fragment {

    private EditText healthDisplay;
    private EditText equipmentMassDisplay;
    private EditText cashDisplay;
    private Button restartButt;

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
        restartButt = (Button) view.findViewById(R.id.restartButton);

        String health = ("Health: " + Double.toString(GameData.getInstance().getPlayer().getPlayerHealth()));
        String cash = ("Cash: " + Integer.toString(GameData.getInstance().getPlayer().getCash()));
        String mass = ("Mass: " + Double.toString(GameData.getInstance().getPlayer().getEquipmentMass()));
        healthDisplay.setText(health);
        cashDisplay.setText(cash);
        equipmentMassDisplay.setText(mass);

        restartButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //TODO: figure out how to send back to MainActivity
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


}
