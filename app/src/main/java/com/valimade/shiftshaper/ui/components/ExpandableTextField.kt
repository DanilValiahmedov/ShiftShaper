package com.valimade.shiftshaper.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valimade.shiftshaper.ui.theme.PrimaryColor
import com.valimade.shiftshaper.ui.theme.SecondaryColor

@Composable
fun ExpandableTextField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit
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
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(56.dp),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black.copy(alpha = 0.7f),
                    cursorColor = PrimaryColor,

                    focusedContainerColor = SecondaryColor.copy(alpha = 0.05f),
                    unfocusedContainerColor = SecondaryColor.copy(alpha = 0.03f),

                    focusedIndicatorColor = PrimaryColor,
                    unfocusedIndicatorColor = PrimaryColor.copy(alpha = 0.3f),

                    focusedLabelColor = PrimaryColor,
                ),
                trailingIcon = {
                    if (value.isNotEmpty()) {
                        IconButton(onClick = { onValueChange("") }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Очистить",
                                tint = PrimaryColor
                            )
                        }
                    }
                }
            )
        }
    }
}