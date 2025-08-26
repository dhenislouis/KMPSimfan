package org.kmp.simfan.core.navigation

import org.jetbrains.compose.resources.DrawableResource
import org.kmp.simfan.Routes

data class NavigationItem(
    val title: String,
    val icon: DrawableResource,
    val selectedIcon: DrawableResource,
    val screen: Routes,
)