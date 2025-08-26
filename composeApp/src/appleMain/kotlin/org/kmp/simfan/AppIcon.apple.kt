package org.kmp.simfan

@androidx.compose.runtime.Composable
actual fun AppIcon(
    name: String,
    modifier: androidx.compose.ui.Modifier,
    selected: Boolean
) {
    val res = when (name) {
        "home" -> if (selected) Res.drawable.home_active else Res.drawable.home
        "product" -> if (selected) Res.drawable.product_active else Res.drawable.product
        "deposito" -> if (selected) Res.drawable.deposito_active else Res.drawable.deposito
        "profile" -> if (selected) Res.drawable.profile_active else Res.drawable.profile
        else -> Res.drawable.default_icon
    }

    Icon(
        painter = painterResource(res),
        contentDescription = name,
        modifier = modifier
    )
}