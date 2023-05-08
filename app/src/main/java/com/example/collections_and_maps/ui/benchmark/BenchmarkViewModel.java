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
            final List<ResultItem> items = benchmark.getItemsList(true);
            itemsLiveData.setValue(new ArrayList<>(items));

            disposable = Observable.fromIterable(items)
                    .filter(rItem -> !rItem.isHeader())
                    .map(rItem -> {
                        final ResultItem resultItem = new ResultItem(
                                rItem.headerText, rItem.methodName,
                                benchmark.getMeasureTime(rItem, value),
                                false);
                        int index = items.indexOf(rItem);
                        items.set(index, resultItem);
                        return resultItem;
                    })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            resultItem -> {
                                itemsLiveData.setValue(new ArrayList<>(items));
                            },
                            Throwable::printStackTrace,
                            () -> {
                                liveTextTV.setValue(R.string.calcButtonStart);
                            }
                    );

        } else {
            disposable.dispose();
        }
    }

    public ResultItem todo(ResultItem rItem) {


        return new ResultItem(rItem.headerText, rItem.methodName,
                benchmark.getMeasureTime(rItem, value), false);


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
