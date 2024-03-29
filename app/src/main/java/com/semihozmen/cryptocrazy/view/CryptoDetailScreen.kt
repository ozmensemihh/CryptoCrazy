package com.semihozmen.cryptocrazy.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.semihozmen.cryptocrazy.model.Crypto
import com.semihozmen.cryptocrazy.util.Resource
import com.semihozmen.cryptocrazy.viewmodel.CryptoDetailViewModel


@Composable
fun CryptoDetailScreen(
    id:String,
    price:String,
    navController: NavController,
    viewModel:CryptoDetailViewModel = hiltViewModel()
){
    
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Cyan),
    contentAlignment = Alignment.Center) {

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(Color.White)) {

            /*
            var cryptoItem by remember {
                mutableStateOf<Resource<Crypto>>(Resource.Loading())
            }

            LaunchedEffect(key1 =Unit ){
                cryptoItem = viewModel.getCrypto()
            }

             */

            val cryptoItem by produceState<Resource<Crypto>>(initialValue = Resource.Loading()){
                value = viewModel.getCrypto()
            }


            Box(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.Center){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    when(cryptoItem){
                        is Resource.Success ->{
                            val selectedCrypto = cryptoItem.data!![0]

                            Text(text = selectedCrypto.name,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 40.sp)

                            Image(painter = rememberImagePainter(data = selectedCrypto.logo_url), contentDescription = selectedCrypto.name,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(200.dp, 200.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.Gray, CircleShape))

                            Text(text = selectedCrypto.name,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center)

                        }
                        is Resource.Error ->{
                            Text(text = cryptoItem.message!!)
                        }
                        is Resource.Loading ->{
                            CircularProgressIndicator(color = Color.Cyan)
                        }
                    }

                }
            }

        }
    }

}