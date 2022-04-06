package com.jmanrique.marvelproject.app.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmanrique.marvelproject.R
import com.jmanrique.marvelproject.app.ui.base.BaseActivity
import com.jmanrique.marvelproject.databinding.ActivityCharacterDetailBinding
import com.jmanrique.marvelproject.domain.model.characters.MarvelCharacter
import com.jmanrique.marvelproject.domain.model.comics.MarvelComic
import com.jmanrique.marvelproject.utils.extensions.loadUrl
import com.jmanrique.marvelproject.utils.extensions.safeValue
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CharacterDetailActivity : BaseActivity<ActivityCharacterDetailBinding>() {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private var characterName: String = ""
    private var characterID: Int = 0

    @Inject
    lateinit var comicsListAdapter: CharacterDetailComicsListAdapter

    override fun bindViewToModel() {
        binding.viewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getBundleExtras()
        initViews()
        initObservers()
        initListeners()
        callAPI()
    }

    private fun getBundleExtras() {
        characterName = intent.extras.let{ intent.extras!!.getString("characterName").safeValue()}
        characterID = intent.extras.let { intent.extras!!.getInt("characterID") }
    }

    private fun initViews() {

        supportActionBar?.apply {
            title = characterName
            setDisplayShowHomeEnabled(true);
            setDisplayHomeAsUpEnabled(true);
        }

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.characterDetailComicsList.layoutManager = layoutManager
        binding.characterDetailComicsList.adapter = comicsListAdapter
        comicsListAdapter.onDetailItemClick = {
            goToUrl(it)
        }
        comicsListAdapter.onPurchaseItemClick = {
            goToUrl(it)
        }
    }

    private fun initObservers() {
        viewModel.characterData.observe(this) {
            fillCharacterData(it)
        }

        viewModel.comicsData.observe(this) {
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

    override fun inflate(layoutInflater: LayoutInflater): ActivityCharacterDetailBinding =
        ActivityCharacterDetailBinding.inflate(layoutInflater)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
           android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}