package com.valimade.shiftshaper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.valimade.shiftshaper.ui.screen.DataFillingScreen
import com.valimade.shiftshaper.ui.theme.ShiftShaperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShiftShaperTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DataFillingScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}