package com.jmanrique.marvelproject.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseActivity<DB> : AppCompatActivity() where DB : ViewDataBinding {

    lateinit var binding: DB

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)
        bindViewToModel();
    }


    abstract fun inflate(layoutInflater: LayoutInflater): DB

    open fun bindViewToModel(){}

}