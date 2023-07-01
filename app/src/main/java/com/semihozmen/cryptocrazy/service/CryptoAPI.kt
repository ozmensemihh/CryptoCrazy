package com.semihozmen.cryptocrazy.service

import com.semihozmen.cryptocrazy.model.Crypto
import com.semihozmen.cryptocrazy.model.CryptoModelList
import retrofit2.http.GET

interface CryptoAPI {

    @GET("atilsamancioglu/IA32-CryptoComposeData/main/cryptolist.json")
    suspend fun getCryptoAll():CryptoModelList

    @GET("atilsamancioglu/IA32-CryptoComposeData/main/crypto.json")
    suspend fun getCryptoById():Crypto


}