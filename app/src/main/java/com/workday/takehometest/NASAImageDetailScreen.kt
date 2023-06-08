package com.workday.takehometest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun NASAImageDetailScreen(
    id: String?,
    viewModel: NASAImageSearchViewModel,
    modifier: Modifier = Modifier,
) {
    if (id.isNullOrEmpty()) {
        return
    }

    val image = viewModel.images.find {
        it.id == id
    } ?: return

    Column(
      modifier = modifier.padding(6.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),

    ) {
        Text("Title: " + image.title)
        Text("Description: " + image.description)
        Text("Date Created: " + image.dateCreated.toString())
        AsyncImage(
            model = image.href,
            contentDescription = null,
            modifier = Modifier.width(300.dp)
        )
    }
}