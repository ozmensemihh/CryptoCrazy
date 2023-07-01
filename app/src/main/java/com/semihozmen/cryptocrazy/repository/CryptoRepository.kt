package com.semihozmen.cryptocrazy.repository

import com.semihozmen.cryptocrazy.model.Crypto
import com.semihozmen.cryptocrazy.model.CryptoModelList
import com.semihozmen.cryptocrazy.service.CryptoAPI
import com.semihozmen.cryptocrazy.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    val cryptoAPI: CryptoAPI
)  {

    suspend fun getCryptoList():Resource<CryptoModelList>{
        val response = try{
            cryptoAPI.getCryptoAll()
        }catch (e:Exception){
            return Resource.Error(e.localizedMessage)
        }

        return Resource.Success(response)
    }


    suspend fun getCrypto():Resource<Crypto>{
        val response = try {
            cryptoAPI.getCryptoById()
        }catch (e:Exception){
            return Resource.Error(e.localizedMessage)
        }

        return Resource.Success(response)
    }

}