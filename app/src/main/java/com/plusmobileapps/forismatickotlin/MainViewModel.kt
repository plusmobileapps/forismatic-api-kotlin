package com.plusmobileapps.forismatickotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MainViewState {
    object Empty: MainViewState()
    object Loading: MainViewState()
    data class Loaded(val quote: GetQuoteModel): MainViewState()
}

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ForismaticRepository) : ViewModel() {

    private val _state = MutableStateFlow<MainViewState>(MainViewState.Empty)
    val state: StateFlow<MainViewState> get() = _state

    fun getQuoteClicked() {
        _state.value = MainViewState.Loading
        viewModelScope.launch {
            val quote = repository.getQuote()
            _state.value = MainViewState.Loaded(quote)
        }
    }
}