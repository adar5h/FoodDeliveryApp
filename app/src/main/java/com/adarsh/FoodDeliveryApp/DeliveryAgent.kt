package com.adarsh.FoodDeliveryApp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.foodorderanddeliveryappkotlin.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class DeliveryAgent : AppCompatActivity() {

    lateinit var mapFragment: SupportMapFragment
    lateinit var googleMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_agent)

        mapFragment = supportFragmentManager.findFragmentById(R.id.mapz) as SupportMapFragment
        mapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it

            val location = LatLng(30.74,76.77)
            googleMap.addMarker(MarkerOptions().position(location).title("McDonalds"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,5f))

            val location1 = LatLng(31.74,77.77)
            googleMap.addMarker(MarkerOptions().position(location1).title("SlimDogs"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,5f))

            val location2 = LatLng(31.30,78.4)
            googleMap.addMarker(MarkerOptions().position(location2).title("Chicago's Pizza"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,5f))

            val location3 = LatLng(30.9,77.7)
            googleMap.addMarker(MarkerOptions().position(location3).title("Zaxby's"))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,5f))
        })

    }
}