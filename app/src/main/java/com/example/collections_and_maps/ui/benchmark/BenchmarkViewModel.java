package com.example.collections_and_maps.ui.benchmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.Benchmark;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BenchmarkViewModel extends ViewModel {

    private final MutableLiveData<List<ResultItem>> itemsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveTextTV = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveShowerMessages = new MutableLiveData<>();
    private final Benchmark benchmark;
    private Disposable disposable;

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
        if (itemsLiveData.getValue() == null) {
            itemsLiveData.setValue(benchmark.getItemsList(false));
        }
    }


    public void startMeasure(@NonNull String inputtedValue) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
            liveTextTV.setValue(R.string.calcButtonStart);
        } else {
            final int value = checkValidateValue(inputtedValue);

            if (value < 0) {
                return;
            }

            liveTextTV.setValue(R.string.calcButtonStop);
            itemsLiveData.setValue(benchmark.getItemsList(true));
            final List<ResultItem> items = getItemsLiveData().getValue();

            disposable = Observable.fromIterable(items)
                    .subscribeOn(Schedulers.io())
                    .subscribe(rItem -> {
                        if (!rItem.isHeader()) {
                            final ResultItem resultItem = new ResultItem(rItem.headerText, rItem.methodName,
                                    benchmark.getMeasureTime(rItem, value), false);
                            if (disposable != null) {
                                int index = items.indexOf(rItem);
                                items.set(index, resultItem);
                                itemsLiveData.postValue(new ArrayList<>(items));
                            }
                        }
                    }, Throwable::printStackTrace, () -> liveTextTV.postValue(R.string.calcButtonStart));
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
