package com.example.ashwani.customotp;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.msg91.sendotp.library.SendOtpVerification;
import com.msg91.sendotp.library.Verification;
import com.msg91.sendotp.library.VerificationListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tabs_layout);
        tabLayout.setupWithViewPager(viewPager);


    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ContactListFragment(), "Contacts");
        adapter.addFragment(new SentOtpListFragment(), "OTP history");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> fragmentList=new ArrayList<>();
        List<String> fragmentTitleList=new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }

}
