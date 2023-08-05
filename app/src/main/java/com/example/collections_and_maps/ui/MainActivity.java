package com.example.collections_and_maps.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.collections_and_maps.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayoutMediator tabLayoutMediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setBackgroundColor(Color.BLUE);
        tabLayout.setSelectedTabIndicatorColor(Color.YELLOW);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager2);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                tab.setText(getString(position == 0 ? R.string.Collections : R.string.Maps))
        );
        tabLayoutMediator.attach();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        tabLayoutMediator.detach();
    }
}
