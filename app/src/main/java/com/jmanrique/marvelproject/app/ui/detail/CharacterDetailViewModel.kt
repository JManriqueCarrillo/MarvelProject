package com.jmanrique.marvelproject.app.ui.detail

import androidx.lifecycle.MutableLiveData
import com.jmanrique.marvelproject.app.ui.base.BaseViewModel
import com.jmanrique.marvelproject.domain.model.characters.MarvelCharacter
import com.jmanrique.marvelproject.domain.model.comics.MarvelComic
import com.jmanrique.marvelproject.domain.usecases.characters.GetCharactersByIdUseCase
import com.jmanrique.marvelproject.domain.usecases.comics.GetComicsByCharacterIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharactersByIdUseCase: GetCharactersByIdUseCase,
    private val getComicsByCharacterIdUseCase: GetComicsByCharacterIdUseCase
) : BaseViewModel() {

    val characterData = MutableLiveData<MarvelCharacter>()
    val comicsData = MutableLiveData<List<MarvelComic>>()

    fun getCharacterDetail(characterId: Int){
        subscribe(getCharactersByIdUseCase.execute(characterId),{
            characterData.value = it.results[0]
        })
    }

    fun getComics(characterId: Int){
        subscribe(getComicsByCharacterIdUseCase.execute(characterId),{
            comicsData.value = it.results
        })
    }

}