package com.example.evelyne_ines.stateslistproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AdapterView.OnItemClickListener {

    String[] states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado",
            "Connecticut", "Delaware", "Florida", "Georgia", "Hawaï", "Idaho", "Illinois", "Indiana", "Iowa",
            "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississipi",
            "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
            "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee",
            "Texas", "Utah", "Vermont", "Virginia", "Washington", "West virginia", "Wisconsin", "Wyoming"};

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        List<String> StatesAL = new ArrayList<String>(Arrays.asList(states));
        ArrayAdapter<String> StatesAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                states);
        ListView listView = (ListView)rootView.findViewById(R.id.listViewlayout);
        listView.setOnItemClickListener(this);
        listView.setAdapter(StatesAdapter);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(i == 19){
            Intent intent = new Intent(getContext(), MapsActivity.class);
            intent.putExtra("Lat", "38.979054");
            intent.putExtra("Lng", "-76.492236");
            intent.putExtra("capital", "Annapolis");
            intent.putExtra("Name", states[i]);
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(getActivity(), "click on MaryLand to display the map", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
