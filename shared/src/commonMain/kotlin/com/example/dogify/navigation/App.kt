package com.example.dogify.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.dogify.di.sharedModule
import org.koin.compose.KoinApplication
import org.koin.core.module.Module


val moduleList: () -> List<Module> = {
    sharedModule
}
@Composable
fun App() {
    KoinApplication(moduleList) {
        Navigator(HomeScreen)
    }

}