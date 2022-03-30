package com.jmanrique.marvelproject.app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.jmanrique.marvelproject.app.ui.base.BaseActivity
import com.jmanrique.marvelproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCharactersList()

    }

    override fun inflate(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)
}