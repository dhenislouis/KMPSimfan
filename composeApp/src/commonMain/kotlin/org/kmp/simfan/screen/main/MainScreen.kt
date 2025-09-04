//package org.kmp.simfan.screen.main
//
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import cafe.adriel.voyager.core.screen.Screen
//import cafe.adriel.voyager.navigator.CurrentScreen
//import cafe.adriel.voyager.navigator.Navigator
//import org.kmp.simfan.components.navbar.BottomNavbar
//import org.kmp.simfan.screen.home.HomeScreen
//
//object MainScreen : Screen {
//    @Composable
//    override fun Content() {
//        Navigator(HomeScreen) { navigator ->
//            Scaffold(
//                bottomBar = { BottomNavbar() }
//            ) { innerPadding ->
//                Box(modifier = Modifier.padding(innerPadding)) {
//                    CurrentScreen()
//                }
//            }
//        }
//    }
//}
