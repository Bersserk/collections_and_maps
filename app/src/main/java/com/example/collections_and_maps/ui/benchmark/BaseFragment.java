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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.databinding.FragmentBenchmarkBinding;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    private ExecutorService service;
    private FragmentBenchmarkBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBenchmarkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final int spans = getSpanCount();
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(
                this.getActivity(), spans, LinearLayoutManager.VERTICAL, false
        );
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(spans + 1, 1, spans));

        final RecyclerView listRecycler = requireView().findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new BenchmarksItemDecoration());
        listRecycler.setHasFixedSize(true);
        listRecycler.setLayoutManager(gridLayoutManager);

        adapter.submitList(createTemplateList(false));
        listRecycler.setAdapter(adapter);

        binding.calcButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (service == null || service.isShutdown()) {
            binding.calcButton.setText(R.string.calcButtonStop);
            final List<ResultItem> newList = createTemplateList(true);
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


    protected abstract ResultItem createNewResultItem(ResultItem rItem, int value);

    protected abstract int getSpanCount();

    protected abstract List<ResultItem> createTemplateList(boolean itemAnimated);

    synchronized protected void updateUI(List<ResultItem> resultList) {
        handler.post(() -> adapter.submitList(new ArrayList<>(resultList)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    // we will need this block later ***
//    public static CollectionsPagerFragment newInstance(String fragmentData) {
//        CollectionsPagerFragment fragment = new CollectionsPagerFragment();
//        Bundle args = new Bundle();
//        args.putString(TYPE_BENCHMARK, fragmentData);
//        fragment.setArguments(args);
//        return fragment;
//    }
}
