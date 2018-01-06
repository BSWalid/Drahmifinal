package com.example.willy.drahmi;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class input extends Fragment {



    Database db;

    Bundle b = new Bundle();


    String idc;

    public input( ) {
        // Required empty public





    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_input2, container, false);
                    db = new Database(getActivity().getApplicationContext());
                     db.open();
         b=getArguments();
       this.idc=b.getString("idaccount");
        Log.i("testof id acc", ""+idc);
        updateliste2(v);





        return v;
    }

    @Override
    public void onStart() {
        super.onStart();



    }

    public void updateliste2(View v){

        Cursor c = db.GetAllinputsByID(idc);
        List<inputitem> items =  new ArrayList<inputitem>();


        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            inputitem item = new inputitem();
            item.setId(c.getString(0));
            item.setDate(c.getString(1));
            item.setTime(c.getString(2));
            item.setAmmount(c.getFloat(3));
            item.setObjectif(c.getString(4));
            items.add(item);

        }
        inputItemAdapter adapter = new inputItemAdapter(items,getActivity().getApplicationContext());
        RecyclerView rv = ( RecyclerView) v.findViewById(R.id.inputlist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);



    }




}
