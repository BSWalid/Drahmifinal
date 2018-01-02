package com.example.willy.drahmi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Walid on 02/01/2018.
 */

public class SectionPageAdapter extends FragmentPagerAdapter {
    List <Fragment> fms = new ArrayList<>();
    List <String> ftagmenttitle= new ArrayList<>();


    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }
    public void addfragments (Fragment fragment , String title)
        {
            fms.add(fragment);
            ftagmenttitle.add(title);

        }

    @Override
    public CharSequence getPageTitle(int position) {
        return ftagmenttitle.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fms.get(position);
    }

    @Override
    public int getCount() {
        return fms.size();
    }
}
