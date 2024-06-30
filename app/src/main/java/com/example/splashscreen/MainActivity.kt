package com.example.splashscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.splashscreen.Constants.MAIN_SCREEN
import com.example.splashscreen.Constants.MAIN_SCREEN2
import com.example.splashscreen.Constants.SPLASH_SCREEN
import com.example.splashscreen.ui.theme.SplashScreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreenTheme {
                Navigate()
            }
        }
    }
}

@Composable
fun Navigate() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        composable(SPLASH_SCREEN) {
            SplashScreen(navController)
        }
        composable(MAIN_SCREEN) {
            MainScreen()
        }
    }
}


@Composable
fun SplashScreen(navController: NavController, splashViewModel: MainViewModel = viewModel()) {
    val isSplashScreenVisible by splashViewModel.isSplashScreenVisible.collectAsState()

    if (!isSplashScreenVisible) {
        LaunchedEffect(Unit) {
            navController.navigate(MAIN_SCREEN) {
                popUpTo(SPLASH_SCREEN) { inclusive = true }
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.coffee),
                contentDescription = "",
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = MAIN_SCREEN2, fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}

object Constants {
    const val SPLASH_SCREEN = "splash_screen"
    const val MAIN_SCREEN = "main_screen"
    const val MAIN_SCREEN2 = "Main Screen"
}
