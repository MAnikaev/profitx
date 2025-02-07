package com.itis.profitx.navigation

import AnalysisScreen
import IncomeScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itis.impl.presentation.ui.AddExpenseScreen
import com.itis.impl.presentation.ui.AddIncomeScreen
import com.itis.impl.presentation.ui.ExpensesScreen
import com.itis.impl.presentation.ui.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController = navController, startDestination = "income") {
                composable("income") { IncomeScreen(navController) }
                composable("expenses") { ExpensesScreen(navController) }
                composable("analysis") { AnalysisScreen() }
                composable("profile") { ProfileScreen(navController) }
                composable("addIncome") { AddIncomeScreen(navController) }
                composable("addExpense") { AddExpenseScreen(navController) }
            }
        }
    }
}
