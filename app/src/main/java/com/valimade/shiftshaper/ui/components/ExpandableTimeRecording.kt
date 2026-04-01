package com.valimade.shiftshaper.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.valimade.shiftshaper.ui.theme.PrimaryColor
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@Composable
fun ExpandableTimeRecording(
    title: String,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val hours = (0..23).map { it.toString().padStart(2, '0') }
    val minutes = (0..59).map { it.toString().padStart(2, '0') }

    var selectedHour by remember { mutableStateOf(0) }
    var selectedMinute by remember { mutableStateOf(0) }

    LaunchedEffect(selectedHour, selectedMinute) {
        onValueChange("${hours[selectedHour]}:${minutes[selectedMinute]}")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { expanded = !expanded }
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = if (expanded)
                    Icons.Default.KeyboardArrowDown
                else
                    Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = PrimaryColor
            )
        }

        Divider(color = PrimaryColor.copy(alpha = 0.3f))

        AnimatedVisibility(visible = expanded) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                TimePickerColumn(
                    items = hours,
                    selectedIndex = selectedHour,
                    onIndexChange = { selectedHour = it }
                )

                Text(
                    text = ":",
                    fontSize = 24.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                TimePickerColumn(
                    items = minutes,
                    selectedIndex = selectedMinute,
                    onIndexChange = { selectedMinute = it }
                )
            }

        }
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun TimePickerColumn(
    items: List<String>,
    selectedIndex: Int,
    onIndexChange: (Int) -> Unit
) {
    val itemHeight = 40.dp
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = selectedIndex)
    val flingBehavior = rememberSnapperFlingBehavior(lazyListState = listState)

    Box(
        modifier = Modifier
            .height(3 * itemHeight)
            .width(70.dp),
        contentAlignment = Alignment.Center
    ) {

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(vertical = itemHeight)
        ) {
            itemsIndexed(items) { index, item ->
                Box(
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item,
                        fontSize = 20.sp,
                        color = if (index == listState.firstVisibleItemIndex) PrimaryColor else Color.Black
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .height(itemHeight)
                .fillMaxWidth()
                .border(2.dp, PrimaryColor.copy(alpha = 0.9f), RoundedCornerShape(8.dp))
                .background(PrimaryColor.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
        )
    }

    // Слежение за выбранным элементом
    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                onIndexChange(index.coerceIn(0, items.lastIndex))
            }
    }
}