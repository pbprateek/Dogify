package com.example.dogify.view

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.dogify.model.Breed
import com.example.dogify.useCases.FetchBreedsUseCase
import com.example.dogify.useCases.GetBreedsUseCase
import com.example.dogify.useCases.ToggleFavouriteStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class MainViewModel(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val fetchBreedsUseCase: FetchBreedsUseCase,
    private val onToggleFavouriteStateUseCase: ToggleFavouriteStateUseCase
):ScreenModel {

    private val _state = MutableStateFlow(DogifyState())
    val state: StateFlow<DogifyState> = _state

    private var breedsCache = emptyList<Breed>()


    init {
        loadData()
        coroutineScope.launch {
            getBreedsUseCase.invoke().collect {
                breedsCache = it
                it.filter { breed ->
                    if (state.value.shouldFilterFavourite)
                        breed.isFavourite
                    else
                        true
                }.also { breedList ->
                    _state.value = _state.value.copy(
                        breeds = breedList,
                        state = if (it.isEmpty()) State.EMPTY else State.NORMAL
                    )
                }
            }
        }
    }

    fun toggleFilterFavourite() {
        _state.value =
            _state.value.copy(shouldFilterFavourite = !_state.value.shouldFilterFavourite, breeds = breedsCache.filter { breed ->
                if (!state.value.shouldFilterFavourite)
                    breed.isFavourite
                else
                    true
            })
    }


    private fun loadData() {
        _state.value = _state.value.copy(state = State.LOADING)
        coroutineScope.launch {
            try {
                fetchBreedsUseCase.invoke()
            } catch (ex: Exception) {
                //No Internet
            }
        }
    }

    fun toggleBreedFavourite(name: String, isFavourite: Boolean) {
        coroutineScope.launch {
            onToggleFavouriteStateUseCase.invoke(name, isFavourite)
        }

    }
}