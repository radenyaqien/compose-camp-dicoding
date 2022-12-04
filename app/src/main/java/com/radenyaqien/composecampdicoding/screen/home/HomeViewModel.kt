package com.radenyaqien.composecampdicoding.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.radenyaqien.composecampdicoding.data.RickMortyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(private val repository: RickMortyRepository) : ViewModel() {


    private val _searchBarWidgetState: MutableState<SearchBarWidgetState> =
        mutableStateOf(value = SearchBarWidgetState.CLOSED)
    val searchBarWidgetState: State<SearchBarWidgetState> = _searchBarWidgetState

    private val _state = MutableStateFlow(repository.getDataRickMorty())
    val state = _state.asStateFlow()

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _state.update {
            repository.searchRickMorty(_query.value)
        }
    }

    fun updateSearchWidgetState(newValue: SearchBarWidgetState) {
        _searchBarWidgetState.value = newValue
    }
}

class ViewModelFactory(private val repository: RickMortyRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}