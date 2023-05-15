package com.example.collections_and_maps.ui.benchmark;

import android.util.Pair;

import androidx.annotation.NonNull;
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

    public int value;

    public void onCreate() {
        itemsLiveData.setValue(benchmark.getItemsList(false));
    }

    public void startMeasure(@NonNull String inputtedValue) {
        if (disposable.isDisposed()) {
            final int value = checkValidateValue(inputtedValue);
            if (value < 0) {
                return;
            }

            liveTextTV.setValue(R.string.calcButtonStop);
            List<ResultItem> items = benchmark.getItemsList(true);
            itemsLiveData.setValue(new ArrayList<>(items));

            disposable = Observable.fromIterable(items)
                    .filter(rItem -> !rItem.isHeader())
                    .flatMap(item -> Observable.just(item)
                            .subscribeOn(Schedulers.io())
                            .map(oldResultItem -> {
                                Integer index = items.indexOf(oldResultItem);
                                ResultItem newResultItem = new ResultItem(oldResultItem, benchmark.getMeasureTime(oldResultItem, value));
                                return Pair.create(index, newResultItem);
                            }))
                    .observeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> liveTextTV.setValue(R.string.calcButtonStart))
                    .subscribe(pair -> {
                        items.set(pair.first, pair.second);
                        itemsLiveData.setValue(new ArrayList<>(items));
                    }, Throwable::printStackTrace);
        } else {
            disposable.dispose();
        }
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
