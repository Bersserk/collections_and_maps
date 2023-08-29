package com.example.collections_and_maps.ui.benchmark

import androidx.core.util.Pair
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.collections_and_maps.R
import com.example.collections_and_maps.models.benchmarks.Benchmark
import com.example.collections_and_maps.models.benchmarks.ResultItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BenchmarkViewModel(private val benchmark: Benchmark) : ViewModel() {

    private val itemsLiveData = MutableLiveData<List<ResultItem>>()
    private val liveTextTV = MutableLiveData<Int>()
    private val liveShowerMessages = MutableLiveData<Int?>()
    private var disposable = Disposable.disposed()

    fun getItemsLiveData(): LiveData<List<ResultItem>> = itemsLiveData

    fun getLiveTextTV(): LiveData<Int> = liveTextTV

    fun getLiveShowerMessages(): LiveData<Int?> = liveShowerMessages

    fun onCreate() {
        itemsLiveData.value = benchmark.getItemsList(false)
    }

    fun startMeasure(inputtedValue: String) {
        if (disposable.isDisposed) {
            val value = checkValidateValue(inputtedValue)
            if (value < 0) {
                return
            }

            liveTextTV.postValue(R.string.calcButtonStop)
            val items = benchmark.getItemsList(true).toMutableList()
            itemsLiveData.postValue(ArrayList(items))

            disposable = Observable.fromIterable(items)
                .filter { rItem -> !rItem.isHeader() }
                .flatMap { it ->
                    Observable.fromCallable {
                        Pair.create(
                            items.indexOf(it),
                            it.copy(it, benchmark.getMeasureTime(it, value))
                        )
                    }
                }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { liveTextTV.postValue(R.string.calcButtonStart) }
                .subscribe({ pair ->
                    items[pair.first] = pair.second
                    itemsLiveData.postValue(ArrayList(items))
                }, { throwable -> throwable.printStackTrace() })
        } else {
            disposable.dispose()
        }
    }


    fun getSpan(): Int = benchmark.getSpan()

    private fun checkValidateValue(inputtedValue: String): Int {
        var value = -1
        var message: Int? = null
        try {
            value = inputtedValue.toInt()
        } catch (e: NumberFormatException) {
            message = R.string.empty_input_value
            e.printStackTrace()
        }
        return if (value == 0) {
            liveShowerMessages.value = R.string.OverZero
            -1
        } else {
            liveShowerMessages.value = message
            value
        }
    }
}
