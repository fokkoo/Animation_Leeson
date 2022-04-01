package com.example.a1lesson5

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewSampleFragment : Fragment() {

    private val viewModel by viewModels<RecyclerViewSampleViewModel>()

    private lateinit var recyclerView: RecyclerView

    private val adapter by lazy{
        SampleAdapter(
            onPlanetClickListner = {planet->viewModel.onPlanetClick(planet)},
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view_sample, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view_sample)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        observeViewModel()
        viewModel.loadData
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeViewModel() {
        viewModel.getItems().observe(viewLifecycleOwner) { items ->
            adapter.items = items
            adapter.notifyDataSetChanged()

//            val sampleDiffUtil = SampleDiffUtil(
//                oldList = adapter.items,
//                newList = items,
//            )
//            val sampleDiffResult = DiffUtil.calculateDiff(sampleDiffUtil)
//            adapter.items = items
//            sampleDiffResult.dispatchUpdatesTo(adapter)
        }

        viewModel.getMessages().observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }


}