package com.example.collections_and_maps.ui.benchmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.collections_and_maps.R
import com.example.collections_and_maps.databinding.ItemBenchmarkBinding
import com.example.collections_and_maps.models.benchmarks.ResultItem
import com.example.collections_and_maps.ui.benchmark.BenchmarkAdapter.BenchmarkViewHolder

class BenchmarkAdapter : ListAdapter<ResultItem, BenchmarkViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenchmarkViewHolder {
        val binding = ItemBenchmarkBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BenchmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BenchmarkViewHolder, position: Int) {
        holder.bindTo(getItem(position)!!)
    }

    class BenchmarkViewHolder(
        private val binding: ItemBenchmarkBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(item: ResultItem) {
            val showProgress = if (item.progressVisible) ON else OFF
            if (binding.progressBar.alpha != showProgress) {
                binding.progressBar.animate().setDuration(300).alpha(showProgress).start()
            }
            setDisplayItemData(item)
        }

        fun setDisplayItemData(item: ResultItem) {
            if (item.isHeader()) {
                binding.nameView.setText(item.nameForHeader)
            } else if (item.isResult()) {
                binding.nameView.text =
                    itemView.context.getString(R.string.timing, item.timing.toString())
            } else {
                binding.nameView.text = ""
            }
        }

        companion object {
            private const val ON = 1.0f
            private const val OFF = 0.0f
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ResultItem> =
            object : DiffUtil.ItemCallback<ResultItem>() {
                override fun areItemsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
                    return oldItem.isHeader() == newItem.isHeader()
                }

                override fun areContentsTheSame(oldItem: ResultItem, newItem: ResultItem): Boolean {
                    return !newItem.progressVisible && oldItem.timing == newItem.timing
                }
            }
    }
}
