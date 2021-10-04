package com.example.socioinfonavit.ui.benevits

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socioinfonavit.network.ApiService
import com.example.socioinfonavit.network.Benevit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class BenevitsViewModel : ViewModel() {

    private var _benevitsByWallet = MutableLiveData<HashMap<String, List<Benevit>>>()
    val benevitsByWallet : LiveData<HashMap<String, List<Benevit>>> get() = _benevitsByWallet

    init {
        fetchAllBenevits()
    }

    private fun fetchAllBenevits(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val listWallets = ApiService.getMethods?.getAllWallets()
                    val benevits = ApiService.getMethods?.getAllBenevits()
                    val finalList = ArrayList<Benevit>()
                    val benevitsByWalletAux = hashMapOf<String, List<Benevit>>()

                    benevits?.unlocked?.forEach {
                        it.isLocked = false
                        finalList.add(it)
                    }

                    benevits?.locked?.forEach {
                        it.isLocked = true
                        finalList.add(it)
                    }

                    listWallets?.forEach { wallet ->
                        benevitsByWalletAux[wallet.name] = finalList.filter { it.wallet.name == wallet.name }
                    }

                    _benevitsByWallet.postValue(benevitsByWalletAux)

                } catch (ex: Exception) {
                    Log.d("error_wallets_benevits", ex.message.toString())
                }
            }
        }
    }
}