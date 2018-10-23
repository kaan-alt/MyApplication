package au.edu.curtin.myapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class SmellOListFragment extends Fragment {


    private RecyclerView rv;
    private SmellOListAdaptor adapter;
    private LinearLayoutManager rvLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater li, ViewGroup parent, Bundle b) {
        // Inflate the layout for this fragment
        View view = li.inflate(R.layout.fragment_smell_o_list, parent, false);

        rv = (RecyclerView) view.findViewById(R.id.listRecyclerView);

        // Set up the RecyclerView
        adapter = new SmellOListAdaptor();
        rvLayout = new LinearLayoutManager(getActivity());
        rv.setAdapter(adapter);
        rv.setLayoutManager(rvLayout);

        return view;
    }






    private class SmellOListViewHolder extends RecyclerView.ViewHolder
    {
        private TextView itemName;
        private TextView leftRight;
        private TextView upDown;




        public SmellOListViewHolder(LayoutInflater li, ViewGroup parent)
        {
            //DOUBLE CHECK grid_cell
            super(li.inflate(R.layout.smell_o_list_entry, parent, false));


            itemName = (TextView) itemView.findViewById(R.id.smellOListItemName);
            leftRight = (TextView) itemView.findViewById(R.id.smellOLeftRight);
            upDown = (TextView) itemView.findViewById(R.id.smellOUpDown);





        }

        public void bind(Item importedItemElement)
        {
            itemName.setText(importedItemElement.getDescription());
            int colDifference = GameData.getInstance().getPlayer().getColLocation() - importedItemElement.getItemColLocation();
            if(colDifference == 0)
            {
                leftRight.setText("0");
            }
            else if(colDifference < 0)
            {
                leftRight.setText(Integer.toString(Math.abs(colDifference)) + " east");
            }
            else if(colDifference > 0)
            {
                leftRight.setText(Integer.toString(Math.abs(colDifference)) + " west");
            }
            int rowDifference = GameData.getInstance().getPlayer().getRowLocation() - importedItemElement.getItemRowLocation();
            if(rowDifference == 0)
            {
                upDown.setText("0");
            }
            else if(rowDifference < 0)
            {
                upDown.setText(Integer.toString(Math.abs(rowDifference)) + " south");
            }
            else if(rowDifference > 0)
            {
                upDown.setText(Integer.toString(Math.abs(rowDifference)) + " north");
            }
        }

    }


    public class SmellOListAdaptor extends RecyclerView.Adapter<SmellOListViewHolder>
    {

        ArrayList<Item> bigListOfItemsOfSurrondingAreas = makeGaintListOfSurrondingAreas();
        @Override
        public int getItemCount()
        {
            return bigListOfItemsOfSurrondingAreas.size();
        }

        @Override
        public SmellOListViewHolder onCreateViewHolder(ViewGroup container, int viewType)
        {
            return new SmellOListViewHolder(LayoutInflater.from(getActivity()), container);
        }

        @Override
        public void onBindViewHolder(@NonNull SmellOListViewHolder smellOListViewHolder, int i) {
            smellOListViewHolder.bind(bigListOfItemsOfSurrondingAreas.get(i));
        }
    }

    //Get a big list of surronding areas making sure I dont .getItems from a non valid grid sqaure i.e. -1,-1
    public ArrayList<Item> makeGaintListOfSurrondingAreas()
    {
        ArrayList<Item> bigList = new ArrayList<Item>();
        int playerRow = GameData.getInstance().getPlayer().getRowLocation();
        int playerCol = GameData.getInstance().getPlayer().getColLocation();
        for(int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                //Validation it within the map size
                if(playerRow + i > 0 && playerRow + i < GameData.HEIGHT)
                {
                    if(playerCol + j > 0 && playerCol + j < GameData.WIDTH) {
                        bigList.addAll(GameData.getInstance().grid[playerRow + i][playerCol + j].getItems());
                    }
                }
            }
        }
        return bigList;
    }

}
