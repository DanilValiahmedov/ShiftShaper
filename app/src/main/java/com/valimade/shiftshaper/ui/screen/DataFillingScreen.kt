package com.valimade.shiftshaper.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valimade.shiftshaper.domain.model.ModeWork
import com.valimade.shiftshaper.ui.components.ExpandableModeWork
import com.valimade.shiftshaper.ui.components.ExpandableTextField
import com.valimade.shiftshaper.ui.components.ExpandableTimeRecording

@Composable
fun DataFillingScreen(modifier: Modifier = Modifier) {

    var route by remember { mutableStateOf("") }
    var shift by remember { mutableStateOf("") }
    var initialTime by remember { mutableStateOf("") }
    var pointS by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }
    var pointE by remember { mutableStateOf("") }
    var mode by remember { mutableStateOf("") }
    var modeList by remember { mutableStateOf(
        listOf(ModeWork.LINE.title, ModeWork.RESERVE.title)
    ) }
    var work by remember { mutableStateOf("") }
    var rest by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }


    Column (
        modifier = modifier
            .padding(16.dp),

        ) {
        ExpandableTextField("Маршрут", route) { route = it }
        ExpandableTextField("Смена", shift) { shift = it }
        ExpandableTimeRecording("Начало смены") { initialTime = it }
        ExpandableTextField("Место заступления", pointS) { pointS = it }
        ExpandableTimeRecording("Время окончания смены") { endTime = it }
        ExpandableTextField("Место окончания смены", pointE) { pointE = it }
        ExpandableModeWork(
            title = "Режим",
            items = modeList,
            selectedItem = mode,
            onItemSelected = { mode = it }
        )
        ExpandableTextField("Работа", work) { work = it }
        ExpandableTextField("Подмены", rest) { rest = it }
        ExpandableTextField("Продолжительность", duration) { duration = it }
    }
}
