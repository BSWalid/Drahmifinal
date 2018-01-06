package com.example.willy.drahmi;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.icu.util.Output;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OutputInput extends AppCompatActivity {


    private ViewPager mViewPager;
    Database db;
    String idc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new Database(this);
        db.open();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output_input);
        idc = getIntent().getStringExtra("id");





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewpager);

        TabLayout tl= ( TabLayout) findViewById(R.id.tab);
        tl.setupWithViewPager(viewpager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addinput();
                updateliste2();
            }
        });


        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addoutput();
                updateliste3();


            }
        });






    }

    public void setupViewPager (ViewPager vp)
        {
             SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
            Bundle args = new Bundle();
            args.putString("idaccount",idc);

            input inpute = new input();
            OutPut outpute = new OutPut();
           inpute.setArguments(args);
            outpute.setArguments(args);
             adapter.addfragments(outpute,"Output");
             adapter.addfragments(inpute,"input");
             vp.setAdapter(adapter);



        }

    public void addinput(){
        AlertDialog.Builder myDialog = new AlertDialog.Builder(OutputInput.this);
        myDialog.setTitle("add Input");
        LinearLayout LL = new LinearLayout(OutputInput.this);
        LL.setOrientation(LinearLayout.VERTICAL);
        LL.setGravity(Gravity.CENTER);
        TextView addinput= new TextView(OutputInput.this);
        addinput.setText("Where did you get money");
        final EditText input= new EditText(OutputInput.this);
        input.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

        TextView solde= new TextView(OutputInput.this);
        solde.setText("How Much");
        final EditText soldeamount = new EditText(OutputInput.this);
        soldeamount.setInputType(InputType.TYPE_CLASS_NUMBER);

        LL.addView(addinput);
        LL.addView(input);
        LL.addView(solde);
        LL.addView(soldeamount);
        myDialog.setView(LL);

        myDialog.setPositiveButton("Confirme", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Objectif= input.getText().toString();
                String Money=soldeamount.getText().toString();
                float money2=Float.parseFloat(Money);

                String date;
                String time;

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                SimpleDateFormat df2 = new SimpleDateFormat("hh:mm:ss");
                date = df.format(c.getTime());
                time=df2.format(c.getTime());

                db.insertinput(date,time,money2,Objectif,idc);
                //refresh account solde
                String slde = db.getSoldebyid(idc);
                float solde= Float.parseFloat(slde);
                solde=solde+money2;
                db.setSolde(idc,solde);


                Toast.makeText(getApplicationContext(),"money added",Toast.LENGTH_LONG).show();

            }
        });
        myDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        myDialog.show();






    }



    public void addoutput(){
        AlertDialog.Builder myDialog = new AlertDialog.Builder(OutputInput.this);
        myDialog.setTitle("add Input");
        LinearLayout LL = new LinearLayout(OutputInput.this);
        LL.setOrientation(LinearLayout.VERTICAL);
        LL.setGravity(Gravity.CENTER);
        TextView addinput= new TextView(OutputInput.this);
        addinput.setText("you send the money to where?");
        final EditText input= new EditText(OutputInput.this);
        input.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

        TextView solde= new TextView(OutputInput.this);
        solde.setText("How Much");
        final EditText soldeamount = new EditText(OutputInput.this);
        soldeamount.setInputType(InputType.TYPE_CLASS_NUMBER);

        LL.addView(addinput);
        LL.addView(input);
        LL.addView(solde);
        LL.addView(soldeamount);
        myDialog.setView(LL);

        myDialog.setPositiveButton("Confirme", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Objectif= input.getText().toString();
                String Money=soldeamount.getText().toString();
                float money2=Float.parseFloat(Money);

                String date;
                String time;

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                SimpleDateFormat df2 = new SimpleDateFormat("hh:mm:ss");
                date = df.format(c.getTime());
                time=df2.format(c.getTime());

                db.insertoutput(date,time,money2,Objectif,idc);
                //refresh account solde
                String slde = db.getSoldebyid(idc);
                float solde= Float.parseFloat(slde);
                solde=solde-money2;
                db.setSolde(idc,solde);


                Toast.makeText(getApplicationContext(),"money added",Toast.LENGTH_LONG).show();

            }
        });
        myDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        myDialog.show();






    }
    public void updateliste2(){

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
        inputItemAdapter adapter = new inputItemAdapter(items,getApplicationContext());
        RecyclerView rv = ( RecyclerView) findViewById(R.id.inputlist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);



    }


    public void updateliste3(){

        Cursor c = db.GetAlloutputsByID(idc);
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
        outPutItemAdapter adapter = new outPutItemAdapter(items,getApplicationContext());
        RecyclerView rv = ( RecyclerView) findViewById(R.id.outputlist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);



    }




}







