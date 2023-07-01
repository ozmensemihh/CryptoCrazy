package com.semihozmen.cryptocrazy.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semihozmen.cryptocrazy.model.CryptoModelItem
import com.semihozmen.cryptocrazy.repository.CryptoRepository
import com.semihozmen.cryptocrazy.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CryptoListViewModel @Inject constructor(
   private val repository: CryptoRepository
) :ViewModel(){

    var cryptoList = mutableStateOf<List<CryptoModelItem>>(listOf())
    var errorMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initCryptoList = listOf<CryptoModelItem>()
    private var isStartingSearch = true

    fun searchCrypto(query:String){
         val listToSearch = if(isStartingSearch){
             cryptoList.value
         }else{
             initCryptoList
         }

        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                cryptoList.value = initCryptoList
                isStartingSearch = true
                return@launch
            }

            val results = listToSearch.filter {
                it.currency.contains(query.trim(),true)
            }

            if(isStartingSearch){
                initCryptoList = cryptoList.value
                isStartingSearch = false
            }

            cryptoList.value = results
        }

    }

    init {
        loadCrryptos()
    }

    fun loadCrryptos(){
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getCryptoList()

            when(result){
                is Resource.Success ->{
                    val items = result.data!!.mapIndexed { index, cryptoModelItem ->
                        CryptoModelItem(cryptoModelItem.currency,cryptoModelItem.price)
                    } as List<CryptoModelItem>

                    errorMessage.value =""
                    isLoading.value = false
                    cryptoList.value += items
                }

                is Resource.Error ->{
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }

                else -> {}
            }
        }



    }

}