package com.strider.weathermvvm.ui

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.*
import androidx.navigation.ui.setupActionBarWithNavController
import autodispose2.androidx.lifecycle.scope
import autodispose2.autoDispose
import com.strider.weathermvvm.R
import com.strider.weathermvvm.databinding.ActivityMainBinding
import com.strider.weathermvvm.ui.home.HomeViewModel
import com.strider.weathermvvm.utils.observeNotNull
import com.tbruyelle.rxpermissions3.RxPermissions
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)



        navController.addOnDestinationChangedListener { controller, destination, arguments ->

        }

        requestPermissions()

        viewModel.currentWeather.observeNotNull(this) { it ->
                Timber.e("qwe ALL_WEATHER ${it.cityId} ${it.descriptions.firstOrNull()?.description}")
        }
    }

    private fun requestPermissions() {
        RxPermissions(this).request(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
            .autoDispose(scope())
            .subscribe { isGranted ->
                if (isGranted) {
                    viewModel.updateLocation()
                } else {
                    requestPermissions()
                }
            }
    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}