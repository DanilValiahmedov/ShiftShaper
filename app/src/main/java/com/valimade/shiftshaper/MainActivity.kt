package com.valimade.shiftshaper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valimade.shiftshaper.ui.components.ExpandableTextField
import com.valimade.shiftshaper.ui.theme.ShiftShaperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShiftShaperTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {

    var route by remember { mutableStateOf("") }
    var shift by remember { mutableStateOf("") }
    var initialTime by remember { mutableStateOf("") }
    var pointS by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }
    var pointE by remember { mutableStateOf("") }
    var mode by remember { mutableStateOf("") }
    var work by remember { mutableStateOf("") }
    var rest by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .padding(16.dp),

    ) {
        ExpandableTextField("Маршрут", route) { route = it }
        ExpandableTextField("Смена", shift) { shift = it }
        ExpandableTextField("Начало смены", initialTime) { initialTime = it }
        ExpandableTextField("Место заступления", pointS) { pointS = it }
        ExpandableTextField("Время окончания смены", endTime) { endTime = it }
        ExpandableTextField("Место окончания смены", pointE) { pointE = it }
        ExpandableTextField("Режим", mode) { mode = it }
        ExpandableTextField("Работа", work) { work = it }
        ExpandableTextField("Подмены", rest) { rest = it }
        ExpandableTextField("Продолжительность", duration) { duration = it }
    }
}
