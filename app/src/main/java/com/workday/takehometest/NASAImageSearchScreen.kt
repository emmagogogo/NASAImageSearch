package com.workday.takehometest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NASAImageSearchScreen(
    navController: NavController,
    viewModel: NASAImageSearchViewModel,
    modifier: Modifier = Modifier,
) {
    val status = viewModel.status.collectAsStateWithLifecycle()
    val images = viewModel.images

    var value by remember { mutableStateOf("") }


    Column(
        modifier = modifier,
    ) {
        Row {
            TextField(
                value = value,
                onValueChange = {value = it },
                maxLines = 1,
                label = { Text("Enter Search Text") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        viewModel.searchNASAImage(value)
                    }
                )

            )

            Button(
                onClick = {
                    viewModel.searchNASAImage(value)
                }) {
                Text("Search")
            }
        }

        Row {
            when (status.value) {
                NASAImageApiStatus.LOADING -> CircularProgressIndicator()
                NASAImageApiStatus.ERROR -> Text("Error, try search again")
                NASAImageApiStatus.DONE -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(images) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.clickable(
                                    onClick = { navController.navigate("detail/${it.id}") }
                                )
                            ) {
                                AsyncImage(
                                    model = it.href,
                                    contentDescription = null,
                                    modifier = Modifier.width(100.dp)
                                )
                                Text(it.title)
                            }
                        }
                    }
                }
            }
        }
    }
}