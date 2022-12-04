package com.radenyaqien.composecampdicoding.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.radenyaqien.composecampdicoding.data.RickMortyRepository
import com.radenyaqien.composecampdicoding.data.model.RickMorty

class DetailViewModel(
    id: Int,
    repository: RickMortyRepository
) : ViewModel() {

    private val _state = mutableStateOf(repository.getRickMortyById(id))
    val state: State<RickMorty?> = _state
}

class ViewModelFactory(private val id: Int, private val repository: RickMortyRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(id, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}