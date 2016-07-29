package com.example.evelyne_ines.firebasestudent;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {
    private Button btnsubmit;
    private EditText etID,etname;
    private DatabaseReference rootRef;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        rootRef = FirebaseDatabase.getInstance().getReference();
        etname =(EditText) rootView.findViewById(R.id.etname);
        etID = (EditText)rootView.findViewById(R.id.etID);
        btnsubmit = (Button)rootView.findViewById(R.id.btnsubmit);
        btnsubmit.setOnClickListener(this);


        return rootView;
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnsubmit){
            String id= etID.getText().toString().trim();
            String firstname = etname.getText().toString().trim();
            Student student = new Student(id, firstname);
            rootRef.push().setValue(student);
            Toast.makeText(getContext(), "Enregistrement réussi", Toast.LENGTH_SHORT).show();
        }
    }
}

