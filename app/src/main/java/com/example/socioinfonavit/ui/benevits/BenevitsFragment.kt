package com.example.socioinfonavit.ui.benevits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.socioinfonavit.R
import com.example.socioinfonavit.databinding.FragmentBenevitsBinding
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton

class BenevitsFragment : Fragment() {

    private lateinit var benevitsViewModel: BenevitsViewModel
    private lateinit var binding: FragmentBenevitsBinding
    private lateinit var skeleton: Skeleton

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        benevitsViewModel = ViewModelProvider(this).get(BenevitsViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_benevits, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var skeleton : Skeleton
        binding.vm = benevitsViewModel
        binding.lifecycleOwner = this
        var walletAdapter = WalletAdapter()

        binding.rvBenevits.apply {
            adapter = walletAdapter
            layoutManager = LinearLayoutManager(requireContext())
            skeleton = applySkeleton(R.layout.unlocked_benevits_adapter, 6)
        }
        skeleton.showSkeleton()
        benevitsViewModel.benevitsByWallet.observe(viewLifecycleOwner, { wallets ->
            walletAdapter.submitList(wallets.values.toList())
            skeleton.showOriginal()
        })
    }
}