package com.nicolas.zenos.learnenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.nicolas.zenos.learnenglish.Fragments.AnimalFragment;
import com.nicolas.zenos.learnenglish.Fragments.NumberFragment;
import com.nicolas.zenos.learnenglish.Fragments.VocalFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private SmartTabLayout smartTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smartTabLayout = findViewById(R.id.viewPagerTab);
        viewPager = findViewById(R.id.viewPager);

        //Remove a sombra do support action bar
        getSupportActionBar().setElevation(0);

        //Configurando fragment e adapter
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
            getSupportFragmentManager(),
            FragmentPagerItems.with(this)
            .add("Bichos", AnimalFragment.class)
            .add("Números", NumberFragment.class)
            .add("Vocais", VocalFragment.class)
            .create()
        );

        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);
    }
}