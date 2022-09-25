package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;

import java.util.ArrayList;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected static final String COLLECTIONS = "collections";
    protected String mCollections;

//    protected final BenchmarksAdapter adapter = new BenchmarksAdapter(this);

    protected EditText collectionSize;
//    protected String[] listNamesMainItem, listNamesItem;
    protected int spanCount;
//    protected ArrayList baseList  = new ArrayList<String>();
    protected long k = 0L;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

//        if (savedInstanceState != null) {
//            baseList = savedInstanceState.getStringArrayList("baseList");
//        }

        if (getArguments() != null) {
            mCollections = getArguments().getString(COLLECTIONS);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_benchmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        collectionSize = view.findViewById(R.id.collectionSize);
    }

    protected RecyclerView getRecycler() {
        RecyclerView listRecycler = getView().findViewById(R.id.recyclerLayoutItems);
        if(listRecycler.getItemDecorationCount()<1){
            listRecycler.addItemDecoration(new BenchmarksItemDecoration());
        }
        listRecycler.setHasFixedSize(true);
        return listRecycler;
    }

    public void checkInputCorrectly() {
//        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        // get data from EditText
        if (collectionSize.length() > 0 && TextUtils.isDigitsOnly(collectionSize.getText())) {
            k = Long.parseLong(collectionSize.getText().toString());
        } else {
            Toast.makeText(getContext(), R.string.ToastsText, Toast.LENGTH_LONG).show();
        }
    }

//    public void createClearGrid() {
////        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);
//
//        if (baseList == null) {
//            baseList = new ArrayList<String>();
//
//            for (int y = 0; y < spanCount; y++){
//                baseList.add(listNamesMainItem[y]);
//            }
//
//            for (int y = 0; y < listNamesItem.length; y++) {
//                baseList.add(listNamesItem[y]);
//                for (int i = 0; i < spanCount; i++) {
//                    baseList.add("...");
//                }
//            }
//        }
//    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putStringArrayList("baseList", baseList);
    }
}
