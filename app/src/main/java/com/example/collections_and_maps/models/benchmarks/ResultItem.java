package com.example.collections_and_maps.models.benchmarks;

import com.example.collections_and_maps.R;
import com.example.collections_and_maps.databinding.ItemBenchmarkBinding;

public class ResultItem {
    public final int headerText;
    public final int methodName;
    public final boolean hasAnimated;
    public double result;

    public ResultItem(int headerText, int methodName, double result, boolean hasAnimated) {
        this.headerText = headerText;
        this.methodName = methodName;
        this.result = result;
        this.hasAnimated = hasAnimated;
    }


    public boolean hasAnimated() {
        return hasAnimated;
    }

    public void setDataForTV(ItemBenchmarkBinding binding) {

        if (headerText == R.string.empty) {
            binding.nameView.setText(methodName);
        } else if (methodName == R.string.empty) {
            binding.nameView.setText(headerText);
        } else if (!(result == R.string.empty)) {
            binding.nameView.setText(String.format("%s ms", result));
        }
    }
}
