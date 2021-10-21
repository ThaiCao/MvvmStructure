package com.me.structure.presentation.features.favorite.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.me.structure.R
import com.me.structure.bases.BaseFragment
import com.me.structure.databinding.FragmentFavoriteBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

class FavoriteFragment : BaseFragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        _binding?.lifecycleOwner = this
        return binding.root
    }
}