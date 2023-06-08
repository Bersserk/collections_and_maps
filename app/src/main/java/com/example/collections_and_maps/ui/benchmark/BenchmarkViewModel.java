package com.example.collections_and_maps.ui.benchmark;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BenchmarkViewModel extends ViewModel {
    private static final String TAG = "itemsData";

    private final MutableLiveData<List<ResultItem>> itemsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveTextTV = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveShowerMessages = new MutableLiveData<>();
    private final Benchmark benchmark;
    private Disposable disposable = Disposable.disposed();

    public BenchmarkViewModel(Benchmark benchmark) {
        this.benchmark = benchmark;
    }

    public LiveData<List<ResultItem>> getItemsLiveData() {
        return itemsLiveData;
    }

    public LiveData<Integer> getLiveTextTV() {
        return liveTextTV;
    }

    public LiveData<Integer> getLiveShowerMessages() {
        return liveShowerMessages;
    }

    public void onCreate() {
        itemsLiveData.setValue(benchmark.getItemsList(false));
    }

    public void startMeasure(@NonNull String inputtedValue) {
//        Log.i(TAG, "Before disposable = " + disposable.getClass().toString());
        if (disposable.isDisposed()) {
            final int value = checkValidateValue(inputtedValue);
            if (value < 0) {
                return;
            }

            liveTextTV.setValue(R.string.calcButtonStop);
            final List<ResultItem> items = benchmark.getItemsList(true);
            itemsLiveData.setValue(new ArrayList<>(items));

//            Log.d(CALl, "start disposable = " + disposable)
//            Log.d("Thread", "Current thread [beforeRxJava] : " + Thread.currentThread().getName());
            disposable = Observable.fromIterable(items)
                    .filter(rItem -> !rItem.isHeader())
                    .flatMap(it -> Observable.fromCallable(() -> Pair.create(
                                    getIndex(it, items), getNewItem(it, value))
//                            items.indexOf(it),
//                            it.copy(it, benchmark.getMeasureTime(it, value)))
                    )).subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> liveTextTV.setValue(R.string.calcButtonStart))
                    .subscribe(pair -> {
//                        Log.d(TAG, "subscribeIndex = " + pair.first);
//                        Log.i(TAG, "InAction disposable = " + disposable.getClass().toString());
                          putIndex(pair, items);
                          putNewListItems(items);
                            Log.d("Thread", "Current thread [subscribe]: " + Thread.currentThread().getName());
//                        items.set(pair.first, pair.second);
//                        itemsLiveData.setValue(new ArrayList<>(items));
                    }, Throwable::printStackTrace);

        } else {
//            Log.i(TAG, "else -> disposable.dispose()");
            Log.d("Thread", "Current thread [else]: " + Thread.currentThread().getName());
            disposable.dispose();
        }
//        Log.i(TAG, "After disposable = " + disposable.getClass().toString());
//        Log.i(TAG, "end of method");
    }

    private int getIndex(ResultItem it, List<ResultItem> items) {
        int index = items.indexOf(it);
//        Log.i(TAG, "nidex item = " + index);
        return index;
    }

    private ResultItem getNewItem(ResultItem it, int value) {
//        Log.i(TAG, "OldItem = " + it);
        Log.d("Thread", "Current thread [newItem]: " + Thread.currentThread().getName());
        //        Log.i(TAG, "NewItem = " + newItem);
        return it.copy(it, benchmark.getMeasureTime(it, value));
    }

    private void putIndex(Pair <Integer, ResultItem> pair, List<ResultItem> items) {
//        Log.i(TAG, "index newItem = " + pair.first);
//        Log.i(TAG, "newItem = " + pair.second);
        items.set(pair.first, pair.second);
    }

    private void putNewListItems(List <ResultItem> items) {
        List <ResultItem> newItems = new ArrayList<>(items);
//        Log.i(TAG, "newListItems = " + newItems);
        itemsLiveData.setValue(newItems);
    }


    public int getSpan() {
        return benchmark.getSpan();
    }

    private int checkValidateValue(String inputtedValue) {
        int value = -1;
        Integer message = null;
        try {
            value = Integer.parseInt(inputtedValue);
        } catch (NumberFormatException e) {
            message = R.string.empty_input_value;
            e.printStackTrace();
        }
        liveShowerMessages.setValue(message);
        return value;
    }
}
