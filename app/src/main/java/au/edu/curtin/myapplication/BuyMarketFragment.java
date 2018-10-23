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


public class BuyMarketFragment extends Fragment {


    private RecyclerView rv;
    private BuyMarketAdaptor adapter;
    private LinearLayoutManager rvLayout;

    private OnBuyMarketViewFragmentLis mCallback;


    public interface OnBuyMarketViewFragmentLis
    {
        void marketReplaceAllFragments();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBuyMarketViewFragmentLis) {
            mCallback = (OnBuyMarketViewFragmentLis) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBuymarket view listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater li, ViewGroup parent, Bundle b) {
        // Inflate the layout for this fragment
        View view = li.inflate(R.layout.fragment_buy_market, parent, false);

        rv = (RecyclerView) view.findViewById(R.id.marketBuyRecyclerView);

        // Set up the RecyclerView
        adapter = new BuyMarketAdaptor();
        rvLayout = new LinearLayoutManager(getActivity());
        rv.setAdapter(adapter);
        rv.setLayoutManager(rvLayout);

        return view;
    }






    private class BuyMarketViewHolder extends RecyclerView.ViewHolder
    {
        private EditText buyItemName;
        private Button buyButton;




        public BuyMarketViewHolder(LayoutInflater li, ViewGroup parent)
        {
            //DOUBLE CHECK grid_cell
            super(li.inflate(R.layout.buy_market_entry, parent, false));


            buyItemName = (EditText) itemView.findViewById(R.id.buyItemName);
            buyButton = (Button) itemView.findViewById(R.id.buyButton);



            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().get(getAdapterPosition()) instanceof Food)
                    {
                        GameData.getInstance().getPlayer().setCash(GameData.getInstance().getPlayer().getCash()
                                - GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().get(getAdapterPosition()).getValue());
                        //Can have validation so that health does not exceed 100 later :)
                        GameData.getInstance().getPlayer().setPlayerHealth(GameData.getInstance().getPlayer().getPlayerHealth()
                                + ((Food) GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().remove(getAdapterPosition())).getHealth());
                        //mUpdatePlayerUIElements(marketPlayer);


                        adapter.notifyDataSetChanged();
                        mCallback.marketReplaceAllFragments();
                        validateLoseCon();
                    }
                    else
                    {
                        GameData.getInstance().getPlayer().setCash(GameData.getInstance().getPlayer().getCash() - GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().get(getAdapterPosition()).getValue());
                        GameData.getInstance().getPlayer().addEquipment((Equipment) GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().remove(getAdapterPosition()));
                        //Reset the buy index back to 0


                        adapter.notifyDataSetChanged();
                        mCallback.marketReplaceAllFragments();
                        validateWinCon();
                    }

                }
            });





        }

        public void bind(Item importedItemElement)
        {
            buyItemName.setText(importedItemElement.getDescription());
        }

    }


    public class BuyMarketAdaptor extends RecyclerView.Adapter<BuyMarketViewHolder>
    {

        @Override
        public int getItemCount()
        {
            return GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().size();
        }

        @Override
        public BuyMarketViewHolder onCreateViewHolder(ViewGroup container, int viewType)
        {
            return new BuyMarketViewHolder(LayoutInflater.from(getActivity()), container);
        }

        @Override
        public void onBindViewHolder(@NonNull BuyMarketViewHolder buyMarketViewHolder, int i) {
            buyMarketViewHolder.bind(GameData.getInstance().grid[GameData.getInstance().getPlayer().getRowLocation()][GameData.getInstance().getPlayer().getColLocation()].getItems().get(i));
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