package com.example.collections_and_maps.view_model;

import static com.example.collections_and_maps.models.benchmarks.ResultItem.EMPTY;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.DataFilter;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;

public class FragmentFactory extends ViewModelProvider.NewInstanceFactory {

    private final MutableLiveData<List<ResultItem>> resultItems = new MutableLiveData<>();
    private final DataFilter dataFilter;

    public FragmentFactory(DataFilter dataFilter) {
        this.dataFilter = dataFilter;
    }

    @SuppressWarnings("unchecked")
    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FragmentViewModel(resultItems);
    }

    public void createTemplateList(boolean itemAnimated) {

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
        resultItems.setValue(items);
    }


    public ResultItem createNewResultItem(@NonNull ResultItem rItem, int value) {
        return rItem.isHeader() ? rItem : new ResultItem(rItem.headerText, rItem.methodName,
                dataFilter.getComputeTime().getMeasureTime(rItem, value), false);
    }
}
