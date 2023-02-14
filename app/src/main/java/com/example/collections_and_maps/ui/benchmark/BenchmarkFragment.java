package com.example.collections_and_maps.ui.benchmark;

import static android.widget.Toast.LENGTH_SHORT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.databinding.FragmentBenchmarkBinding;
import com.example.collections_and_maps.models.benchmarks.DefaultList;

public class BenchmarkFragment extends Fragment implements View.OnClickListener {

    private static final String NAME_PAGER_VIEW = "namePagerView";

    private final BenchmarkAdapter adapter = new BenchmarkAdapter();
    private FragmentBenchmarkBinding binding;
    private BenchmarkViewModel model;

    public static BenchmarkFragment newInstance(int namePagerView) {
        final BenchmarkFragment fragmentView = new BenchmarkFragment();
        final Bundle args = new Bundle();
        args.putInt(NAME_PAGER_VIEW, namePagerView);
        fragmentView.setArguments(args);
        return fragmentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final BenchmarkViewModelFactory benchmarkFactory = new BenchmarkViewModelFactory(getArguments()
                .getInt(NAME_PAGER_VIEW));

        model = new ViewModelProvider(this, (ViewModelProvider.Factory) benchmarkFactory)
                .get(BenchmarkViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBenchmarkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final int span = getSpan();
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(
                this.getActivity(), span, LinearLayoutManager.VERTICAL, false
        );
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(span + 1, 1, span));

        final RecyclerView listRecycler = requireView().findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new BenchmarkItemDecoration());
        listRecycler.setHasFixedSize(true);
        listRecycler.setLayoutManager(gridLayoutManager);

        setDefaultList(model);
        model.getItemsLiveData().observe(getViewLifecycleOwner(), adapter::submitList);

        model.getLiveTextTV().observe(getViewLifecycleOwner(),
                integer -> binding.calcButton.setText(integer)
        );

        model.getLiveShowerMessages().observe(getViewLifecycleOwner(),
                toastMessage -> Toast.makeText(BenchmarkFragment.this.getContext(),
                        toastMessage, LENGTH_SHORT).show()
        );

        listRecycler.setAdapter(adapter);
        binding.calcButton.setOnClickListener(this);
    }

    private void setDefaultList(DefaultList list) {
        list.onCreate(false);
    }

    private int getSpan() {
        assert getArguments() != null;
        final int key = getArguments().getInt(NAME_PAGER_VIEW);
        switch (key) {
            case R.string.Collections:
                return 3;
            case R.string.Maps:
                return 2;
            default:
                throw new IllegalArgumentException("key's value is Illegal");
        }
    }

    @Override
    public void onClick(View v) {
        model.startMeasure(binding.inputField.getText().toString());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}