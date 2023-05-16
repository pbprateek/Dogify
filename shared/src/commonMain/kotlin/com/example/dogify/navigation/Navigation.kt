package com.example.dogify.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.dogify.view.MainScreen
import com.example.dogify.view.MainViewModel
import org.koin.compose.koinInject

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        //TODO:After they have fixed Koin KMM support , fix it according to Voyager doc
        val screenModel: MainViewModel = koinInject()
        val uiState = screenModel.state.collectAsState().value
        MaterialTheme() {
            MainScreen(
                modifier = Modifier.fillMaxSize(),
                breeds = uiState.breeds,
                filterApplied = uiState.shouldFilterFavourite,
                applyFilter = {
                    screenModel.toggleFilterFavourite()
                },
                setFavourite = { name, isFav ->
                    screenModel.toggleBreedFavourite(name, isFav)
                }
            )
        }
    }
}

//Voyager koin is not added in common main, bcz it didn't have multiplatform support
//After they do we do not have to use below to make voyager work with koin

//@Composable
//public inline fun <reified T : ScreenModel> Screen.getScreenModel(
//    qualifier: Qualifier? = null,
//    noinline parameters: ParametersDefinition? = null,
//): T {
//    //Throws error that koin application not initiized
//    val koin = getKoin()
//    return rememberScreenModel(tag = qualifier?.value) { koin.get(qualifier, parameters) }
//}