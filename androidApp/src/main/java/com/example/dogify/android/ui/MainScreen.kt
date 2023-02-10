package com.example.dogify.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dogify.model.Breed


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    breeds: List<Breed>,
    filterApplied: Boolean,
    applyFilter: () -> Unit,
    setFavourite: (name: String) -> Unit
) {
    LazyColumn() {
        items(breeds) {
            BreedItem(breed = it)
        }
    }
}

@Composable
fun BreedItem(modifier: Modifier = Modifier, breed: Breed) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column {
            AsyncImage(
                modifier = Modifier.size(200.dp,300.dp),
                model = breed.imageUrl,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.baseline_thumb_up_24)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = breed.name,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}