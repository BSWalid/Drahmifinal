package com.example.willy.drahmi;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TableLayout;
import android.widget.TextView;

public class OutputInput extends AppCompatActivity {


    private ViewPager mViewPager;
    String idc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void setupViewPager (ViewPager vp)
        {
             SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
             adapter.addfragments(new OutPut(),"AccountTransactions");
             adapter.addfragments(new OutPut(),"Output");
             adapter.addfragments(new OutPut(),"inpu");
            vp.setAdapter(adapter);

        }


}




