package com.jmanrique.marvelproject.app.ui.list

import androidx.lifecycle.MutableLiveData
import com.jmanrique.marvelproject.app.ui.base.BaseViewModel
import com.jmanrique.marvelproject.domain.model.characters.MarvelCharacter
import com.jmanrique.marvelproject.domain.usecases.characters.GetCharactersStartWithTextUseCase
import com.jmanrique.marvelproject.domain.usecases.characters.GetCharactersUseCase
import com.jmanrique.marvelproject.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharactersStartWithTextUseCase: GetCharactersStartWithTextUseCase
) : BaseViewModel() {

    val showLoading = SingleLiveEvent<Boolean>().apply { value = false }
    val showEmpty = SingleLiveEvent<Boolean>().apply { value = false }
    val characterList = MutableLiveData<List<MarvelCharacter>>()
    var currentOffset = 0

    fun getCharactersList() {
        showLoading.value = true
        showEmpty.value = false
        subscribe(getCharactersUseCase.execute(currentOffset), {
            characterList.value = it.results
            showLoading.value = false
        }, onError = {
            characterList.value = emptyList()
            showEmpty.value = true
            showLoading.value = false
        })
    }

    fun addMoreElements(value: Int) {
        currentOffset += value
        getCharactersList()
    }

    fun getCharactersStartWithText(search: String) {
        showLoading.value = true
        showEmpty.value = false
        subscribe(getCharactersStartWithTextUseCase.execute(search), {
            if (it.results.isEmpty()) showEmpty.value = true
            else characterList.value = it.results
            showLoading.value = false
        }, onError = {
            showEmpty.value = true
            characterList.value = emptyList()
            showLoading.value = false
        })
    }

}