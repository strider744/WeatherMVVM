package com.strider.weathermvvm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.strider.weathermvvm.R
import com.strider.weathermvvm.databinding.FragmentHomeBinding
import com.strider.weathermvvm.utils.observeNotNull
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment: Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by activityViewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        Timber.e("qwe")
        viewModel.allWeatherList.observeNotNull(this) { list ->
            list.forEach {
                Timber.e("qwe ALL_WEATHER ${it.cityId} ${it.descriptions.firstOrNull()?.description}")
            }
        }
    }
}