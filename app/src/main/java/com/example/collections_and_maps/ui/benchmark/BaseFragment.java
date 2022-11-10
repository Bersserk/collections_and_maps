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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected static final String TYPE_BENCHMARK = "type";


    private ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private Handler handler = new Handler(Looper.getMainLooper());

    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    protected EditText collectionSize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        collectionSize = view.findViewById(R.id.collectionSize);
        Button calcButton = view.findViewById(R.id.calcButton);
        calcButton.setOnClickListener(this);

        final int spans = getSpanCount();
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                spans, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(spans + 1, 1, spans));

        RecyclerView listRecycler = getView().findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new BenchmarksItemDecoration());
        listRecycler.setHasFixedSize(true);
        listRecycler.setLayoutManager(gridLayoutManager);

        adapter.submitList(this.createTemplateList());
        listRecycler.setAdapter(adapter);
    }

    protected abstract int getSpanCount();

                List <String> newList2;

    @Override
    public void onClick(View view) {


        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    newList2 = new ArrayList<>(adapter.getCurrentList());
                    newList2.set(5, String.valueOf(getSizeList()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.submitList(newList2);
                    }
                });
            }
        });


        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    newList2 = new ArrayList<>(adapter.getCurrentList());
                    newList2.set(4, String.valueOf(getSizeList()));
                    newList2.set(5, "second");
                    newList2.set(6, "thirty");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.submitList(newList2);
                    }
                });
            }
        });

        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    newList2 = new ArrayList<>(adapter.getCurrentList());
                    newList2.set(8, String.valueOf(getSizeList()));
                    newList2.set(9, "fourth");
                    newList2.set(10, "fifth");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.submitList(newList2);
                    }
                });
            }
        });




    }

    private List<String> updateList(List<String> currentList) {
        return currentList;
    }
//        });


//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                    adapter.submitList(new Compute(adapter.getCurrentList(), getSizeList()));
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    }).start();
//            }
//        });


    protected abstract List<String> getResults(List<String> templateList, int sizeList);

    protected abstract List<String> createTemplateList();

    protected List<String> createTemplateList(int listNamesMainItem, int listNamesItem) {

        String[] listMain = getResources().getStringArray(listNamesMainItem);
        String[] listItem = getResources().getStringArray(listNamesItem);

        List<String> templateList = new ArrayList<>(Arrays.asList(listMain));

        for (String s : listItem) {
            templateList.add(s);
            for (int i = 0; i < listMain.length; i++) {
                templateList.add("");
            }
        }
        return templateList;
    }

    public int getSizeList() {
        int size;
        try {
            size = Integer.parseInt(collectionSize.getText().toString());
            if (size > 0) {
                return size;
            } else {
                Toast.makeText(getContext(), R.string.OverZero, Toast.LENGTH_LONG).show();
                return 0;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), R.string.ToastsText, Toast.LENGTH_LONG).show();
            return 0;
        }
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






