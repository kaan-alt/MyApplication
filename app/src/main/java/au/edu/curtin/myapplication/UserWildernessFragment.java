package au.edu.curtin.myapplication;


import android.content.Context;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                }
            });

            useButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: onclick listerner for usable items
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


}
