package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    private ExecutorService service;
    private Unbinder unbinder;

    @Nullable
    @BindView(R.id.inputField)
    EditText inputFiled;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

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
    }


    @Optional
    @OnClick(R.id.calcButton)
    public void onClick(Button button) {
        final String inputtedValue = inputFiled.getText().toString();

        if (service != null && !service.isShutdown()) {
            service.shutdownNow();
            button.setText(R.string.calcButtonStart);
        } else {
            button.setText(R.string.calcButtonStop);
            final List<ResultItem> newList = createTemplateList(true);
            service = Executors.newCachedThreadPool();
            final AtomicInteger counterActiveThreads = new AtomicInteger();

            try {
                final int value = Integer.parseInt(inputtedValue);
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
                                button.setText(R.string.calcButtonStart);
                            }
                        }
                    });
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                if (inputtedValue.isEmpty()) {
                    Toast.makeText(getContext(), R.string.NeedAnyValue, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), R.string.OnlyNumber, Toast.LENGTH_LONG).show();
                    inputFiled.setText("");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }


    protected abstract ResultItem createNewResultItem(ResultItem rItem, int value);

    protected abstract int getSpanCount();

    protected abstract List<ResultItem> createTemplateList(boolean showProgress);

    synchronized protected void updateUI(List<ResultItem> resultList) {
        handler.post(() -> adapter.submitList(new ArrayList<>(resultList)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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







