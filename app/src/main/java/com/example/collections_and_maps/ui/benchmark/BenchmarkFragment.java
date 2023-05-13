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

public class BenchmarkFragment extends Fragment implements View.OnClickListener {

    private static final String FRAGMENT_TYPE = "fragmentType";

    private final BenchmarkAdapter adapter = new BenchmarkAdapter();
    private FragmentBenchmarkBinding binding;
    private BenchmarkViewModel model;

    public static BenchmarkFragment newInstance(int namePagerView) {
        final BenchmarkFragment fragmentView = new BenchmarkFragment();
        final Bundle args = new Bundle();
        args.putInt(FRAGMENT_TYPE, namePagerView);
        fragmentView.setArguments(args);
        return fragmentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        final BenchmarkViewModelFactory benchmarkFactory = new BenchmarkViewModelFactory(getArguments()
                .getInt(FRAGMENT_TYPE), getContext());

        model = new ViewModelProvider(this, (ViewModelProvider.Factory) benchmarkFactory)
                .get(BenchmarkViewModel.class);
        model.onCreate();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBenchmarkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        final int span = model.getSpan();
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(
                this.getActivity(), span, LinearLayoutManager.VERTICAL, false
        );
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(span + 1, 1, span));

        final RecyclerView listRecycler = requireView().findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new BenchmarkItemDecoration());
        listRecycler.setHasFixedSize(true);
        listRecycler.setLayoutManager(gridLayoutManager);

        model.getItemsLiveData().observe(getViewLifecycleOwner(), adapter::submitList);

        model.getLiveTextTV().observe(getViewLifecycleOwner(),
                integer -> binding.calcButton.setText(integer)
        );

        model.getLiveShowerMessages().observe(getViewLifecycleOwner(),
                integer -> binding.inputField.setError(integer == null
                        ? null : getText(integer)));

        listRecycler.setAdapter(adapter);
        binding.calcButton.setOnClickListener(this);
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
