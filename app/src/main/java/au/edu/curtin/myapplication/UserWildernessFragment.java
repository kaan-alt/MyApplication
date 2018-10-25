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


public class UserWildernessFragment extends Fragment {

    private PlayerList playerList;

    private RecyclerView rv;
    private UserWildernessAdaptor adapter;
    private LinearLayoutManager rvLayout;

    private OnUserWildernessViewFragmentLis mCallback;


    public interface OnUserWildernessViewFragmentLis
    {
        void wildernessReplaceAllFragments();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserWildernessViewFragmentLis) {
            mCallback = (OnUserWildernessViewFragmentLis) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnUserwilderness view listener");
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
        playerList.load();
    }


    @Override
    public View onCreateView(LayoutInflater li, ViewGroup parent, Bundle b) {
        // Inflate the layout for this fragment
        View view = li.inflate(R.layout.fragment_user_wilderness, parent, false);

        rv = (RecyclerView) view.findViewById(R.id.wildernessUserRecyclerView);

        // Set up the RecyclerView
        adapter = new UserWildernessAdaptor();
        rvLayout = new LinearLayoutManager(getActivity());
        rv.setAdapter(adapter);
        rv.setLayoutManager(rvLayout);

        return view;
    }






    private class UserWildernessViewHolder extends RecyclerView.ViewHolder
    {
        private EditText userItemName;
        private Button dropButton;
        private Button useButton;



        public UserWildernessViewHolder(LayoutInflater li, ViewGroup parent)
        {
            //DOUBLE CHECK grid_cell
            super(li.inflate(R.layout.user_wilderness_entry, parent, false));


            userItemName = (EditText) itemView.findViewById(R.id.wildernessUserItemName);
            dropButton = (Button) itemView.findViewById(R.id.wildernessDropButton);
            useButton = (Button) itemView.findViewById(R.id.wildernessUseButton);


            dropButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().add(GameData.getInstance().getPlayer().getEquipment().get(getAdapterPosition()));
                    GameData.getInstance().getPlayer().removeEquipment(getAdapterPosition());

                    adapter.notifyDataSetChanged();
                    mCallback.wildernessReplaceAllFragments();
                    playerList.edit(GameData.getInstance().getPlayer());
                }
            });

            useButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String usableItemName = GameData.getInstance().getPlayer().getEquipment().get(getAdapterPosition()).getDescription();
                    if (usableItemName.equals("Smell-O")) {
                        Intent intent = new Intent(getActivity(), SmellOActivity.class);
                        startActivity(intent);
                    } else if (usableItemName.equals("iDrive")) {
                        GameData.getInstance().randomiseAreaAgain();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    } else if (usableItemName.equals("Ben")) {
                        GameData.getInstance().getPlayer().addEquipmentListToExist(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems());
                        //removeAllItemsFromArea
                        GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().clear();
                        adapter.notifyDataSetChanged();
                        mCallback.wildernessReplaceAllFragments();
                        validateWinCon();
                        validateLoseCon();
                        playerList.edit(GameData.getInstance().getPlayer());
                    }
                }
            });






        }

        public void bind(Item importedItemElement)
        {
            userItemName.setText(importedItemElement.getDescription());
            if(!importedItemElement.isUsable())
            {
                useButton.setVisibility(View.GONE);
            }
        }

    }


    public class UserWildernessAdaptor extends RecyclerView.Adapter<UserWildernessViewHolder>
    {

        @Override
        public int getItemCount()
        {
            return GameData.getInstance().getPlayer().getEquipment().size();
        }

        @Override
        public UserWildernessViewHolder onCreateViewHolder(ViewGroup container, int viewType)
        {
            return new UserWildernessViewHolder(LayoutInflater.from(getActivity()), container);
        }

        @Override
        public void onBindViewHolder(@NonNull UserWildernessViewHolder userWildernessViewHolder, int i) {
            userWildernessViewHolder.bind(GameData.getInstance().getPlayer().getEquipment().get(i));
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
        if(GameData.getInstance().getPlayer().getPlayerHealth() <= 0.0)
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
