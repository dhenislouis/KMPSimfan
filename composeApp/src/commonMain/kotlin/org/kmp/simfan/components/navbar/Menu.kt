package org.kmp.simfan.components.navbar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Ini Halaman Home")
        }
    }
}

object ProdukScreen : Screen {
    @Composable
    override fun Content() {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Ini Halaman Produk")
        }
    }
}

object DepositoScreen : Screen {
    @Composable
    override fun Content() {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Ini Halaman Deposito")
        }
    }
}

object ProfilScreen : Screen {
    @Composable
    override fun Content() {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Ini Halaman Profil")
        }
    }
}
