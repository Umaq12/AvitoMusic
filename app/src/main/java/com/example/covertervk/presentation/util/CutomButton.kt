package com.example.covertervk.presentation.util

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    onClick:  () -> Unit,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(14.dp),
    align: Arrangement.Horizontal = Arrangement.Center,
    contentPadding: PaddingValues = PaddingValues(0.dp, 0.dp),
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(shape)
            .clickable(
                onClick = onClick,
                enabled = enabled,
            )
    ) {
        Row(
            modifier = modifier
                .padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = align
        ) {
            content()
        }

    }
}