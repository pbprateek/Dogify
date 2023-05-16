package com.example.dogify.view

import com.example.dogify.model.Breed

data class DogifyState(
    val state: State = State.LOADING,
    val isRefreshing: Boolean = true,
    val shouldFilterFavourite: Boolean = false,
    val breeds: List<Breed> = emptyList()
)

enum class State {
    LOADING, ERROR, NORMAL, EMPTY
}