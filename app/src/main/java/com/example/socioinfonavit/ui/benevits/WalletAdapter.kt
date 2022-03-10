package com.example.socioinfonavit.ui.benevits

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.socioinfonavit.databinding.WalletAdapterBinding
import com.example.socioinfonavit.data.local.Benevit

class WalletAdapter : ListAdapter<List<Benevit>, WalletAdapter.WalletViewHolder>(diffObject) {

    object diffObject : DiffUtil.ItemCallback<List<Benevit>>(){
        override fun areItemsTheSame(oldItem: List<Benevit>, newItem: List<Benevit>): Boolean {
            return oldItem.size == newItem.size
        }

        override fun areContentsTheSame(oldItem: List<Benevit>, newItem: List<Benevit>): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val binding = WalletAdapterBinding.inflate(LayoutInflater.from(parent.context))
        return WalletViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {

        try {

            val wallet = getItem(position)
            val benevitsAdapter = BenevitsAdapter()

            holder.benevitsRecyler.apply {
                adapter = benevitsAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            }

            if(wallet[0].wallet.name.equals("Negocio") || wallet[0].wallet.name.equals("Parque verde")){
                Log.d("detalle", wallet.toString())
            }

            holder.walletName.text = wallet[0].wallet.name
            benevitsAdapter.submitList(wallet)
        }catch (ex : Exception) {
            Log.d("error", ex.message.toString());
        }
    }


    class WalletViewHolder(binding : WalletAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        val walletName = binding.txtWallet
        val benevitsRecyler = binding.rvWallet
    }
}