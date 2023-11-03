package com.example.poketeambuilder.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.poketeambuilder.domain.Pokemon
import com.example.poketeambuilder.domain.usecases.GetAllPokeUseCase
import com.example.poketeambuilder.domain.usecases.GetFilteredPokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokeDexViewModel @Inject constructor(
    private val getAllPokeUseCase: GetAllPokeUseCase,
    private val getFilteredPokeUseCase: GetFilteredPokeUseCase
) : ViewModel() {

    private var _currentPokeList = MutableLiveData<List<Pokemon>?>()
    val currentPokeList: LiveData<List<Pokemon>?> = _currentPokeList

    var state = MutableStateFlow(false)
    val stateFlow = state.asStateFlow()

    suspend fun loadPokes() {
        withContext(Dispatchers.IO) {
            val result = getAllPokeUseCase()
            result.fold(
                onSuccess = { currentPokeList -> _currentPokeList.postValue(currentPokeList)},
                onFailure = { error -> Log.e("PokeDexViewModel", error.toString())}
            )
        }
    }

    suspend fun getFilteredPoke(
        nameOrId: String?,
        mainType: String?,
        secondaryType: String?
    ) {
        withContext(Dispatchers.IO) {
            val result = getFilteredPokeUseCase(
                nameOrId = nameOrId,
                mainType = mainType,
                secondaryType = secondaryType
            )

            result.fold(
                onSuccess = { currentPokeList ->
                    _currentPokeList.postValue(currentPokeList)
                },
                onFailure = {error ->
                    Log.e("PokeDexViewModel", error.toString())
                }
            )
        }
    }

}