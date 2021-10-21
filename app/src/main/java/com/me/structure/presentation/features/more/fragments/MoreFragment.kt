package com.me.structure.presentation.features.more.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.me.structure.R
import com.me.structure.bases.BaseFragment
import com.me.structure.databinding.FragmentMoreBinding
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MoreFragment : BaseFragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_more, container, false)
        _binding?.lifecycleOwner = this
        return binding.root
    }
}