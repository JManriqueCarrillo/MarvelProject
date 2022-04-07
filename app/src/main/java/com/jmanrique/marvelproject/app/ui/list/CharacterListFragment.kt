package com.jmanrique.marvelproject.app.ui.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jmanrique.marvelproject.R
import com.jmanrique.marvelproject.app.ui.base.BaseFragment
import com.jmanrique.marvelproject.app.ui.main.MainActivity
import com.jmanrique.marvelproject.data.network.APIConstants
import com.jmanrique.marvelproject.databinding.FragmentCharacterListBinding
import com.jmanrique.marvelproject.domain.model.characters.MarvelCharacter
import com.jmanrique.marvelproject.utils.extensions.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment : BaseFragment<FragmentCharacterListBinding>(),
    SearchView.OnQueryTextListener {

    val OFFSET_VALUE = APIConstants.limit.toInt()
    val viewModel: CharacterListViewModel by viewModels()

    @Inject
    lateinit var characterListAdapter: CharacterListAdapter

    lateinit var layoutManager: LinearLayoutManager
    private var flagLoading = false
    private var flagScrollListener = true

    private lateinit var searchTerm: String

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentCharacterListBinding =
        FragmentCharacterListBinding.inflate(layoutInflater, container, attachToRoot)


    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        initListeners()
        restartList()
    }

    private fun initViews() {

        (activity as MainActivity).supportActionBar?.apply {
            title = getString(R.string.app_name)
            setHasOptionsMenu(true)
        }

        layoutManager = GridLayoutManager(context, 2)
        binding.characterList.layoutManager = layoutManager
        binding.characterList.adapter = characterListAdapter
    }

    private fun initObservers() {
        viewModel.characterList.observe(viewLifecycleOwner) {
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
            navigateToCharacterDetail(it)
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

    private fun navigateToCharacterDetail(item: MarvelCharacter) {
        val navController = view?.let { Navigation.findNavController(it) }
        val bundle = Bundle()
        bundle.putString("characterName", item.name)
        bundle.putInt("characterID", item.id)
        navController?.navigate(R.id.action_list_to_detail, bundle)
    }

    private fun callAPI() {
        viewModel.getCharactersList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val search = menu?.findItem(R.id.menuSearch)
        val searchView = search?.actionView as androidx.appcompat.widget.SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

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