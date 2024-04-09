package com.example.dogify.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.dogify.di.sharedModule
import org.koin.compose.KoinApplication


@Composable
fun App() {
    KoinApplication(application = {
        modules(sharedModule)
    }) {
        Navigator(HomeScreen)
    }
}