package com.example.uas.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.uas.Fragment.LoginTabFragrament;
import com.example.uas.Fragment.RegisterTabFragment;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public LoginAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                LoginTabFragrament loginTabFragrament = new LoginTabFragrament();
                return loginTabFragrament;
            case 1:
                RegisterTabFragment registerTabFragment = new RegisterTabFragment();
                return registerTabFragment;
            default:
                return null;
        }
    }

}
