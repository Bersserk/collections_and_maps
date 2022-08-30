package com.example.collections_and_maps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.example.collections_and_maps.adapters.PagerViewAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    TabLayoutMediator tabLayoutMediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager2);

        PagerViewAdapter adapter = new PagerViewAdapter(this);
        viewPager2.setAdapter(adapter);

        tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position)
                -> tab.setText(position == 0 ? "Collections" : "Maps"));
        tabLayoutMediator.attach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tabLayoutMediator.detach();
    }
}
