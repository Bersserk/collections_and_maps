package com.example.collections_and_maps.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.collections_and_maps.R
import com.example.collections_and_maps.ui.benchmark.BenchmarkFragment.Companion.newInstance

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> newInstance(R.string.Collections)
            1 -> newInstance(R.string.Maps)
            else -> throw RuntimeException("Unsupported type")
        }
    }

    override fun getItemCount(): Int = 2
}
