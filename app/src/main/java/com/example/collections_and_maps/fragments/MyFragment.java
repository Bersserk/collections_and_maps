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
import com.example.collections_and_maps.MyItemDecoration;
import com.example.collections_and_maps.R;
import com.example.collections_and_maps.adapters.ListViewAdapter;

import java.util.ArrayList;


public abstract class MyFragment extends Fragment{
    protected static final String COLLECTIONS = "collections";
    protected static final String MAPS = "maps";

    protected String mCollections;
    protected String mMaps;

    protected LinearLayout mLinearLayoutNamesColumn;
    protected RecyclerView listRecycler;
    protected ListViewAdapter adapter;

    protected EditText collectionSize;
    protected String[] listArr, list;
    protected int spanCount;
    protected ArrayList baseList;
    protected long k = 0L;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCollections = getArguments().getString(COLLECTIONS);
            mMaps = getArguments().getString(MAPS);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        collectionSize = view.findViewById(R.id.collectionSize);
        mLinearLayoutNamesColumn = view.findViewById(R.id.linearLayoutNamesColumn);
        mLinearLayoutNamesColumn.setOrientation(LinearLayout.HORIZONTAL);

        listRecycler = view.findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new MyItemDecoration());
        listRecycler.setHasFixedSize(true);

        for (int i = 0; i < spanCount; i++){
            View view1 = View.inflate(getContext(), R.layout.item_tv_top, null);
            TextView dialogTV1 = (TextView) view1.findViewById(R.id.nameViewTop);
            dialogTV1.setText(listArr[i]);

            mLinearLayoutNamesColumn.addView(view1, new LinearLayout.LayoutParams
                    (0, LinearLayout.LayoutParams.MATCH_PARENT, 1));
        }
    }

    public void calc() {

        // get data from EditText
        if (collectionSize.length() > 0 && TextUtils.isDigitsOnly(collectionSize.getText())) {
            k = Long.parseLong(collectionSize.getText().toString());
        } else {
            Toast.makeText(getContext(), "Размер необходимо задавать только числами", Toast.LENGTH_LONG).show();
        }
    }

    public void createClearGrid(){
        baseList = new ArrayList<String>();
        for (int y = 0; y < list.length; y++) {
            baseList.add(list[y]);
            for (int i = 0; i < spanCount; i++) {
                baseList.add("...");
            }
        }
    }

}
