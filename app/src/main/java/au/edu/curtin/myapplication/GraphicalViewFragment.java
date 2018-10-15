package au.edu.curtin.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class GraphicalViewFragment extends Fragment
{

    private RecyclerView rv;
    private MapAdapter adapter;
    private GridLayoutManager rvLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater li, ViewGroup parent, Bundle b) {
        // Inflate the layout for this fragment
        View view = li.inflate(R.layout.fragment_graphical_view, parent, false);

        rv = (RecyclerView) view.findViewById(R.id.mapRecyclerView);

        // Set up the RecyclerView
        adapter = new MapAdapter();
        rvLayout = new GridLayoutManager(getActivity(), GameData.getInstance().HEIGHT, GridLayoutManager.HORIZONTAL, false);
        rv.setAdapter(adapter);
        rv.setLayoutManager(rvLayout);

        return view;
    }






    private class MapViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView gridImage;


        public MapViewHolder(LayoutInflater li, ViewGroup parent)
        {
            //DOUBLE CHECK grid_cell
            super(li.inflate(R.layout.grid_cell, parent, false));


            gridImage = (ImageView) itemView.findViewById(R.id.gridImage);


            int size = parent.getMeasuredHeight() / GameData.getInstance().HEIGHT + 1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;





        }

        public void bind(Area importedAreaElemenet)
        {
            if(importedAreaElemenet.isExplored())
            {
                if (importedAreaElemenet.isStarred())
                {
                    gridImage.setImageResource(R.drawable.ic_tree1);
                }
                else
                {
                    if(importedAreaElemenet.isTown())
                    {
                        gridImage.setImageResource(R.drawable.ic_building4);
                    }
                    else
                    {
                        gridImage.setImageResource(R.drawable.ic_grass3);
                    }
                }
            }
            else
            {
                gridImage.setImageResource(R.drawable.ic_water);
            }
        }

    }


    public class MapAdapter extends RecyclerView.Adapter<MapViewHolder>
    {

        @Override
        public int getItemCount()
        {
            return GameData.getInstance().HEIGHT * GameData.getInstance().WIDTH;
        }

        @Override
        public MapViewHolder onCreateViewHolder(ViewGroup container, int viewType)
        {
            return new MapViewHolder(LayoutInflater.from(getActivity()), container);
        }

        @Override
        public void onBindViewHolder(@NonNull MapViewHolder mapViewHolder, int i) {
            int row = i % GameData.getInstance().HEIGHT;
            int col = i / GameData.getInstance().HEIGHT;
            //This returns a Area object that is then binded
            mapViewHolder.bind(GameData.getInstance().get(row, col));
        }
    }

}
