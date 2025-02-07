package com.itis.profitx.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(backgroundColor = Color(0xFFDCEDC8)) {
        BottomNavigationItem(
            icon = { Icon(Icons.Default.AttachMoney, contentDescription = "Income") },
            selected = false,
            onClick = { navController.navigate("income") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Expenses") },
            selected = false,
            onClick = { navController.navigate("expenses") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.BarChart, contentDescription = "Analysis") },
            selected = false,
            onClick = { navController.navigate("analysis") }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            selected = true,
            onClick = { navController.navigate("profile") }
        )
    }
}