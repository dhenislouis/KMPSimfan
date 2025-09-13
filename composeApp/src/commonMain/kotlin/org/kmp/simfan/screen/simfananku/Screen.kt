package org.kmp.simfan.screen.simfananku

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.kmp.simfan.Routes
import org.kmp.simfan.components.CustomTabRow
import org.kmp.simfan.core.Button1
import org.kmp.simfan.core.navigation.BottomBar



private val BgSecondary = Color(0xFFF1F2F6)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SimfankuScreen(
    navController: NavController,
    currentRoute: Routes?,
    onDetailDepositoSimfanku: () -> Unit = {},
    onDetailTabunganSimfanku: () -> Unit = {},

    ) {
    val pagerState = rememberPagerState(initialPage = 0) { 2 } // 2 tab: Deposito, Tabungan
    val coroutineScope = rememberCoroutineScope()
    val tabTitles = listOf("Deposito", "Tabungan")

    Scaffold(
        bottomBar = {
            BottomBar(
                currentRoute = currentRoute,
                onNavigate = { navController.navigate(it) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BgSecondary)
                .padding(paddingValues)
        ) {
            // TopBar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Simfanku",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // TabRow
            CustomTabRow(
                selectedTab = pagerState.currentPage,
                onTabSelected = { index ->
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )


            HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
                when (page) {
                    0 -> {
                        // Deposito
                        SimfankuDepositoScreen(onDetailDepositoSimfanku)
                    }
                    1 -> {
                        // Tabungan
                        SimfankuTabunganScreen(onDetailTabunganSimfanku)
                    }
                }
            }
        }
    }
}