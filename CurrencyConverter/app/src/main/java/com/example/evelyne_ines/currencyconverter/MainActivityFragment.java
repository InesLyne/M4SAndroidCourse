package com.example.evelyne_ines.currencyconverter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private EditText input;
    private TextView output;
    private Button btnsubmit;

    public MainActivityFragment() {
    }
    public interface MainFragmentCallback{
        String onClicked();
    }
    MainFragmentCallback mainFragmentCallback;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnsubmit = (Button)view.findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);
        input = (EditText)view.findViewById(R.id.etinput);
        output = (TextView)view.findViewById(R.id.tvoutput);

    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainFragmentCallback)
            mainFragmentCallback = (MainFragmentCallback) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainFragmentCallback = null;
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnsubmit){

            if(mainFragmentCallback != null){
                String choice = mainFragmentCallback.onClicked();
                Log.e("valeur", choice);
                if(input.getText().toString().trim().equals("")){
                    input.setError("This field cannot be empty!");
                }else {
                    Log.e("info", "Not Empty");
                    conversion(choice);
                }
            }
        }
    }

    public void conversion (String choice){
        Float f = Float.parseFloat(input.getText().toString());

        if(choice.equals("Dollars")){
            Float results = new Float(f * 586.84);
            output.setText(results.toString()+"\n Francs CFA");
        }else{
            Float results = new Float(f / 586.84);
            output.setText(results.toString() + "\n Dollars");
        }
    }
}
