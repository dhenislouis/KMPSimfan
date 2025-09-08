package org.kmp.simfan.core.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.core.Primary
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.deposito_active
import simfan.composeapp.generated.resources.home
import simfan.composeapp.generated.resources.home_svg
import simfan.composeapp.generated.resources.home_active
import simfan.composeapp.generated.resources.label_deposito
import simfan.composeapp.generated.resources.product
import simfan.composeapp.generated.resources.product_active
import simfan.composeapp.generated.resources.profile
import simfan.composeapp.generated.resources.profile_active

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
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 4.dp
    ) {
        // --- HOME ---
        val homeSelected = currentRoute == Routes.Home
        val homeTint = animateColorAsState(
            targetValue = if (homeSelected) Color.White else Color.Black,
            label = "homeTintAnim"
        ).value
        NavigationBarItem(
            selected = homeSelected,
            onClick = { onNavigate(Routes.Home) },
            icon = {
                AnimatedContent(targetState = homeSelected, label = "homeIconAnim") { selected ->
                    AsyncImage(
                        model = Res.getUri("files/ic_house.svg"),
                        contentDescription = "House",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(homeTint)
                    )
                }
            },
            label = {
                AnimatedContent(targetState = homeSelected, label = "homeLabelAnim") { selected ->
                    Text("Home", color = if (selected) Primary else Color.Black,
                        style = MaterialTheme.typography.labelMedium)
                }
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color(0XFF668CFF)
            ),
            alwaysShowLabel = true
        )

        // --- PRODUCT ---
        val productSelected = currentRoute == Routes.Product
        val productTint = animateColorAsState(
            targetValue = if (productSelected) Color.White else Color.Black,
            label = "productTintAnim"
        ).value
        NavigationBarItem(
            selected = productSelected,
            onClick = { onNavigate(Routes.Product) },
            icon = {
                AnimatedContent(targetState = homeSelected, label = "homeIconAnim") { selected ->
                    AsyncImage(
                        model = Res.getUri("files/ic_vector.svg"),
                        contentDescription = "House",
                        modifier = Modifier.size(24.dp),
                            colorFilter = ColorFilter.tint(productTint)
                    )
                }
            },
            label = {
                AnimatedContent(targetState = productSelected, label = "productLabelAnim") { selected ->
                    Text("Product", color = if (selected) Primary else Color.Black,style = MaterialTheme.typography.labelMedium)
                }
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color(0XFF668CFF)
            ),
            alwaysShowLabel = true
        )

        // --- SIMFANKU ---
        val simfankuSelected = currentRoute == Routes.SimfankuDeposito || currentRoute == Routes.SimfankuTabungan
        val simfankuTint = animateColorAsState(
            targetValue = if (simfankuSelected) Color.White else Color.Black,
            label = "simfankuTintAnim"
        ).value
        NavigationBarItem(
            selected = simfankuSelected,
            onClick = { onNavigate(Routes.Simfanku) },
            icon = {
                AnimatedContent(targetState = simfankuSelected, label = "simfankuIconAnim") { selected ->
                    AsyncImage(
                        model = Res.getUri("files/ic_simfanku.svg"),
                        contentDescription = "House",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(simfankuTint)
                    )
                }
            },
            label = {
                AnimatedContent(targetState = simfankuSelected, label = "simfankuLabelAnim") { selected ->
                    Text("Simfanku", color = if (selected) Primary else Color.Black,style = MaterialTheme.typography.labelMedium)
                }
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color(0XFF668CFF)
            ),
            alwaysShowLabel = true
        )

        // --- PROFILE ---
        val profileSelected = currentRoute == Routes.Profile
        val profileTint = animateColorAsState(
            targetValue = if (profileSelected) Color.White else Color.Black,
            label = "profileTintAnim"
        ).value
        NavigationBarItem(
            selected = profileSelected,
            onClick = { onNavigate(Routes.Profile) },
            icon = {
                AnimatedContent(targetState = homeSelected, label = "homeIconAnim") { selected ->
                    AsyncImage(
                        model = Res.getUri("files/ic_user.svg"),
                        contentDescription = "House",
                        modifier = Modifier.size(24.dp),
                        colorFilter = ColorFilter.tint(profileTint)
                    )
                }
            },
            label = {
                AnimatedContent(targetState = profileSelected, label = "profileLabelAnim") { selected ->
                    Text("Profile", color = if (selected) Primary else Color.Black,style = MaterialTheme.typography.labelMedium)
                }
            },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = Color(0XFF668CFF)
            ),
            alwaysShowLabel = true
        )
    }
}
