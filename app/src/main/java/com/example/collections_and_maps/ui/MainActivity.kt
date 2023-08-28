package com.example.collections_and_maps.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.collections_and_maps.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayoutMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        tabLayout.setBackgroundColor(Color.BLUE)
        tabLayout.setSelectedTabIndicatorColor(Color.YELLOW)

        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager2)
        val adapter = ViewPagerAdapter(this)
        viewPager2.adapter = adapter
        tabLayoutMediator = TabLayoutMediator(
            tabLayout, viewPager2
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = getString(if (position == 0) R.string.Collections else R.string.Maps)
        }
        tabLayoutMediator.attach()
    }

    public override fun onDestroy() {
        super.onDestroy()
        tabLayoutMediator.detach()
    }
}