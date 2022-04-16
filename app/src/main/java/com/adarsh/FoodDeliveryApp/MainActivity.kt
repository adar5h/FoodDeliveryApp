package com.adarsh.FoodDeliveryApp

import android.content.Intent
import android.hardware.Camera.open
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor.open
import android.system.Os.close
import android.system.Os.open
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.foodorderanddeliveryappkotlin.R
import com.adarsh.FoodDeliveryApp.adapter.RestaurantListAdapter
import com.adarsh.FoodDeliveryApp.models.RestaurentModel
import com.adarsh.FoodDeliveryApp.session.LoginPref
import com.bumptech.glide.disklrucache.DiskLruCache.open
import com.demo.foodorderanddeliveryappkotlin.firstFragment
import com.demo.foodorderanddeliveryappkotlin.secondFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.Exception
import java.nio.channels.AsynchronousFileChannel.open
import java.nio.channels.AsynchronousServerSocketChannel.open
import java.nio.channels.AsynchronousSocketChannel.open
import java.nio.channels.DatagramChannel.open
import java.nio.channels.FileChannel.open
import java.nio.channels.Pipe.open
import java.nio.channels.Selector.open
import java.nio.channels.ServerSocketChannel.open
import java.nio.channels.SocketChannel.open
import com.demo.foodorderanddeliveryappkotlin.firstFragment.Companion as firstFragment1

class MainActivity : AppCompatActivity(), RestaurantListAdapter.RestaurantListClickListener {

    private lateinit var adapter: ArrayAdapter<*>
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setTitle("Restaurant List")

        val restaurantModel = getRestaurantData()
        initRecyclerView(restaurantModel)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView2)
//        val navController = findNavController(R.id.fragmentContainerView2)


//        bottomNavigationView.setupWithNavController(navController)

        bottomNavigationView.setOnNavigationItemReselectedListener {
                    val intent = Intent(this, LoggedIn::class.java)
                    startActivity(intent)
        }


//
    //        bottomNavigationView.setOnClickListener{
//            val intent = Intent(this, LoggedIn::class.java)
//            startActivity(intent)
//        }
//
//            val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
//            val navView : NavigationView = findViewById(R.id.nav_view)
//
//            toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open , R.string.close)
//            drawerLayout.addDrawerListener(toggle)
//            toggle.syncState()
////
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//            navView.setNavigationItemSelectedListener {
//                when(it.itemId){
//                    R.id.nav_home -> {
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                    }
//                    R.id.nav_profile -> {
//                        val intent = Intent(this, LoggedIn::class.java)
//                        startActivity(intent)
//                    }
//
//                    R.id.nav_logout -> {
//                        val intent = Intent(this, LoginActivity::class.java)
//                        startActivity(intent)
//                    }
//                }
//                true
//            }
    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        if(toggle.onOptionsItemSelected(item)){
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }

    private fun initRecyclerView(restaurantList: List<RestaurentModel?>?) {
        val recyclerViewRestaurant = findViewById<RecyclerView>(R.id.recyclerViewRestaurant)
        recyclerViewRestaurant.layoutManager = LinearLayoutManager(this)
        val adapter = RestaurantListAdapter(restaurantList, this)
        recyclerViewRestaurant.adapter =adapter
    }

    private fun getRestaurantData(): List<RestaurentModel?>? {
        val inputStream: InputStream = resources.openRawResource(R.raw.restaurent)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n : Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)

            }

        }catch (e: Exception){}
        val jsonStr: String = writer.toString()
        val gson = Gson()
        val restaurantModel = gson.fromJson<Array<RestaurentModel>>(jsonStr, Array<RestaurentModel>::class.java).toList()

        return restaurantModel
    }

    override fun onItemClick(restaurantModel: RestaurentModel) {
       val intent = Intent(this@MainActivity, RestaurantMenuActivity::class.java)
        intent.putExtra("RestaurantModel", restaurantModel)
        startActivity(intent)
    }

}
