package com.semihozmen.cryptocrazy.viewmodel

import androidx.lifecycle.ViewModel
import com.semihozmen.cryptocrazy.model.Crypto
import com.semihozmen.cryptocrazy.model.CryptoModelItem
import com.semihozmen.cryptocrazy.repository.CryptoRepository
import com.semihozmen.cryptocrazy.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
   private val repository: CryptoRepository
): ViewModel() {

   suspend fun getCrypto():Resource<Crypto>{
      return repository.getCrypto()
   }
}