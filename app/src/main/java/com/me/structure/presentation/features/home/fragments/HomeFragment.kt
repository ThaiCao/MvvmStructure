package com.me.structure.presentation.features.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyVisibilityTracker
import com.me.structure.R
import com.me.structure.bases.BaseFragment
import com.me.structure.databinding.FragmentHomeBinding
import com.me.structure.presentation.features.home.controller.MovieController
import com.me.structure.presentation.features.home.listener.OnMovieListener
import com.me.structure.presentation.features.home.viewmodel.HomeViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), OnMovieListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    val homeViewModel: HomeViewModel by viewModel()
    var controller: MovieController? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        _binding?.lifecycleOwner = this
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDeviceController()
        initRecyclerView()
        handleListener()
        homeViewModel.getListMovie()
    }

    private fun initDeviceController() {
        controller = MovieController(this)
    }

    private fun initRecyclerView() {
        val epoxyVisibilityTracker = EpoxyVisibilityTracker()
        binding.rvMovies.let { epoxyVisibilityTracker.attach(it) }

        val layoutManager = GridLayoutManager(context, 2)
        binding.rvMovies.layoutManager = layoutManager
        binding.rvMovies.adapter = controller?.adapter
    }

    private fun handleListener() {
        homeViewModel.listMovie.observe(viewLifecycleOwner) {
            android.util.Log.e("TEST_DATA","getListMovie refresh")
            controller?.refresh(it)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun goToMovieDetail(id: String) {
        TODO("Not yet implemented")
    }
}