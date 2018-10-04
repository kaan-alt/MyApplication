package au.edu.curtin.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class StatusBarFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater li, ViewGroup parent, Bundle b) {
        View view = li.inflate(R.layout.fragment_status_bar, parent, false);

        EditText healthDisplay = (EditText) view.findViewById(R.id.healthDisplay)
        EditText equipmentMassDisplay = (EditText) view.findViewById(R.id.equipmentMassDisplay);
        EditText cashDisplay = (EditText) view.findViewById(R.id.cashDisplay);
        healthDisplay.setText("Health: " + Double.toString(thePlayer.getPlayerHealth()));
        cashDisplay.setText("Cash: " + Integer.toString(thePlayer.getCash()));
        equipmentMassDisplay.setText("Mass: " + Double.toString(thePlayer.getEquipmentMass()));

    }
}
