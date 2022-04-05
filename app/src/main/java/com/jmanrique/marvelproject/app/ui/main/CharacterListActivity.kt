package com.jmanrique.marvelproject.app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jmanrique.marvelproject.app.ui.base.BaseActivity
import com.jmanrique.marvelproject.data.network.APIConstants
import com.jmanrique.marvelproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListActivity : BaseActivity<ActivityMainBinding>() {

    val OFFSET_VALUE = APIConstants.limit.toInt()
    private val viewModel: CharacterListViewModel by viewModels()

    @Inject
    lateinit var characterListAdapter: CharacterListAdapter

    private val layoutManager = GridLayoutManager(this, 2)
    private var flagLoading = false
    private var flagScrollListener = true

    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initObservers()
        initListeners()
        viewModel.getCharactersList()
    }

    private fun initViews() {
        binding.characterList.layoutManager = layoutManager
        binding.characterList.adapter = characterListAdapter
    }

    private fun initObservers() {
        viewModel.characterList.observe(this) {
            if (it.isEmpty()) {
                flagScrollListener = false
            } else {
                characterListAdapter.data.addAll(it)
                characterListAdapter.notifyDataSetChanged()
            }
            flagLoading = false
        }
    }

    private fun initListeners() {
        characterListAdapter.onItemClick = {
            Toast.makeText(this, "Clicked ${it.name}", Toast.LENGTH_SHORT).show()
        }

        binding.characterList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (flagScrollListener &&
                    layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1 &&
                    !flagLoading
                ) {
                    flagLoading = true
                    viewModel.addMoreElements(OFFSET_VALUE)
                }
            }
        })
    }

    override fun inflate(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)
}