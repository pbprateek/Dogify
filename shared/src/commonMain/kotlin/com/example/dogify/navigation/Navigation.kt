package com.example.dogify.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.example.dogify.view.MainScreen
import com.example.dogify.view.MainViewModel

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: MainViewModel = getScreenModel<MainViewModel>()
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