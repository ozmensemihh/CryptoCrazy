package com.semihozmen.cryptocrazy.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.semihozmen.cryptocrazy.viewmodel.CryptoListViewModel

@Composable
fun CryptoListScreen(
    navController: NavController,
    viewModel : CryptoListViewModel = hiltViewModel()
){

    Surface(
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier.fillMaxSize()
    ) {
        
        Column {
            Text(text = "Crypto Crazy", modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary)
            
            Spacer(modifier = Modifier.height(10.dp))
            // SesarchBar
            SearchBar(modifier = Modifier.fillMaxWidth().padding(16.dp),"Search ...."){
                viewModel.searchCrypto(it)
            }
            Spacer(modifier = Modifier.height(10.dp))
            // List


                }
        }

}
@Composable
fun SearchBar(
    modifier: Modifier,
    hint:String="",
    search :(String) -> Unit = {}
){

    var text by remember {
        mutableStateOf("")
    }

    var isHintState by remember {
        mutableStateOf(hint!="")
    }
    
    Box(modifier =  Modifier) {

        BasicTextField(value = text, onValueChange = {
            text = it
            search(it) },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintState = it.isFocused != true && text.isEmpty()
                }
        )

        if(isHintState){
            Text(text = hint,color = Color.LightGray,modifier= Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
        }


    }




}



