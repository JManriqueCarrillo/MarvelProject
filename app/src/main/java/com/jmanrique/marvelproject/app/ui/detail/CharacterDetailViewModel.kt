package com.jmanrique.marvelproject.app.ui.detail

import android.content.Context
import com.jmanrique.marvelproject.app.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    @ApplicationContext context: Context
) : BaseViewModel() {
}