package com.valimade.shiftshaper.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valimade.shiftshaper.ui.theme.PrimaryColor
import com.valimade.shiftshaper.ui.theme.SecondaryColor

@Composable
fun ExpandableModeWork(
    title: String,
    items: List<String>,
    selectedItem: String?,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

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
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items) { item ->
                    ChipItem(
                        text = item,
                        isSelected = item == selectedItem,
                        onClick = { onItemSelected(item) }
                    )
                }
            }
        }
    }
}

@Composable
fun ChipItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val background = if (isSelected) PrimaryColor else SecondaryColor
    val textColor = if (isSelected) SecondaryColor else PrimaryColor

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .border(
                width = 2.dp,
                color = PrimaryColor,
                shape = RoundedCornerShape(50)
            )
            .background(background)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 14.sp
        )
    }
}