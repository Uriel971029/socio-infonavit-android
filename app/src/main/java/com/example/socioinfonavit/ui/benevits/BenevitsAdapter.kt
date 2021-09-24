package com.example.socioinfonavit.ui.benevits

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.socioinfonavit.databinding.BenevitsAdapterBinding
import com.example.socioinfonavit.network.Benevit

class BenevitsAdapter()  : ListAdapter<Benevit, BenevitsAdapter.BenevitsViewHolder>(DiffObject) {

    object DiffObject: DiffUtil.ItemCallback<Benevit>() {
        override fun areItemsTheSame(oldItem: Benevit, newItem: Benevit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Benevit, newItem: Benevit): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenevitsViewHolder {
        var binding = BenevitsAdapterBinding.inflate(LayoutInflater.from(parent.context))
        return BenevitsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BenevitsViewHolder, position: Int) {
        var benevit = getItem(position)
        holder.descuento.text = benevit.title
        holder.descripcion.text = benevit.description
        holder.message.text = benevit.ally.description
        holder.walletName.text = benevit.wallet.name
    }

    class BenevitsViewHolder (binding: BenevitsAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        val descuento = binding.txtDescuento
        val descripcion = binding.txtDescription
        val message = binding.txtMessage
        val walletName = binding.txtNameWallet
    }
}