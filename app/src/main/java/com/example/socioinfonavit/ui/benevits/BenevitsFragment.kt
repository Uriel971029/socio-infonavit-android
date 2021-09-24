package com.example.socioinfonavit.ui.benevits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.socioinfonavit.R
import com.example.socioinfonavit.databinding.FragmentBenevitsBinding

class BenevitsFragment : Fragment() {

    private lateinit var benevitsViewModel: BenevitsViewModel
    private lateinit var binding: FragmentBenevitsBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        benevitsViewModel = ViewModelProvider(this).get(BenevitsViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_benevits, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = benevitsViewModel
        binding.lifecycleOwner = this

        val benevitsAdapter = BenevitsAdapter()
        binding.rvBenevits.apply {
            adapter = benevitsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        benevitsViewModel.benevits.observe(viewLifecycleOwner, {
            benevitsAdapter.submitList(it)
        })
    }
}