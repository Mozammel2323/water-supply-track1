package com.waterbabu.watertrack.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.BarChart
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.waterbabu.watertrack.ui.screens.dashboard.DashboardScreen
import com.waterbabu.watertrack.ui.screens.customers.CustomersScreen
import com.waterbabu.watertrack.ui.screens.delivery.DeliveryScreen
import com.waterbabu.watertrack.ui.screens.payment.PaymentScreen
import com.waterbabu.watertrack.ui.screens.reports.ReportsScreen

@Composable
fun WaterTrackApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val items = listOf(
                    BottomNavItem.Dashboard,
                    BottomNavItem.Customers,
                    BottomNavItem.Delivery,
                    BottomNavItem.Payment,
                    BottomNavItem.Reports
                )

                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                lazyRestoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = androidx.compose.foundation.layout.Modifier.padding(paddingValues)
        ) {
            composable("dashboard") {
                DashboardScreen(navController)
            }
            composable("customers") {
                CustomersScreen(navController)
            }
            composable("delivery") {
                DeliveryScreen(navController)
            }
            composable("payment") {
                PaymentScreen(navController)
            }
            composable("reports") {
                ReportsScreen(navController)
            }
        }
    }
}

sealed class BottomNavItem(val route: String, val label: String, val icon: androidx.compose.material.icons.materialIcon) {
    object Dashboard : BottomNavItem("dashboard", "Dashboard", Icons.Filled.Dashboard)
    object Customers : BottomNavItem("customers", "Customers", Icons.Filled.People)
    object Delivery : BottomNavItem("delivery", "Delivery", Icons.Filled.LocalShipping)
    object Payment : BottomNavItem("payment", "Payment", Icons.Filled.Payment)
    object Reports : BottomNavItem("reports", "Reports", Icons.Filled.BarChart)
}
