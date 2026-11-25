package com.ashraful.autopilot.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ashraful.autopilot.ui.screens.AccountsScreen
import com.ashraful.autopilot.ui.screens.DashboardScreen
import com.ashraful.autopilot.ui.screens.SchedulerScreen
import com.ashraful.autopilot.ui.screens.SettingsScreen

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    data object Dashboard : BottomNavItem("dashboard", "Dashboard", Icons.Filled.Dashboard)
    data object Accounts : BottomNavItem("accounts", "Accounts", Icons.Filled.Person)
    data object Scheduler : BottomNavItem("scheduler", "Scheduler", Icons.Filled.Schedule)
    data object Settings : BottomNavItem("settings", "Settings", Icons.Filled.Settings)
}

val bottomNavItems = listOf(
    BottomNavItem.Dashboard,
    BottomNavItem.Accounts,
    BottomNavItem.Scheduler,
    BottomNavItem.Settings
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Dashboard.route) { DashboardScreen() }
            composable(BottomNavItem.Accounts.route) { AccountsScreen() }
            composable(BottomNavItem.Scheduler.route) { SchedulerScreen() }
            composable(BottomNavItem.Settings.route) { SettingsScreen() }
        }
    }
}
