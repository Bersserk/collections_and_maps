package com.example.collections_and_maps.ui.benchmark;

import android.os.Bundle;
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
import com.example.collections_and_maps.ui.models.BenchmarkItemDecoration;
import com.example.collections_and_maps.ui.models.RecyclerSizeLookup;
import com.example.collections_and_maps.view_model.benchmark.BenchmarkViewModel;
import com.example.collections_and_maps.view_model.benchmark.BenchmarkViewModelFactory;
import com.example.collections_and_maps.view_model.interfases.DefaultList;
import com.example.collections_and_maps.view_model.models.ValueValidator;

public class BenchmarkFragmentView extends Fragment implements View.OnClickListener {

    private static final String BENCHMARK_TYPE = "namePagerView";

    private final BenchmarkAdapter adapter = new BenchmarkAdapter();
    private FragmentBenchmarkBinding binding;
    private BenchmarkViewModel model;

    public static BenchmarkFragmentView newInstance(int namePagerView) {
        BenchmarkFragmentView fragmentView = new BenchmarkFragmentView();
        Bundle args = new Bundle();
        args.putInt(BENCHMARK_TYPE, namePagerView);
        fragmentView.setArguments(args);
        return fragmentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;

        final BenchmarkViewModelFactory benchmarkFactory = new BenchmarkViewModelFactory(getArguments()
                .getInt(BENCHMARK_TYPE));

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
                this.getActivity(), span, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(span + 1, 1, span));

        final RecyclerView listRecycler = requireView().findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new BenchmarkItemDecoration());
        listRecycler.setHasFixedSize(true);
        listRecycler.setLayoutManager(gridLayoutManager);

        setDefaultList(model);
        model.getItemsLiveData().observe(getViewLifecycleOwner(), adapter::submitList);

        model.getLiveTextTV().observe(getViewLifecycleOwner(),
                integer -> binding.calcButton.setText(integer));

        listRecycler.setAdapter(adapter);
        binding.calcButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        model.startMeasure(new ValueValidator(binding.inputField.getText()));
    }


    private void setDefaultList(DefaultList list) {
        list.onCreate(false);
    }

    private int getSpan() {
        assert getArguments() != null;
        switch (getArguments().getInt(BENCHMARK_TYPE)) {
            case R.string.Collections:
                return 3;
            case R.string.Maps:
                return 2;
            default:
                throw new IllegalArgumentException("key's value is Illegal");
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
