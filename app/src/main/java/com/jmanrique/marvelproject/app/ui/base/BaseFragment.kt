package com.jmanrique.marvelproject.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<DB>: Fragment() where DB: ViewDataBinding {

    lateinit var binding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateBinding(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        bindViewToModel()
        return binding.root
    }

    abstract fun inflateBinding(layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): DB

    open fun bindViewToModel() {
        /**
         * Bind view components to model provider (ViewModel itself / LiveData) via binding (view is not created)
         */
    }

}