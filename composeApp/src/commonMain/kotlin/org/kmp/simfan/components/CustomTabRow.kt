package org.kmp.simfan.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.kmp.simfan.core.Button1
private val BgSecondary = Color(0xFFF1F2F6)


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