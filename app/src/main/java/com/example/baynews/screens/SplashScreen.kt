package com.example.baynews.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.example.baynews.R
import com.example.baynews.BuildConfig

@Composable
fun SplashScreen(onFinished: () -> Unit) {
    LaunchedEffect(Unit) {
        android.util.Log.d("BayNews", "API key length = ${BuildConfig.NEWS_API_KEY.length}")
        delay(1200)
        onFinished()
    }

    val mandiriBlue = Color(0xFF003D79)
    val mandiriYellow = Color(0xFFFFC107)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(mandiriBlue, mandiriBlue.copy(alpha = 0.92f))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo_mandiri),
                contentDescription = "Bank Mandiri Logo",
                modifier = Modifier.height(72.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "BayNews",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(6.dp))

            Box(
                Modifier
                    .height(4.dp)
                    .width(48.dp)
                    .background(mandiriYellow)
            )

            Spacer(Modifier.height(18.dp))

            CircularProgressIndicator(color = Color.White, strokeWidth = 3.dp)
        }
    }
}