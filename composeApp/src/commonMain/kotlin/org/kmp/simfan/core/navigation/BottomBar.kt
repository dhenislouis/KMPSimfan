package org.kmp.simfan.core.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Primary
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.deposito_active
import simfan.composeapp.generated.resources.home
import simfan.composeapp.generated.resources.home_active
import simfan.composeapp.generated.resources.label_deposito
import simfan.composeapp.generated.resources.product
import simfan.composeapp.generated.resources.profile
import simfan.composeapp.generated.resources.profile_active
import simfan.composeapp.generated.resources.simfanku
import simfan.composeapp.generated.resources.simfanku_active

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
                    painter = painterResource(  if ( currentRoute == Routes.Home) Res.drawable.home_active else Res.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier.size(18.dp),
                    tint = Color.White

                )
            },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Primary,

                )
        )

        NavigationBarItem(
            selected = currentRoute == Routes.Product,
            onClick = { onNavigate(Routes.Product) },
            icon = {
                Icon(
                    painter = painterResource(  if ( currentRoute == Routes.Home) Res.drawable.profile_active else Res.drawable.product),

                    contentDescription = "Product",
                    modifier = Modifier.size(18.dp)
                )
            },
            label = { Text("Product") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Primary,

                )
        )

        NavigationBarItem(
            selected = currentRoute == Routes.Simfanku,
            onClick = { onNavigate(Routes.Simfanku) },
            icon = {
                Icon(
                    painter = painterResource(  if ( currentRoute == Routes.Home) Res.drawable.label_deposito else Res.drawable.deposito_active),

                    contentDescription = "Simfanku",
                    modifier = Modifier.size(18.dp)
                )
            },
            label = { Text("Simfanku") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Primary,

                )
        )

        NavigationBarItem(
            selected = currentRoute == Routes.Profile,
            onClick = { onNavigate(Routes.Profile) },
            icon = {
                Icon(
                    painter = painterResource(  if ( currentRoute == Routes.Home) Res.drawable.profile_active else Res.drawable.profile),

                    contentDescription = "Profile",
                    modifier = Modifier.size(18.dp)
                )
            },
            label = { Text("Profile") },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Primary,

            )
        )
    }
}