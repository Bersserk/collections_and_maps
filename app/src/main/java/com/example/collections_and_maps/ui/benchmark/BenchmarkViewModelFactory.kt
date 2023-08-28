package com.example.collections_and_maps.ui.benchmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.collections_and_maps.App
import com.example.collections_and_maps.R
import com.example.collections_and_maps.models.benchmarks.Benchmark
import javax.inject.Inject
import javax.inject.Named

class BenchmarkViewModelFactory(private val benchmarkType: Int) : ViewModelProvider.Factory {

    @Inject
    @Named("collection")
    lateinit var collectionsBenchmark: Benchmark

    @Inject
    @Named("maps")
    lateinit var mapsBenchmark: Benchmark

    init {
        App.getAppComponent()?.inject(this)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass == BenchmarkViewModel::class.java) {
            (benchmarkType == R.string.Collections) -> BenchmarkViewModel(collectionsBenchmark) as T
            (benchmarkType == R.string.Maps) -> BenchmarkViewModel(mapsBenchmark) as T
            else -> throw IllegalArgumentException("Unsupported ViewModel class: " + modelClass.name)
        }
    }
}
