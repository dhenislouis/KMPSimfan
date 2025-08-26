package org.kmp.simfan.core.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Primary
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.home
import simfan.composeapp.generated.resources.label_deposito
import simfan.composeapp.generated.resources.product
import simfan.composeapp.generated.resources.profile
val bottomBarRoutes = listOf(
    Routes.Home,
    Routes.Product,
    Routes.Simfanku,
    Routes.Profile
)

@Composable
fun BottomBar(
    currentRoute: Routes?,
    onNavigate: (Routes) -> Unit
) {
    NavigationBar(  containerColor = Color.White,
        tonalElevation = 4.dp) {
        NavigationBarItem(
            selected = currentRoute == Routes.Home,
            onClick = { onNavigate(Routes.Home) },
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier.size(18.dp)

                )
            },
            label = { Text("Home") }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.Product,
            onClick = { onNavigate(Routes.Product) },
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.product),
                    contentDescription = "Product",
                    modifier = Modifier.size(18.dp)
                )
            },
            label = { Text("Product") }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.Simfanku,
            onClick = { onNavigate(Routes.Simfanku) },
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.label_deposito),
                    contentDescription = "Simfanku",
                    modifier = Modifier.size(18.dp)
                )
            },
            label = { Text("Simfanku") }
        )

        NavigationBarItem(
            selected = currentRoute == Routes.Profile,
            onClick = { onNavigate(Routes.Profile) },
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier.size(18.dp)
                )
            },
            label = { Text("Profile") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Primary
            )
        )
    }
}