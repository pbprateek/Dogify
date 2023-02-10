package com.example.dogify.android.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dogify.android.R
import com.example.dogify.model.Breed
import java.util.*


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    breeds: List<Breed>,
    filterApplied: Boolean,
    applyFilter: () -> Unit,
    setFavourite: (name: String, isFavourite: Boolean) -> Unit
) {
    Column(modifier = modifier) {
        TopAppBar() {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Switch(modifier = Modifier.align(Alignment.CenterEnd), checked = filterApplied, onCheckedChange = {
                    applyFilter()
                })

                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Doggos",
                    style = MaterialTheme.typography.h4
                )
            }

        }

        LazyColumn(
            modifier = modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            items(breeds) {
                BreedItem(breed = it, setFavourite = setFavourite)
            }
        }
    }


}

@Composable
fun BreedItem(modifier: Modifier = Modifier, breed: Breed, setFavourite: (name: String, isFavourite: Boolean) -> Unit) {
    Card(modifier = Modifier.padding(12.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.FillHeight,
                model = breed.imageUrl,
                contentDescription = null,
                //placeholder = painterResource(id = R.drawable.baseline_thumb_up_24)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = breed.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    fontStyle = FontStyle.Italic
                )

                IconToggleButton(checked = breed.isFavourite, onCheckedChange = { setFavourite(breed.name, !breed.isFavourite) }) {
                    Icon(painter = painterResource(id = if (breed.isFavourite) R.drawable.baseline_thumb_up_24 else R.drawable.baseline_thumb_up_off_alt_24), contentDescription = null)
                }
            }

        }

    }
}