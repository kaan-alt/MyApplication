package au.edu.curtin.myapplication;


import android.content.Context;
import android.content.Intent;
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


public class UserMarketFragment extends Fragment {

    private PlayerList playerList;

    private RecyclerView rv;
    private UserMarketAdaptor adapter;
    private LinearLayoutManager rvLayout;

    private OnUserMarketViewFragmentLis mCallback;


    public interface OnUserMarketViewFragmentLis
    {
        void marketReplaceAllFragments();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserMarketViewFragmentLis) {
            mCallback = (OnUserMarketViewFragmentLis) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnUsermarket view listener");
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
        View view = li.inflate(R.layout.fragment_buy_market, parent, false);

        rv = (RecyclerView) view.findViewById(R.id.marketBuyRecyclerView);

        // Set up the RecyclerView
        adapter = new UserMarketAdaptor();
        rvLayout = new LinearLayoutManager(getActivity());
        rv.setAdapter(adapter);
        rv.setLayoutManager(rvLayout);

        return view;
    }






    private class UserMarketViewHolder extends RecyclerView.ViewHolder
    {
        private EditText userItemName;
        private Button sellButton;
        private Button useButton;



        public UserMarketViewHolder(LayoutInflater li, ViewGroup parent)
        {
            //DOUBLE CHECK grid_cell
            super(li.inflate(R.layout.user_market_entry, parent, false));


            userItemName = (EditText) itemView.findViewById(R.id.marketUserItemName);
            sellButton = (Button) itemView.findViewById(R.id.marketSellButton);
            useButton = (Button) itemView.findViewById(R.id.marketUseButton);


            sellButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GameData.getInstance().getPlayer().setCash(GameData.getInstance().getPlayer().getCash() + (int)(0.75 * (double)GameData.getInstance().getPlayer().getEquipment().get(getAdapterPosition()).getValue()));
                    GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().add(GameData.getInstance().getPlayer().getEquipment().get(getAdapterPosition()));
                    GameData.getInstance().getPlayer().removeEquipment(getAdapterPosition());
                    adapter.notifyDataSetChanged();
                    mCallback.marketReplaceAllFragments();
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
                        mCallback.marketReplaceAllFragments();
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


    public class UserMarketAdaptor extends RecyclerView.Adapter<UserMarketFragment.UserMarketViewHolder>
    {

        @Override
        public int getItemCount()
        {
            return GameData.getInstance().getPlayer().getEquipment().size();
        }

        @Override
        public UserMarketFragment.UserMarketViewHolder onCreateViewHolder(ViewGroup container, int viewType)
        {
            return new UserMarketFragment.UserMarketViewHolder(LayoutInflater.from(getActivity()), container);
        }

        @Override
        public void onBindViewHolder(@NonNull UserMarketFragment.UserMarketViewHolder userMarketViewHolder, int i) {
            userMarketViewHolder.bind(GameData.getInstance().getPlayer().getEquipment().get(i));
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
