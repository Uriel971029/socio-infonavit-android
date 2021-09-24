package com.example.socioinfonavit.ui.benevits

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socioinfonavit.network.ApiService
import com.example.socioinfonavit.network.Benevit
import com.example.socioinfonavit.network.Wallet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BenevitsViewModel : ViewModel() {

    private var _benevits = MutableLiveData<List<Benevit>>()
    val benevits : LiveData<List<Benevit>> get() = _benevits

    init {
        fetchAllBenevits()
    }

    fun fetchAllBenevits(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val listWallets = ApiService.getMethods?.getAllWallets()
                    val benevitsWallets = ApiService.getMethods?.getAllBenevits()?.locked
                    _benevits.postValue(benevitsWallets)

                } catch (ex: Exception) {
                    Log.d("error_wallets_benevits", ex.message.toString())
                }
            }
        }
    }

}