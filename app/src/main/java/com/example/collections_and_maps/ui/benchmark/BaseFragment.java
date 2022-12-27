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

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    private ExecutorService service;
    private EditText inputFiled;
    private Button calcButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputFiled = view.findViewById(R.id.inputField);
        calcButton = view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(this);

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


    @Override
    public void onClick(View view) {
        final String inputText = inputFiled.getText().toString();

        if (service != null && !service.isShutdown()) {
            service.shutdownNow();
            calcButton.setText(R.string.calcButtonStart);
        } else {
            try {
                final int value = Integer.parseInt(inputText);
                calcButton.setText(R.string.calcButtonStop);
                List<ResultItem> newList = createTemplateList(true);
                service = Executors.newCachedThreadPool();

                for (ResultItem rItem : newList) {
                    service.submit(() -> {
                        final ResultItem resultItem = toMakeResultItem(rItem, value);
                        if (!service.isShutdown()) {
                            int index = newList.indexOf(rItem);
                            newList.set(index, resultItem);
                            updateUI(newList);
                        }
                    });
                }


            } catch (NumberFormatException e) {
                e.printStackTrace();
                if (inputText.isEmpty()) {
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



    protected abstract ResultItem toMakeResultItem(ResultItem rItem, int value);

    protected abstract int getSpanCount();

    protected abstract List<ResultItem> createTemplateList(boolean setAnimateItem);

    synchronized protected void updateUI(List<ResultItem> resultList) {
        handler.post(() -> adapter.submitList(new ArrayList<>(resultList)));
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







