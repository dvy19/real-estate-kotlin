package com.example.realestate

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.realestate.ml.HousePricePredictionScreen
import com.example.realestate.screens.BrokerDetailsScreen
import com.example.realestate.screens.HomeScreen
import com.example.realestate.screens.RagChatScreen


@Composable
fun MainScreen(
    rootNavController: NavController,
    innerPadding: PaddingValues
) {
    val mainNavController = rememberNavController()

    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route



    Scaffold(
        bottomBar = {
            BottomNav(mainNavController)
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = mainNavController,
            startDestination = Screens.HomeScreen.route
        ) {

            composable(Screens.HomeScreen.route) {
                HomeScreen(mainNavController)
            }

            composable(Screens.HousePricePredictionScreen.route) {
                HousePricePredictionScreen(mainNavController)
            }

            composable(Screens.RagChatScreen.route) {
                RagChatScreen(mainNavController)
            }

            /*
            composable<Screens.BrokerDetailsScreen> { backStackEntry ->
                // Extract the data class passed via navigation
                val args = backStackEntry.toRoute<Screens.BrokerDetailsScreen>()

                // Pass the brokerId to your Screen Composable
                BrokerDetailsScreenContent(
                    brokerId = args.brokerId,
                    onBackClicked = { navController.popBackStack() }
                )
            }

             */

            composable(Screens.BrokerDetailsScreen.route){ backStackEntry ->
                val brokerId = backStackEntry.arguments?.getString("id")

                if (brokerId != null) {
                    BrokerDetailsScreen(
                        id = brokerId,
                        mainNavController = mainNavController
                    )
                }

            }


        }
    }
}
