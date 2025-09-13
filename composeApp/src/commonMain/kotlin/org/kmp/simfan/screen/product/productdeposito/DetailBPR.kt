package org.kmp.simfan.screen.product.productdeposito

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.jetbrains.compose.resources.painterResource
import org.kmp.simfan.Routes
import org.kmp.simfan.extension.toRupiah
import org.kmp.simfan.presentation.product.DetailProductViewModel
import simfan.composeapp.generated.resources.Res
import simfan.composeapp.generated.resources.arrow_back
import simfan.composeapp.generated.resources.simfan_websuite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBPRProductDeposito(
    navController: NavController,
    currentRoute: Routes.DetailProductDeposito,
    onBackClick: () -> Unit,
    viewModel: DetailProductViewModel = viewModel()
) {
    val bpr by viewModel.bpr
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            // Show error message (e.g., Toast or Snackbar)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Detail BPR",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(Res.drawable.arrow_back),
                            contentDescription = "Kembali",
                            tint = Color.Black,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F6F6)) // bg_secondary
                .padding(paddingValues)
        ) {
            if (isLoading && bpr == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                // 🔹 Konten Scrollable
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(bottom = 16.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(2.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            bpr?.let { bprData ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(bottom = 12.dp)
                                ) {
                                    Image(
                                        painter = painterResource(Res.drawable.simfan_websuite),
                                        contentDescription = "Product Image",
                                        modifier = Modifier
                                            .size(50.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                    )
                                    Column(
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(start = 12.dp)
                                    ) {
                                        Text(
                                            text = bprData.name,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color.Black
                                        )
                                        Text(
                                            text = bprData.location,
                                            fontSize = 12.sp,
                                            color = Color(0xFF999999)
                                        )
                                    }
                                }
                                DividerLine(Modifier.padding(vertical = 14.dp))
                                Column(
                                    modifier = Modifier.padding(vertical = 12.dp)
                                ) {
                                    InfoRow("Kode OJK", bprData.ojkCode)
                                    InfoRow("Kode Produk / UID", "BPR-${bprData.id}-${bprData.productCode}")
                                    InfoRow(
                                        "Dijamin LPS",
                                        if (bprData.isGuaranteedLPS) "Ya, Sampai dengan ${bprData.depositLimit.toLong().toRupiah()}" else "Tidak"
                                    )
                                    InfoRow("Sisa Limit Deposito", "Rp${bprData.depositLimit.toLong().toRupiah()}")
                                    InfoRow("Total Transaksi BPR", "${bprData.transactionsTotal} Transaksi")
                                }
                                DividerLine(Modifier.padding(vertical = 14.dp))
                                Column(modifier = Modifier.padding(top = 12.dp)) {
                                    InfoRow("Neraca Keuangan", "Periode 1 Juli 2025", boldLabel = true)
                                    // Here you would add financial data if available
                                    InfoRow("Aset", "Rp750.000")
                                    InfoRow("Kewajiban", "Rp250.000")
                                    InfoRow("Ekuitas", "Rp500.000")
                                    InfoRow("Loan to Deposito Ratio", "65%")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String, boldLabel: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = if (boldLabel) FontWeight.SemiBold else FontWeight.Normal,
            color = Color(0xFF22242F),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF22242F)
        )
    }
}

@Composable
fun DividerLine(padding: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0xFFE0E0E0))
    )
}