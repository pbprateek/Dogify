package com.example.dogify.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dogify.model.Breed
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.rememberAsyncImagePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


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

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BreedItem(modifier: Modifier = Modifier, breed: Breed, setFavourite: (name: String, isFavourite: Boolean) -> Unit) {
    Card(modifier = Modifier.padding(12.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {

            val painter = rememberAsyncImagePainter(breed.imageUrl)
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.FillHeight,
                painter = painter,
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = breed.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    fontStyle = FontStyle.Italic
                )

                IconToggleButton(
                    checked = breed.isFavourite,
                    onCheckedChange = { setFavourite(breed.name, !breed.isFavourite) }) {
                    val image =
                        if (breed.isFavourite) "baseline_thumb_up_24.xml" else "baseline_thumb_up_off_alt_24.xml"
                    Image(
                        painter = painterResource(image),
                        contentDescription = null
                    )
                }
            }

        }

    }
}