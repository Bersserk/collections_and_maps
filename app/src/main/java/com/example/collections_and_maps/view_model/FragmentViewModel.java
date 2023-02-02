package com.example.collections_and_maps.view_model;

import static com.example.collections_and_maps.models.benchmarks.ResultItem.EMPTY;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.databinding.FragmentBenchmarkBinding;
import com.example.collections_and_maps.models.benchmarks.DataFilter;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.ui.benchmark.BenchmarksAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FragmentViewModel extends AndroidViewModel {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private ExecutorService service;
    private LiveData<List<ResultItem>> liveResultItem;
    private final DataFilter dataFilter;
    public final int span;

    public LiveData<List<ResultItem>> getLiveResultItem() {
        return liveResultItem;
    }

    public FragmentViewModel(@NonNull Application application, DataFilter dataFilter) {
        super(application);
        System.out.println("View - FragmentViewModel");

        this.dataFilter = dataFilter;
        this.span = dataFilter.getSpan();
    }


    public void createTemplateList(boolean itemAnimated) {
        System.out.println("View - createTemplateList");


        final List<ResultItem> items = new ArrayList<>();

        for (int itemOfListHead : dataFilter.getListHeadsId()) {
            items.add(new ResultItem(itemOfListHead, R.string.empty, EMPTY, false));
        }

        for (int methodsID : dataFilter.getListMethodsId()) {
            items.add(new ResultItem(R.string.empty, methodsID, EMPTY, false));
            for (int headsID : dataFilter.getListHeadsId()) {
                items.add(new ResultItem(headsID, methodsID, EMPTY, itemAnimated));
            }
        }
        liveResultItem = new MutableLiveData<>(items);
    }


    public ResultItem createNewResultItem(@NonNull ResultItem rItem, int value) {
        return rItem.isHeader() ? rItem : new ResultItem(rItem.headerText, rItem.methodName,
                dataFilter.getComputeTime().getMeasureTime(rItem, value), false);
    }

    public void updateUI(List<ResultItem> resultList, BenchmarksAdapter adapter) {
        System.out.println("View - updateUI");

        handler.post(() -> adapter.submitList(new ArrayList<>(resultList)));
    }

    public void onClick(FragmentBenchmarkBinding binding, BenchmarksAdapter adapter) {
        System.out.println("View - onClick");

        if (service == null || service.isShutdown()) {
            binding.calcButton.setText(R.string.calcButtonStop);

            // send clear list with animation
            createTemplateList(true);
            final List<ResultItem> newList = getLiveResultItem().getValue();

            service = Executors.newCachedThreadPool();
            final AtomicInteger counterActiveThreads = new AtomicInteger();
            final int value = checkValidateValue(binding.inputField.getText());

            for (ResultItem rItem : newList) {
                counterActiveThreads.getAndIncrement();
                service.submit(() -> {
                    final ResultItem resultItem = createNewResultItem(rItem, value);
                    if (!service.isShutdown()) {
                        int index = newList.indexOf(rItem);
                        newList.set(index, resultItem);
                        liveResultItem = new MutableLiveData<>(newList);
                        updateUI(newList, adapter);

                        if (counterActiveThreads.decrementAndGet() == 0) {
                            service.shutdown();
                            binding.calcButton.setText(R.string.calcButtonStart);
                        }
                    }
                });
            }
        } else {
            service.shutdownNow();
            binding.calcButton.setText(R.string.calcButtonStart);
        }
    }



    private int checkValidateValue(@NonNull Editable inputtedValue) {
        final String in = inputtedValue.toString();
        int value;
        try {
            value = Integer.parseInt(in);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            value = 0;
        }
        return value;
    }


    public static class FragmentFactory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;
        private final int position;

        public FragmentFactory(@NonNull Application application, int position) {
            this.application = application;
            this.position = position;
        }

        @SuppressWarnings("unchecked")
        @Override
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new FragmentViewModel(application, new DataFilter(position));
        }
    }

}

