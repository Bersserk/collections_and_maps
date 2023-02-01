package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.databinding.FragmentBenchmarkBinding;
import com.example.collections_and_maps.models.benchmarks.DataFilter;
import com.example.collections_and_maps.models.benchmarks.ResultItem;
import com.example.collections_and_maps.view_model.FragmentFactory;
import com.example.collections_and_maps.view_model.FragmentViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseFragment extends Fragment implements View.OnClickListener {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    private ExecutorService service;
    private FragmentBenchmarkBinding binding;

    private FragmentViewModel fragmentViewModel;
    private FragmentFactory fragmentFactory;

    private DataFilter dataFilter;

    public void setDataForFragments(DataFilter dataForFragments) {
        dataFilter = dataForFragments;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBenchmarkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // init FragmentFactory and inflate data for current Fragment
        fragmentFactory = new FragmentFactory(dataFilter);
        // init FragmentViewModel with FragmentFactory
        fragmentViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) fragmentFactory)
                .get(FragmentViewModel.class);

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(
                this.getActivity(), dataFilter.getSpan(), LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(dataFilter.getSpan() + 1, 1, dataFilter.getSpan()));

        final RecyclerView listRecycler = requireView().findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new BenchmarksItemDecoration());
        listRecycler.setHasFixedSize(true);
        listRecycler.setLayoutManager(gridLayoutManager);

        // set and get ResultList
        fragmentFactory.createTemplateList(false);
        subscribeToModel();

        // send into recycler
        listRecycler.setAdapter(adapter);
        binding.calcButton.setOnClickListener(this);
    }

    private void subscribeToModel() {
        fragmentViewModel.getLiveDataResultItem()
                .observe(getViewLifecycleOwner(), this::updateUI);
    }


    @Override
    public void onClick(View v) {
        if (service == null || service.isShutdown()) {
            binding.calcButton.setText(R.string.calcButtonStop);

            // send clear list with animation
            fragmentFactory.createTemplateList(true);
            final List<ResultItem> newList = fragmentViewModel.getLiveDataResultItem().getValue();

            service = Executors.newCachedThreadPool();
            final AtomicInteger counterActiveThreads = new AtomicInteger();
            final int value = checkValidateValue(binding.inputField.getText());

            for (ResultItem rItem : newList) {
                counterActiveThreads.getAndIncrement();
                service.submit(() -> {
                    final ResultItem resultItem = fragmentFactory.createNewResultItem(rItem, value);
                    if (!service.isShutdown()) {
                        int index = newList.indexOf(rItem);
                        newList.set(index, resultItem);
                        System.out.println(newList);
                        updateUI(newList);

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

    synchronized protected void updateUI(List<ResultItem> resultList) {
        handler.post(() -> adapter.submitList(new ArrayList<>(resultList)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("dataFilter", dataFilter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            dataFilter = (DataFilter) savedInstanceState.getSerializable("dataFilter");
        }
    }
}
