package au.edu.curtin.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class AreaInfoFragment extends Fragment {

    private EditText townDisplay;
    private EditText playerDescriptionDisplay;
    private EditText starDisplay;
    private Button starButt;
    private Button confirmButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater li, ViewGroup parent, Bundle b) {
        // Inflate the layout for this fragment
        View view = li.inflate(R.layout.fragment_area_info, parent, false);


        townDisplay = (EditText) view.findViewById(R.id.townDisplayT);
        playerDescriptionDisplay = (EditText) view.findViewById(R.id.playerDescriptionDisplayT);
        starDisplay = (EditText) view.findViewById(R.id.starredDisplayT);
        starButt = (Button) view.findViewById(R.id.starredButton);
        confirmButton = (Button) view.findViewById(R.id.conButton);

        //update if 'town or wild' string
        if(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].isTown())
        {
            townDisplay.setText("Town");
        }
        else
        {
            townDisplay.setText("Wilderness");
        }

        //update if "starred" or not
        if(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].isStarred())
        {
            starDisplay.setText("STARRED");
        }
        else
        {
            starDisplay.setText("NOT STARRED");
        }

        //display what the play put into descrption
        playerDescriptionDisplay.setText(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getAreaDescription());



        starButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].isStarred())
                {
                    GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setStarred(false);
                }
                else
                {
                    GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setStarred(true);
                }
                //LATER MAKE THIS A METHOD BY ITSELF
                if(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].isStarred())
                {
                    starDisplay.setText("STARRED");
                }
                else
                {
                    starDisplay.setText("NOT STARRED");
                }
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].setAreaDescription(playerDescriptionDisplay.getText().toString());
            }
        });

        return view;
    }

}
