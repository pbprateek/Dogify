package com.example.dogify.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


internal actual fun getDispatcherProvider(): DispatcherProvider = IosDispatcherProvider()


/*
Kotlin/Native doesn't have an I/O dispatcher at the moment
 and we'll need to use the default dispatcher to move tasks
  to a background thread for iOS. Since we don't want to compromise on Android,
   we'll want this to be platform-specific and use the classic I/O dispatcher.
 */
private class IosDispatcherProvider : DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}