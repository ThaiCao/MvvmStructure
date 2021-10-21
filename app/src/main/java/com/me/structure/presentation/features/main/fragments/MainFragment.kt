package com.me.structure.presentation.features.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.me.structure.R
import com.me.structure.bases.BaseFragment
import com.me.structure.databinding.FragmentMainBinding
import com.me.structure.presentation.features.main.adapter.MainAdapter
import com.me.structure.presentation.features.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment(), BottomNavigationView.OnNavigationItemSelectedListener {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModel()
    private var mainAdapter: MainAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        _binding?.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        handleClick()
        initListener()
    }

    override fun onResume() {
        super.onResume()
//        initView()
    }

    private fun initView() {
        mainAdapter = activity?.let { MainAdapter(it) }
        binding.viewPager.adapter = mainAdapter
        binding.viewPager.offscreenPageLimit = 5
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
        binding.viewPager.isUserInputEnabled = false
        binding.navView.setOnNavigationItemSelectedListener(this)
    }

    private fun initListener() {

    }

    private fun handleClick() {

    }

    var pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            binding.navView.menu.getItem(position).isChecked = true
        }
    }

    companion object {
        fun newInstance(args: Bundle?) = MainFragment().apply {
            arguments = args
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.currentItem = 0
                return true
            }
            R.id.navigation_recent -> {
                viewPager.currentItem = 1
                return true
            }
            R.id.navigation_favorite -> {
                viewPager.currentItem = 2
                return true
            }
            R.id.navigation_more -> {
                viewPager.currentItem = 3
                return true
            }
            else -> return true
        }
    }
}