package com.jmanrique.marvelproject.app.ui.main

import android.content.Context
import android.util.Log
import com.jmanrique.marvelproject.app.ui.base.BaseViewModel
import com.jmanrique.marvelproject.domain.usecases.characters.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
): BaseViewModel(){

    fun getCharactersList(){
        subscribe(getCharactersUseCase.execute(null),{
            Log.d("CHARACTERS", it.toString())
        })
    }

}