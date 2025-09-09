package org.kmp.simfan.screen.product

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
import org.kmp.simfan.core.Button1
import org.kmp.simfan.core.navigation.BottomBar


private val BgSecondary = Color(0xFFF1F2F6)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductScreen(
    navController: NavController,
    currentRoute: Routes?,
    onFilterClick: () -> Unit = {},
    onDetailBpr: () -> Unit = {},
    onDetailDepositoClick: () -> Unit = {},
    onDetailTabunganClick: () -> Unit = {}
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
                    text = "Produk",
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
//            TabRow(
//                selectedTabIndex = pagerState.currentPage,
//                containerColor = BgSecondary
//            ) {
//                tabTitles.forEachIndexed { index, title ->
//                    Tab(
//                        text = { Text(title) },
//                        selected = pagerState.currentPage == index,
//                        onClick = {
//                            coroutineScope.launch {
//                                pagerState.animateScrollToPage(index)
//                            }
//                        }
//                    )
//                }
//            }

            // Pager untuk konten
            HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
                when (page) {
                    0 -> {
                        // Deposito
                        ProductDepositoScreen(
                            onFilterClick = onFilterClick,
                            AjukanPenempatan = onDetailDepositoClick
                        )
                    }
                    1 -> {
                        // Tabungan
                        ProductTabunganScreen(
                            onFilterClick = onFilterClick,
                            onDetailBprClick = onDetailBpr,
                            onDetailClick = onDetailTabunganClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomTabRow(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BgSecondary)
            .padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Tab Deposito
        Button(
            onClick = { onTabSelected(0) },
            modifier = Modifier
                .weight(1f)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedTab == 0) Button1 else Color.Transparent,
                contentColor = if (selectedTab == 0) Color.White else Button1
            ),
            border = BorderStroke(1.5.dp, Button1),
            shape = RoundedCornerShape(50)
        ) {
            Text("Deposito", fontSize = 12.sp)
        }

        // Tab Tabungan
        Button(
            onClick = { onTabSelected(1) },
            modifier = Modifier
                .weight(1f)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedTab == 1) Button1 else Color.Transparent,
                contentColor = if (selectedTab == 1) Color.White else Button1
            ),
            border = BorderStroke(1.5.dp, Button1),
            shape = RoundedCornerShape(50)
        ) {
            Text("Tabungan", fontSize = 12.sp)
        }
    }
}