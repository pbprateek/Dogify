package com.example.dogify.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.TurnLeft
import androidx.compose.material.icons.filled.TurnRight
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dogify.model.Breed
import com.example.dogify.util.AsyncImage
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

        Box(modifier = Modifier.weight(1f)) {
            val scrollState = rememberLazyListState()
            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                state = scrollState
            ) {
                items(breeds) {
                    BreedItem(breed = it, setFavourite = setFavourite)
                }
            }

            //Desktop only
//            VerticalScrollbar(
//                modifier = Modifier.align(Alignment.CenterEnd)
//                    .fillMaxHeight(),
//                adapter = rememberScrollbarAdapter(scrollState)
//            )
        }
    }


}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun BreedItem(modifier: Modifier = Modifier, breed: Breed, setFavourite: (name: String, isFavourite: Boolean) -> Unit) {
    Card(modifier = Modifier.padding(12.dp)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Icon(Icons.Default.Image, contentDescription = null, tint = Color.Black)

            AsyncImage(
                imageUrl = breed.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.FillHeight
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
                    //TODO:Uncomment to Make it work with our file resource
                    //Working in android and desktop, not ios
//                    val image =
//                        if (breed.isFavourite) "baseline_thumb_up_24.xml" else "baseline_thumb_up_off_alt_24.xml"
//                    Image(
//                        painter = painterResource(image),
//                        contentDescription = null
                    // )

                    val image =
                        if (breed.isFavourite) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp
                    Icon(
                        image,
                        contentDescription = null
                    )


                }
            }

        }

    }
}