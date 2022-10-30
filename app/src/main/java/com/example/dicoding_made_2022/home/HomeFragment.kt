package com.example.dicoding_made_2022.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.core.data.Resource
import com.example.dicoding_made_2022.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            homeViewModel.marvelEvent.observe(viewLifecycleOwner) { tourism ->
                if (tourism != null) {
                    when (tourism) {
                        is Resource.Loading -> {
                            // binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            // binding.progressBar.visibility = View.GONE
                            // marvelEventAdapter.setData(tourism.data)
                        }
                        is Resource.Error -> {
                            // binding.progressBar.visibility = View.GONE
                            // binding.viewError.root.visibility = View.VISIBLE
                            // binding.viewError.tvError.text = tourism.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

//            with(binding.rvTourism) {
//                layoutManager = LinearLayoutManager(context)
//                setHasFixedSize(true)
//                adapter = tourismAdapter
//            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}