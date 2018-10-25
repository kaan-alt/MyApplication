package au.edu.curtin.myapplication;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class PickWildernessFragment extends Fragment {

    private PlayerList playerList;

    private RecyclerView rv;
    private PickWildernessAdaptor adapter;
    private LinearLayoutManager rvLayout;

    private OnPickWildernessViewFragmentLis mCallback;


    public interface OnPickWildernessViewFragmentLis
    {
        void wildernessReplaceAllFragments();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPickWildernessViewFragmentLis) {
            mCallback = (OnPickWildernessViewFragmentLis) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnPickWilderness view listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        playerList = new PlayerList(getContext());
        //TODO might be NOT NULL
        playerList.load();
    }


    @Override
    public View onCreateView(LayoutInflater li, ViewGroup parent, Bundle b) {
        // Inflate the layout for this fragment
        View view = li.inflate(R.layout.fragment_pick_wilderness, parent, false);

        rv = (RecyclerView) view.findViewById(R.id.wildernessPickRecyclerView);

        // Set up the RecyclerView
        adapter = new PickWildernessAdaptor();
        rvLayout = new LinearLayoutManager(getActivity());
        rv.setAdapter(adapter);
        rv.setLayoutManager(rvLayout);

        return view;
    }






    private class PickWildernessViewHolder extends RecyclerView.ViewHolder
    {
        private EditText pickItemName;
        private Button pickButton;




        public PickWildernessViewHolder(LayoutInflater li, ViewGroup parent)
        {
            //DOUBLE CHECK grid_cell
            super(li.inflate(R.layout.pick_wilderness_entry, parent, false));


            pickItemName = (EditText) itemView.findViewById(R.id.pickItemName);
            pickButton = (Button) itemView.findViewById(R.id.pickButton);



            pickButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().get(getAdapterPosition()) instanceof Food)
                    {
                        //Can have validation so that health does not exceed 100 later :)
                        GameData.getInstance().getPlayer().setPlayerHealth(GameData.getInstance().getPlayer().getPlayerHealth()
                                + ((Food) GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().remove(getAdapterPosition())).getHealth());

                        adapter.notifyDataSetChanged();
                        mCallback.wildernessReplaceAllFragments();
                        validateLoseCon();
                    }
                    else
                    {
                        GameData.getInstance().getPlayer().addEquipment((Equipment) GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().remove(getAdapterPosition()));

                        adapter.notifyDataSetChanged();
                        mCallback.wildernessReplaceAllFragments();
                        validateWinCon();
                    }

                    playerList.edit(GameData.getInstance().getPlayer());
                }
            });





        }

        public void bind(Item importedItemElement)
        {
            pickItemName.setText(importedItemElement.getDescription());
        }

    }


    public class PickWildernessAdaptor extends RecyclerView.Adapter<PickWildernessViewHolder>
    {

        @Override
        public int getItemCount()
        {
            return GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().size();
        }

        @Override
        public PickWildernessViewHolder onCreateViewHolder(ViewGroup container, int viewType)
        {
            return new PickWildernessViewHolder(LayoutInflater.from(getActivity()), container);
        }

        @Override
        public void onBindViewHolder(@NonNull PickWildernessViewHolder pickWildernessViewHolder, int i) {
            pickWildernessViewHolder.bind(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().get(i));
        }
    }

    public void validateWinCon()
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
        if (winCon) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }

    public void validateLoseCon()
    {
        boolean loseCon = false;
        if(GameData.getInstance().getPlayer().getPlayerHealth() == 0.0)
        {
            loseCon = true;
        }
        if(loseCon)
        {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
    }
}