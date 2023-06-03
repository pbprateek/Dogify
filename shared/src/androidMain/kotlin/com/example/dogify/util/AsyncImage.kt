package com.example.dogify.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale

@Composable
actual fun AsyncImage(
    modifier: Modifier,
    imageUrl: String,
    contentDescription: String?,
    contentScale: ContentScale
) {
    coil.compose.AsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = null,
        contentScale = contentScale
    )
}