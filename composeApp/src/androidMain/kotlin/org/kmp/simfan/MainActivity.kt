package org.kmp.simfan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.Navigator
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen() // jangan kasih "this"!
        super.onCreate(savedInstanceState)
//        FirebaseApp.initializeApp(this)
//        FirebaseConfig.init()

        setContent {
            App()
        }
    }
}


@Preview
@Composable
fun AppAndroidPreview() {
    App()
}