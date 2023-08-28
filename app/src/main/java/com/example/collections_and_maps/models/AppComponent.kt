package com.example.collections_and_maps.models

import com.example.collections_and_maps.ui.benchmark.BenchmarkViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(factory: BenchmarkViewModelFactory?)
}
