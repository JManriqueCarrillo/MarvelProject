package com.jmanrique.marvelproject.app.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jmanrique.marvelproject.R
import com.jmanrique.marvelproject.app.ui.base.BaseActivity
import com.jmanrique.marvelproject.app.ui.detail.CharacterDetailActivity
import com.jmanrique.marvelproject.data.network.APIConstants
import com.jmanrique.marvelproject.databinding.ActivityCharacterListBinding
import com.jmanrique.marvelproject.utils.extensions.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListActivity : BaseActivity<ActivityCharacterListBinding>(), SearchView.OnQueryTextListener {

    val OFFSET_VALUE = APIConstants.limit.toInt()
    private val viewModel: CharacterListViewModel by viewModels()

    @Inject
    lateinit var characterListAdapter: CharacterListAdapter

    private val layoutManager = GridLayoutManager(this, 2)
    private var flagLoading = false
    private var flagScrollListener = true

    private lateinit var searchTerm: String

    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        initObservers()
        initListeners()
        callAPI()
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
            navigateToCharacterDetail(it.id.toString())
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

    fun navigateToCharacterDetail(characterId: String) {
        var intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra("characterID", characterId)
        startActivity(intent)
    }

    private fun callAPI() {
        viewModel.getCharactersList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val search = menu?.findItem(R.id.menuSearch)
        val searchView = search?.actionView as androidx.appcompat.widget.SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun inflate(layoutInflater: LayoutInflater): ActivityCharacterListBinding =
        ActivityCharacterListBinding.inflate(layoutInflater)

    override fun onQueryTextSubmit(query: String?): Boolean {
        hideKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) searchTerm = newText
        else restartList()

        if (searchTerm.isNotEmpty()) searchByText()
        else restartList()

        return true
    }

    private fun searchByText() {
        clearList()
        viewModel.getCharactersStartWithText(searchTerm)
        flagScrollListener = false
    }

    private fun restartList() {
        flagScrollListener = true
        clearList()
        callAPI()
    }

    private fun clearList() {
        characterListAdapter.data.clear()
        characterListAdapter.notifyDataSetChanged()
    }

}