package com.jmanrique.marvelproject.app.ui.main

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jmanrique.marvelproject.app.ui.base.BaseViewModel
import com.jmanrique.marvelproject.data.network.model.characters.Character
import com.jmanrique.marvelproject.data.network.model.characters.CharacterDataContainer
import com.jmanrique.marvelproject.domain.usecases.characters.GetCharactersUseCase
import com.jmanrique.marvelproject.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : BaseViewModel() {

    val showLoading = SingleLiveEvent<Boolean>().apply { value = false }
    val characterList = MutableLiveData<CharacterDataContainer>()

    val currentPage = 0


    fun getCharactersList() {
        showLoading.value = true
        subscribe(getCharactersUseCase.execute(null), {
            Log.d("CHARACTERS", it.toString())
            characterList.value = it.data!!
            showLoading.value = false
        }, onError = {
            Log.d("CHARACTERS", it.toString())
            showLoading.value = false
        })
    }

}