package com.jmanrique.marvelproject.app.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jmanrique.marvelproject.app.ui.base.BaseViewModel
import com.jmanrique.marvelproject.data.network.model.characters.CharacterDataContainer
import com.jmanrique.marvelproject.domain.model.MarvelCharacter
import com.jmanrique.marvelproject.domain.model.MarvelCharacterContainer
import com.jmanrique.marvelproject.domain.usecases.characters.GetCharactersUseCase
import com.jmanrique.marvelproject.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel() {

    val showLoading = SingleLiveEvent<Boolean>().apply { value = false }
    val characterList = MutableLiveData<List<MarvelCharacter>>()

    var currentOffset = 0

    fun getCharactersList() {
        showLoading.value = true
        subscribe(getCharactersUseCase.execute(currentOffset), {
            characterList.value = it.results
            showLoading.value = false
        }, onError = {
            characterList.value = emptyList()
            showLoading.value = false
        })
    }

    fun addMoreElements(value: Int){
        currentOffset+=value
        getCharactersList()
    }

}