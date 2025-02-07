package com.itis.profitx.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itis.impl.presentation.ui.AuthorizationScreen
import com.itis.impl.presentation.ui.RegistrationScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { AuthorizationScreen(navController) }
        composable("registration") { RegistrationScreen(navController) }
        composable("main")  {   MainScreen()  }
    }
}