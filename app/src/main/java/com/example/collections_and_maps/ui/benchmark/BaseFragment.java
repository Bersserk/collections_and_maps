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
import com.example.collections_and_maps.models.benchmarks.Compute;
import com.example.collections_and_maps.models.benchmarks.ResultItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    private EditText inputFiled;
    private ExecutorService service;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private int startedTasks;

    protected BaseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputFiled = view.findViewById(R.id.inputField);
        final Button calcButton = view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(this);

        final int spans = getSpanCount();
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                spans, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(spans + 1, 1, spans));

        final RecyclerView listRecycler = requireView().findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new BenchmarksItemDecoration());
        listRecycler.setHasFixedSize(true);
        listRecycler.setLayoutManager(gridLayoutManager);

        adapter.submitList(createTemplateList());
        listRecycler.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {

        try {
            final int value = Integer.parseInt(inputFiled.getText().toString());
            final List<ResultItem> tempList = createTemplateList();

            if (startedTasks > 0) {
                // to do stopping the calculation
                System.out.println("stop the calculation, startedTasks = " + startedTasks);
            } else if (value > 0 && value < 10000001) {

                startedTasks = tempList.size();
                service = Executors.newCachedThreadPool();
                for (int i = 0; i < tempList.size(); i++) {
                    int index = i;
                    service.submit(new Runnable() {
                        @Override
                        public void run() {
                            final ResultItem resultItem = new Compute(tempList.get(index)).getResultItem();
                            tempList.set(index, resultItem);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    updateUI(new ArrayList<>(tempList));
                                    startedTasks--;
                                    System.out.println("handler.post (" + index + "), startedTasks = " + startedTasks);
                                }
                            });
                        }
                    });
                }

//                System.out.println("handler.toString() = " + handler.toString());
//                System.out.println("handler.getLooper() = " + handler.getLooper());
//                System.out.println("isTerminated() = " + service.isTerminated());
//                System.out.println("isShutdown() = " + service.isShutdown());
//                SystemClock.sleep(2000);
////                service.shutdown();
//                System.out.println("isTerminated(2) = " + service.isTerminated());
//                System.out.println("isShutdown(2) = " + service.isShutdown());
//                SystemClock.sleep(5000);
//                System.out.println("isTerminated(5) = " + service.isTerminated());
//                System.out.println("isShutdown(5) = " + service.isShutdown());

            } else if (value >= 10000001) {
                Toast.makeText(getContext(), R.string.LimitValue, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), R.string.OverZero, Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            if (inputFiled.length() == 0) {
                Toast.makeText(getContext(), R.string.NeedAnyValue, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), R.string.OnlyNumber, Toast.LENGTH_LONG).show();
                inputFiled.setText("");
            }
            e.printStackTrace();
        }
    }

    protected abstract int getSpanCount();

    protected abstract List<ResultItem> createTemplateList();

    private void updateUI(List<ResultItem> resultList) {
        adapter.submitList(resultList);
    }


//    private void calc(long value) {
//        service = Executors.newCachedThreadPool();
//        newList = new ArrayList<>(adapter.getCurrentList());
//
//        int i = 0;
//        for (ResultItem res : newList) {
//            if (res.headerText == 0 && res.methodName == 0) {
//                addTask(i);
//            }
//            i++;
//        }
//
//        service.shutdownNow();
//    }


    // we will need this block later ***
//    public static CollectionsPagerFragment newInstance(String fragmentData) {
//        CollectionsPagerFragment fragment = new CollectionsPagerFragment();
//        Bundle args = new Bundle();
//        args.putString(TYPE_BENCHMARK, fragmentData);
//        fragment.setArguments(args);
//        return fragment;
//    }
}






