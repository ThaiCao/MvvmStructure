package com.me.structure.bases

import com.me.structure.R
import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.tbruyelle.rxpermissions3.RxPermissions
import java.util.concurrent.TimeUnit

abstract class BaseLocationFragment : BaseFragment() {

    // location
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    val lastLocation = MutableLiveData<Location>()
    val deniedPermission = MutableLiveData<Boolean>()
    var rxPermissions: RxPermissions? = null
    private lateinit var locationSettingsRequest: LocationSettingsRequest
    private lateinit var locationRequest: LocationRequest
    private lateinit var settingsClient: SettingsClient
    private var enableGPS = false
    private val GPS_REQUEST = 123

    protected fun initGoogleLocation() {
        initLocationRequest()
        handleLogicGetLocation()
    }

    private fun handleLogicGetLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            && grantedGPS()
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener {
                lastLocation.value = it
            }
        } else {
            requestLocationUpdates()
        }
    }

    private fun initLocationRequest() {
        settingsClient = LocationServices.getSettingsClient(requireContext())
        locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 20 * 1000
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
        locationSettingsRequest = builder.build()
        builder.setAlwaysShow(true)
        enableGPS = grantedGPS()
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    protected fun requestLocationUpdates() {
        if (grantedLocationPermissions()) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            if (!grantedGPS()) {
                showAlertAskGPS()
            } else {
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            super.onLocationResult(locationResult)
                            lastLocation.value = locationResult.lastLocation
                            fusedLocationClient.removeLocationUpdates(this)
                        }

                        override fun onLocationAvailability(p0: LocationAvailability?) {
                            super.onLocationAvailability(p0)
                        }
                    },
                    Looper.myLooper()
                )
            }

        } else {
            requestPermissions()
        }
    }


    fun requestPermissions() {

        rxPermissions = RxPermissions(this)

        rxPermissions?.requestEach(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
            ?.throttleFirst(1000, TimeUnit.MILLISECONDS)
            ?.subscribe { permission ->  // will emit 2 Permission objects
                if (permission.granted) {
                    requestLocationUpdates()
                } else if (permission.shouldShowRequestPermissionRationale) {
                    // Denied permission without ask never again
                    deniedPermission.value = true

                } else {
                    // Denied permission with ask never again
                    // Need to go to the settings
                    deniedPermission.value = true
                    showDialogGoToSetting()
                }
            }
    }

    private fun showDialogGoToSetting() {
        val alertDialog = activity?.let { AlertDialog.Builder(it, R.style.AlertDialog) }
        alertDialog?.setMessage(R.string.lbl_open_setting)
        alertDialog?.setPositiveButton(R.string.lbl_setting) { dialog, _ ->
            openDetailSettings(dialog)
        }
        alertDialog?.setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog?.create()?.show()
    }

    private fun openDetailSettings(dialog: DialogInterface) {
        if (activity != null && isAdded) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:" + context?.packageName)
            startActivity(intent)
            dialog.dismiss()
        }
    }

    protected fun grantedLocationPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun grantedGPS(): Boolean {
        val locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun showAlertAskGPS(): Boolean {
        val enable = grantedGPS()
        if (!grantedGPS()) {

            settingsClient
                .checkLocationSettings(locationSettingsRequest)
                .addOnSuccessListener(requireActivity()) {
                    enableGPS = true
                }

                .addOnFailureListener(requireActivity()) { e ->
                    when ((e as ApiException).statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                            if (activity != null) {
                                try {
                                    val rae = e as ResolvableApiException
                                    rae.startResolutionForResult(requireActivity(), GPS_REQUEST)
                                    deniedPermission.postValue(true)
                                } catch (ex: IntentSender.SendIntentException) {
                                }
                            }
                        }

                        LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                            Toast.makeText(context, "Please Turn On GPS", Toast.LENGTH_LONG).show()
                        }
                    }
                }
        } else {
            requestLocationUpdates()
        }
        return enable
    }

}