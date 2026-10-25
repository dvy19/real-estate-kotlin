package com.example.realestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.realestate.ui.theme.RealEstateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RealEstateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   RootNav(innerPadding)
                }
            }
        }
    }
}


sealed class Screens(val route:String){

    data object Register:Screens("register")
    data object Login:Screens("login")
    data object HomeScreen:Screens("home")
    data object ProfileScreen:Screens("profile")
    data object HousePricePredictionScreen:Screens("house_price_prediction")
    data object RagChatScreen:Screens("rag_chat")




}