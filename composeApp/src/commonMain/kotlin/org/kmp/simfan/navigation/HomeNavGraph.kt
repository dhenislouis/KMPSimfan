package org.kmp.simfan.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.kmp.simfan.Routes
import org.kmp.simfan.screen.home.*
import org.kmp.simfan.screen.home.notification.NotificationScreen
import org.kmp.simfan.screen.home.promo.PromoScreen

fun NavGraphBuilder.homeGraph(navController: NavController) {
    composable<Routes.Home> {
        HomeScreen(
            navController = navController,
            currentRoute = Routes.Home,
            onScreenNotification = { navController.navigate(Routes.Notification) },
            onScreenPromo = { navController.navigate(Routes.Promo) }
        )
    }
    composable<Routes.Notification> {
        NotificationScreen(
            navController = navController,
            currentRoute = Routes.Notification,
            onCloseClick = { navController.popBackStack() }
        )
    }
    composable<Routes.Promo> {
        PromoScreen(
            navController = navController,
            currentRoute = Routes.Home,
            onBackClick = { navController.popBackStack() },
            onMenuClick = { /* Handle menu click */ }
        )
    }
}