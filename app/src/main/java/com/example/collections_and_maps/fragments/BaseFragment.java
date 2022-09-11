package com.example.collections_and_maps.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.RecyclerItemDecoration;
import com.example.collections_and_maps.adapters.ItemsAdapter;

import java.util.ArrayList;


public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected static final String COLLECTIONS = "collections";
    protected String mCollections;

    protected ItemsAdapter adapter;

    protected EditText collectionSize;
    protected String[] listArr, list;
    protected int spanCount;
    protected ArrayList baseList;
    protected long k = 0L;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        if (savedInstanceState != null) {
            baseList = savedInstanceState.getStringArrayList("baseList");
        }

        if (getArguments() != null) {
            mCollections = getArguments().getString(COLLECTIONS);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        collectionSize = view.findViewById(R.id.collectionSize);
        LinearLayout mLinearLayoutNamesColumn = view.findViewById(R.id.linearLayoutNamesColumn);
        mLinearLayoutNamesColumn.setOrientation(LinearLayout.HORIZONTAL);




        for (int i = 0; i < spanCount; i++) {
            View view1 = View.inflate(getContext(), R.layout.item_tv_top, null);
            TextView dialogTV1 = (TextView) view1.findViewById(R.id.nameViewTop);
            dialogTV1.setText(listArr[i]);

            mLinearLayoutNamesColumn.addView(view1, new LinearLayout.LayoutParams
                    (0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        }
    }

    protected RecyclerView getRecycler() {
        RecyclerView listRecycler = getView().findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new RecyclerItemDecoration());
        listRecycler.setHasFixedSize(true);
        return listRecycler;
    }


    public void fillRecycler() {
//        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        // get data from EditText
        if (collectionSize.length() > 0 && TextUtils.isDigitsOnly(collectionSize.getText())) {
            k = Long.parseLong(collectionSize.getText().toString());
        } else {
            Toast.makeText(getContext(), "Размер необходимо задавать только числами", Toast.LENGTH_LONG).show();
        }
    }

    public void createClearGrid() {
//        ComfortableLogsTV.log(this.getClass(), Thread.currentThread().getStackTrace()[2]);

        if (baseList == null) {
            baseList = new ArrayList<String>();

            for (int y = 0; y < list.length; y++) {
                baseList.add(list[y]);
                for (int i = 0; i < spanCount; i++) {
                    baseList.add("...");
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("baseList", baseList);
    }

}
