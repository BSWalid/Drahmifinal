package com.example.willy.drahmi;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Accounts extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Database db;
    String idofuser;
    TextView account;
    String accountnamestring="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        db = new Database(this);
        db.open();
        account = (TextView) findViewById(R.id.accountname);

        idofuser = getIntent().getStringExtra("idofuser");
        String staut= db.getstatubyid(idofuser);
        Log.i("Test","Access  " + staut);

        // controll access
        if( staut.equals("false") || staut.equals("null")){
            Log.i("Test","Access");
            Intent i = new Intent(getApplicationContext(), TheLogin.class);
            startActivity(i);
            finish();
        }
        updateliste();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // we will do addaccount
                addaccount();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        Log.i("Test","test2 : "+idofuser);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.accounts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.logout)
        {
            db.updateconnexion(idofuser,"false");

            Intent i = new Intent(getApplicationContext(), TheLogin.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addaccount(){
        AlertDialog.Builder myDialog = new AlertDialog.Builder(Accounts.this);
        myDialog.setTitle("add account");
        LinearLayout LL = new LinearLayout(Accounts.this);
        LL.setOrientation(LinearLayout.VERTICAL);
        LL.setGravity(Gravity.CENTER);
        TextView addaccount= new TextView(Accounts.this);
        addaccount.setText("please fill in the account");
        final EditText accountname= new EditText(Accounts.this);
        accountname.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);

        LL.addView(addaccount);
        LL.addView(accountname);
        myDialog.setView(LL);
        myDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String Accountnom= accountname.getText().toString();
                db.insertAccount(Accountnom,idofuser);
                Cursor c = db.GetAllAccountByID(idofuser);

                updateliste();

                Toast.makeText(getApplicationContext(),"the account has been add",Toast.LENGTH_LONG).show();

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
    public void updateliste(){

        Cursor c = db.GetAllAccountByID(idofuser);
        List<item> items =  new ArrayList<item>();


        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            item item = new item();
            item.setId(c.getString(0));
            item.setName(c.getString(1));
            item.setSolde(c.getString(2)+" $");
            items.add(item);

        }
        ItemAdapter adapter = new ItemAdapter(items,getApplicationContext());
        RecyclerView rv = ( RecyclerView) findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);



    }
}
