package com.asa.swipetab;
/*
// Jika tanpa support library
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;

// Jika menggunakan support library
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
*/
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


// FragmentActivity tanpa support library
// ActionBarActivity dengan support library
public class MainActivity extends ActionBarActivity {
    MyPagerAdapter mPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);

        /*
        ActionBar actionBar = getActionBar();   // Jika class FragmentActivity / tanpa support library
        ActionBar actionBar = getSupportActionBar();  // Jika class ActionBarActivity / dengan support library
        */
        ActionBar actionBar = getSupportActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        };


        for (int i = 0; i < 3; i++){
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("Tab "+ (i+1))
                            .setTabListener(tabListener));
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //getSupportActionBar().hide();  // Menyembunyikan ActionBar

    }

    public static class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment mFragment = new MyObjectFragment();
            Bundle args = new Bundle();
            args.putInt(MyObjectFragment.KEY, i + 1);
            mFragment.setArguments(args);
            return mFragment;

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "OBJECT " + (position + 1);
        }

    }

    public static class MyObjectFragment extends Fragment {
        public static String KEY = "ASA";

        @Override
        public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup,Bundle bundle){
            View rootView = inflater.inflate(R.layout.layout_fragment, viewGroup, false);
            Bundle args = getArguments();
            ((TextView)rootView.findViewById(R.id.text_asa)).setText(Integer.toString(args.getInt(MyObjectFragment.KEY)));
            return rootView;
        }
    }
}
