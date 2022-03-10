    package com.example.socioinfonavit.ui.benevits

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.socioinfonavit.databinding.LockedBenevitsAdapterBinding
import com.example.socioinfonavit.databinding.UnlockedBenevitsAdapterBinding
import com.example.socioinfonavit.data.local.Benevit
import com.example.socioinfonavit.utils.bindBackground
import com.example.socioinfonavit.utils.bindLogo
import java.lang.Exception

    class BenevitsAdapter()  : ListAdapter<Benevit, RecyclerView.ViewHolder>(DiffObject) {

    private val LOCKED_BENEVIT = 1
    private val UNLOCKED_BENEVIT = 2

    object DiffObject: DiffUtil.ItemCallback<Benevit>() {
        override fun areItemsTheSame(oldItem: Benevit, newItem: Benevit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Benevit, newItem: Benevit): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == LOCKED_BENEVIT){
            val binding = LockedBenevitsAdapterBinding.inflate(LayoutInflater.from(parent.context))
            LockedBenevitsViewHolder(binding)
        }else{
            val binding = UnlockedBenevitsAdapterBinding.inflate(LayoutInflater.from(parent.context))
            UnlockedBenevitsiewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var benevit = getItem(position)

        try {
            if(holder.itemViewType == LOCKED_BENEVIT){
                val lockHolder = holder as LockedBenevitsViewHolder
                lockHolder.logo.bindLogo(benevit.vector_full_path)
            } else {
                val unlockHolder = holder as UnlockedBenevitsiewHolder
                unlockHolder.descuento.text = benevit.title
                unlockHolder.descripcion.text = benevit.description
                unlockHolder.message.text = benevit.ally.description
                unlockHolder.logo.bindLogo(benevit.ally.mini_logo_full_path)
                unlockHolder.header.bindBackground(benevit.primary_color)
            }
        }catch (ex : Exception){
            Log.d("error_benevit_adapter", ex.message.toString())
        }
    }

    override fun getItemViewType(position: Int): Int {
        var benevit = getItem(position)
        return if(benevit.isLocked)
            LOCKED_BENEVIT
        else
            UNLOCKED_BENEVIT
    }

    class UnlockedBenevitsiewHolder (binding: UnlockedBenevitsAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        val descuento = binding.txtDescuento
        val descripcion = binding.txtDescription
        val message = binding.txtMessage
        val logo = binding.logo
        val header = binding.flHeader
    }

    class LockedBenevitsViewHolder (binding: LockedBenevitsAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
        val logo = binding.logo
    }
}