package com.example.favourite.fragments.favourite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.ui.MarvelAdapter
import com.example.dicoding_made_2022.databinding.FragmentFavouriteBinding
import com.example.dicoding_made_2022.di.FavouriteModuleDependencies
import com.example.dicoding_made_2022.ui.activities.detail.DetailActivity
import com.example.favourite.di.DaggerFavouriteComponent
import com.example.favourite.di.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavouriteFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favouriteViewModel: FavouriteViewModel by viewModels {
        factory
    }

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavouriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavouriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val marvelEventAdapter = MarvelAdapter()
            marvelEventAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            favouriteViewModel.favouriteMarvelEvent.observe(viewLifecycleOwner) { dataMarvelEvent ->
                marvelEventAdapter.setData(dataMarvelEvent)
                binding.viewEmpty.root.visibility =
                    if (dataMarvelEvent.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvFavouriteMarvelEvent) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = marvelEventAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}