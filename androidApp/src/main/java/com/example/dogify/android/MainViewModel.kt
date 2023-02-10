package com.example.dogify.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogify.model.Breed
import com.example.dogify.useCases.FetchBreedsUseCase
import com.example.dogify.useCases.GetBreedsUseCase
import com.example.dogify.useCases.ToggleFavouriteStateUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MainViewModel(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val fetchBreedsUseCase: FetchBreedsUseCase,
    private val onToggleFavouriteStateUseCase: ToggleFavouriteStateUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DogifyState())
    val state: StateFlow<DogifyState> = _state

    private var breedsCache = emptyList<Breed>()


    init {
        loadData()
        viewModelScope.launch {
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
        viewModelScope.launch {
            try {
                fetchBreedsUseCase.invoke()
            } catch (ex: UnknownHostException) {
                //No Internet
            }
        }
    }

    fun toggleBreedFavourite(name: String, isFavourite: Boolean) {
        viewModelScope.launch {
            onToggleFavouriteStateUseCase.invoke(name, isFavourite)
        }

    }


}