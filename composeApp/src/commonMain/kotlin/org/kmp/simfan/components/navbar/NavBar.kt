//package org.kmp.simfan.components.navbar
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import cafe.adriel.voyager.navigator.LocalNavigator
//import cafe.adriel.voyager.navigator.currentOrThrow
//import org.jetbrains.compose.resources.ExperimentalResourceApi
//import org.jetbrains.compose.resources.painterResource
//import org.kmp.simfan.screen.home.HomeScreen
//import org.kmp.simfan.screen.product.ProductScreen
//import org.kmp.simfan.screen.deposito.DepositoScreen
//import org.kmp.simfan.screen.profile.ProfileScreen
//import simfan.composeapp.generated.resources.Res
//import simfan.composeapp.generated.resources.*
//
//@OptIn(ExperimentalResourceApi::class)
//@Composable
//fun BottomNavbar() {
//    val navigator = LocalNavigator.currentOrThrow
//    val currentKey = navigator.lastItem.key
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(64.dp)
//            .background(Color.White, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        NavBarItem(
//            label = "Home",
//            icon = painterResource(Res.drawable.home),
//            isSelected = currentKey == HomeScreen.key,
//            onClick = { navigator.replaceAll(HomeScreen) }
//        )
//        NavBarItem(
//            label = "Produk",
//            icon = painterResource(Res.drawable.product),
//            isSelected = currentKey == ProductScreen.key,
//            onClick = { navigator.replaceAll(ProductScreen) }
//        )
//        NavBarItem(
//            label = "Deposito",
//            icon = painterResource(Res.drawable.simpananku),
//            isSelected = currentKey == DepositoScreen.key,
//            onClick = { navigator.replaceAll(DepositoScreen) }
//        )
//        NavBarItem(
//            label = "Profil",
//            icon = painterResource(Res.drawable.profile),
//            isSelected = currentKey == ProfileScreen.key,         // ✅ pakai tanpa ()
//            onClick = { navigator.replaceAll(ProfileScreen) }     // ✅ object, bukan class
//        )
//    }
//}
//
//@Composable
//private fun NavBarItem(
//    label: String,
//    icon: androidx.compose.ui.graphics.painter.Painter,
//    isSelected: Boolean,
//    onClick: () -> Unit
//) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier
//            .clickable { onClick() }
//            .padding(8.dp)
//    ) {
//        Icon(
//            painter = icon,
//            contentDescription = label,
//            tint = if (isSelected) Color.Blue else Color.Gray,
//            modifier = Modifier.size(24.dp)
//        )
//        Text(
//            text = label,
//            fontSize = 12.sp,
//            color = if (isSelected) Color.Blue else Color.Gray
//        )
//    }
//}
