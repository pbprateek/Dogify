package com.example.dogify.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.dogify.android.ui.MainScreen
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val uiState = mainViewModel.state.collectAsState().value
            MaterialTheme() {
                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                    breeds = uiState.breeds,
                    filterApplied = uiState.shouldFilterFavourite,
                    applyFilter = {

                    },
                    setFavourite = {

                    }
                )
            }
        }

    }
}



