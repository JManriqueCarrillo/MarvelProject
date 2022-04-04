package com.jmanrique.marvelproject.app.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jmanrique.marvelproject.app.ui.base.BaseActivity
import com.jmanrique.marvelproject.data.network.model.characters.Character
import com.jmanrique.marvelproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: CharacterListViewModel by viewModels()

    @Inject
    lateinit var characterListAdapter: CharacterListAdapter

    private val layoutManager = GridLayoutManager(this, 2)

    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        characterListAdapter.onItemClick = {
            Toast.makeText(this, "Clicked ${it.name}", Toast.LENGTH_SHORT).show()
        }

        viewModel.getCharactersList()

        viewModel.characterList.observe(this) {
            characterListAdapter.data = it
            binding.characterList.layoutManager = layoutManager
            binding.characterList.adapter = characterListAdapter
        }

        binding.characterList.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount-1){

                }
            }
        })
    }

    override fun inflate(layoutInflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)
}