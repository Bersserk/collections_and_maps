package com.example.collections_and_maps.ui.benchmark

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.collections_and_maps.R
import com.example.collections_and_maps.databinding.FragmentBenchmarkBinding
import com.example.collections_and_maps.models.benchmarks.ResultItem

class BenchmarkFragment : Fragment(), View.OnClickListener {

    companion object {
        private const val FRAGMENT_TYPE = "fragmentType"

        @JvmStatic
        fun newInstance(namePagerView: Int): BenchmarkFragment {
            val fragmentView = BenchmarkFragment()
            val args = Bundle()
            args.putInt(FRAGMENT_TYPE, namePagerView)
            fragmentView.arguments = args
            return fragmentView
        }
    }

    private val adapter = BenchmarkAdapter()
    private lateinit var model: BenchmarkViewModel
    private var binding: FragmentBenchmarkBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val factory = BenchmarkViewModelFactory(requireArguments().getInt(FRAGMENT_TYPE))
            model = ViewModelProvider(this, factory)[BenchmarkViewModel::class.java]
            model.onCreate()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_benchmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBenchmarkBinding.bind(view)

        val span = model.getSpan()
        val gridLayoutManager = GridLayoutManager(
            this.activity, span, LinearLayoutManager.VERTICAL, false
        )
        gridLayoutManager.spanSizeLookup = RecyclerSizeLookup(span + 1, 1, span)
        val listRecycler = requireView().findViewById<RecyclerView>(R.id.recyclerLayoutItems)
        listRecycler.addItemDecoration(BenchmarkItemDecoration())
        listRecycler.setHasFixedSize(true)
        listRecycler.layoutManager = gridLayoutManager

        model.getItemsLiveData().observe(viewLifecycleOwner) { list: List<ResultItem> ->
            adapter.submitList(list)
        }

        model.getLiveTextTV().observe(viewLifecycleOwner) { buttonText: Int ->
            binding?.calcButton?.setText(buttonText)
        }
        model.getLiveShowerMessages().observe(viewLifecycleOwner) { messageText: Int ->
            binding?.inputField?.error = getText(messageText)
        }
        listRecycler.adapter = adapter
        binding?.calcButton?.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        model.startMeasure(binding?.inputField?.text.toString())
        hideKeypad()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun hideKeypad() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocus = binding?.root?.findFocus()
        currentFocus?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}
