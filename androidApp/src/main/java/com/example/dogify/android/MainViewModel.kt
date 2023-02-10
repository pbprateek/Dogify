package com.example.dogify.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogify.model.Breed
import com.example.dogify.useCases.GetBreedsUseCase
import com.example.dogify.useCases.ToggleFavouriteStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val onToggleFavouriteStateUseCase: ToggleFavouriteStateUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DogifyState())
    val state: StateFlow<DogifyState> = _state


    init {
        loadData()
    }

    fun toggleFilterFavourite() {
        _state.value =
            _state.value.copy(shouldFilterFavourite = !_state.value.shouldFilterFavourite)
        loadData()
    }


    private fun loadData() {
        _state.value = _state.value.copy(state = State.LOADING)
        viewModelScope.launch {
            getBreedsUseCase.invoke().filter {
                if (state.value.shouldFilterFavourite)
                    it.isFavourite
                else
                    true
            }.also {
                _state.value = _state.value.copy(
                    breeds = it,
                    state = if (it.isEmpty()) State.EMPTY else State.NORMAL
                )
            }
        }
    }

    fun toggleBreedFavourite(breed: Breed) {
        viewModelScope.launch {
            onToggleFavouriteStateUseCase.invoke(breed)
        }

    }


}