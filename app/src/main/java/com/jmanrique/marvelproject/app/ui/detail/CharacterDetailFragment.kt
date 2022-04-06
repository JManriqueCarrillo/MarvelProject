package com.jmanrique.marvelproject.app.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmanrique.marvelproject.R
import com.jmanrique.marvelproject.app.ui.base.BaseFragment
import com.jmanrique.marvelproject.app.ui.main.MainActivity
import com.jmanrique.marvelproject.databinding.FragmentCharacterDetailBinding
import com.jmanrique.marvelproject.domain.model.characters.MarvelCharacter
import com.jmanrique.marvelproject.domain.model.comics.MarvelComic
import com.jmanrique.marvelproject.utils.extensions.loadUrl
import com.jmanrique.marvelproject.utils.extensions.safeValue
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding>() {

    val viewModel: CharacterDetailViewModel by viewModels()
    private var characterName: String = "Thanos"
    private var characterID: Int = 1009652
    lateinit var navController: NavController

    @Inject
    lateinit var comicsListAdapter: CharacterDetailComicsListAdapter

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentCharacterDetailBinding =
        FragmentCharacterDetailBinding.inflate(layoutInflater, container, attachToRoot)

    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBundleExtras()
        initViews()
        initObservers()
        initListeners()
        callAPI()
    }

    private fun getBundleExtras() {
        characterName = arguments?.getString("characterName").safeValue()
        characterID = arguments?.getInt("characterID") ?: 0
    }

    private fun initViews() {

        navController = view?.let { Navigation.findNavController(it) }!!

        (activity as MainActivity).supportActionBar?.apply {
            title = characterName
            setDisplayHomeAsUpEnabled(true)
            setHasOptionsMenu(true)
        }

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.characterDetailComicsList.layoutManager = layoutManager
        binding.characterDetailComicsList.adapter = comicsListAdapter
        comicsListAdapter.onDetailItemClick = {
            goToUrl(it)
        }
        comicsListAdapter.onPurchaseItemClick = {
            goToUrl(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            navController.navigate(R.id.characterListFragment)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initObservers() {
        viewModel.characterData.observe(viewLifecycleOwner) {
            fillCharacterData(it)
        }

        viewModel.comicsData.observe(viewLifecycleOwner) {
            fillComicsData(it)
        }
    }

    private fun initListeners() {
    }

    private fun callAPI() {
        viewModel.getCharacterDetail(characterID)
        viewModel.getComics(characterID)
    }

    private fun fillCharacterData(item: MarvelCharacter) {
        binding.characterDetailAvatar.loadUrl(
            "${item.thumbnail.path}/landscape_xlarge.${item.thumbnail.extension}",
            R.drawable.portrait_xlarge
        )

        binding.characterDetailName.text = item.name
        binding.characterDetailDescription.text =
            item.description.ifEmpty { getString(R.string.description_no_available) }
        binding.characterDetailMoreInfoButton.setOnClickListener { goToUrl(item.urlDetail) }
    }

    private fun fillComicsData(list: List<MarvelComic>) {
        comicsListAdapter.data.addAll(list)
        comicsListAdapter.notifyDataSetChanged()
    }

    private fun goToUrl(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }


}