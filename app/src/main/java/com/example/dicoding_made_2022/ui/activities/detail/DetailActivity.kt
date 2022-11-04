package com.example.dicoding_made_2022.ui.activities.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.core.domain.model.MarvelEvent
import com.example.dicoding_made_2022.R
import com.example.dicoding_made_2022.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val detailMarvelEvent = intent.getParcelableExtra<MarvelEvent>(EXTRA_DATA)
        showDetailMarvelEvent(detailMarvelEvent)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showDetailMarvelEvent(detailMarvel: MarvelEvent?) {
        val context = this@DetailActivity
        detailMarvel?.let {
            binding.toolbar.title = detailMarvel.title
            binding.contentDetailMarvel.apply {
                tvTitleMarvelEvent.text = detailMarvel.title
                tvDescription.text = detailMarvel.description
                Glide.with(context)
                    .load(detailMarvel.thumbnail?.path + "." + detailMarvel.thumbnail?.extension)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(posterMarvel)
                val url = detailMarvel.urls?.get(0)?.url
                tvLink.text = url
                val uris = Uri.parse(url)
                val intents = Intent(Intent.ACTION_VIEW, uris)
                val b = Bundle()
                b.putBoolean("new_window", true)
                intents.putExtras(b)
                tvLink.setOnClickListener {
                    context.startActivity(intents)
                }
            }

            var statusFavorite = detailMarvel.isFavorite
            setStatusFavorite(statusFavorite)

            binding.addToFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMarvelEvent(detailMarvel, statusFavorite)
                setStatusFavorite(statusFavorite)
                showSnackBar(statusFavorite, detailMarvel.title.toString())
            }

        }
    }

    private fun showSnackBar(state: Boolean, name: String) {
        val msg = if (state) {
            "$name " + getString(R.string.added_to_favorite)
        } else {
            "$name " + getString(R.string.removed_from_favorite)
        }
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG)
            .show()
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.addToFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite
                )
            )
        } else {
            binding.addToFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border
                )
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_DATA = "marvelEvent"
    }
}