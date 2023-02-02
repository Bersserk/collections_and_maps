package com.example.collections_and_maps.ui.benchmark;

import static com.example.collections_and_maps.ui.PagerViewAdapter.POSITION;

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
import com.example.collections_and_maps.view_model.FragmentViewModel;

public class BaseFragment extends Fragment implements View.OnClickListener {

    private final BenchmarksAdapter adapter = new BenchmarksAdapter();
    private FragmentBenchmarkBinding binding;
    private FragmentViewModel fragmentViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Base - onCreate");
        assert getArguments() != null;

        final FragmentViewModel.FragmentFactory fragmentFactory = new FragmentViewModel.FragmentFactory(
                requireActivity().getApplication(), requireArguments().getInt(POSITION));

        fragmentViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) fragmentFactory)
                .get(FragmentViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("Base - onCreateView");

        binding = FragmentBenchmarkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("Base - onViewCreated");


        final int span = fragmentViewModel.span;
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(
                this.getActivity(), span, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new RecyclerSizeLookup(span + 1, 1, span));

        final RecyclerView listRecycler = requireView().findViewById(R.id.recyclerLayoutItems);
        listRecycler.addItemDecoration(new BenchmarksItemDecoration());
        listRecycler.setHasFixedSize(true);
        listRecycler.setLayoutManager(gridLayoutManager);

        fragmentViewModel.createTemplateList(false);
        subscribeToModel();

        // send into recycler
        listRecycler.setAdapter(adapter);
        binding.calcButton.setOnClickListener(this);
    }

    private void subscribeToModel() {
        fragmentViewModel.getLiveResultItem()
                .observe(getViewLifecycleOwner(), resultItems -> {
                    fragmentViewModel.updateUI(resultItems, adapter);
                });
    }

    @Override
    public void onClick(View v) {
        fragmentViewModel.onClick(binding, adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
