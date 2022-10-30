package com.example.dicoding_made_2022.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            homeViewModel.marvelEvent.observe(viewLifecycleOwner) { marvelEvent ->
                if (marvelEvent != null) {
                    when (marvelEvent) {
                        is Resource.Loading -> {
                            // binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            // binding.progressBar.visibility = View.GONE
                            // marvelEventAdapter.setData(marvelEvent.data)
                            Toast.makeText(context, "Anjay Success", Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Error -> {
                            Toast.makeText(context, "Anjay Error", Toast.LENGTH_SHORT).show()
                            // binding.progressBar.visibility = View.GONE
                            // binding.viewError.root.visibility = View.VISIBLE
                            // binding.viewError.tvError.text = marvelEvent.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }

//            with(binding.rvMarvelEvent) {
//                layoutManager = LinearLayoutManager(context)
//                setHasFixedSize(true)
//                adapter = marvelEventAdapter
//            }
        }
    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}