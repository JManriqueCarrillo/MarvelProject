package com.jmanrique.marvelproject.app.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.jmanrique.marvelproject.app.ui.base.BaseActivity
import com.jmanrique.marvelproject.databinding.ActivityCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailActivity : BaseActivity<ActivityCharacterDetailBinding>() {

    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun inflate(layoutInflater: LayoutInflater): ActivityCharacterDetailBinding =
        ActivityCharacterDetailBinding.inflate(layoutInflater)


}