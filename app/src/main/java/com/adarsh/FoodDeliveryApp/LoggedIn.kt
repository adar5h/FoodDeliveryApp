package com.adarsh.FoodDeliveryApp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adarsh.FoodDeliveryApp.session.LoginPref
import com.adarsh.FoodDeliveryApp.MainActivity
import com.demo.foodorderanddeliveryappkotlin.R
import kotlinx.android.synthetic.main.activity_login.*

class LoggedIn : AppCompatActivity(){

    private lateinit var emailTv : TextView
    private lateinit var logoutBtn: Button
    lateinit var session: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)

        session = LoginPref(this)

        emailTv = findViewById(R.id.emailTv)
        logoutBtn = findViewById(R.id.logoutBtn)

        session.checkLogin()

        var user: HashMap<String, String> = session.getUserDetails()

        var email = user.get(LoginPref.KEY_USERNAME)

        emailTv.setText(email)

        logoutBtn.setOnClickListener{
            session.LogoutUser()
        }


//        redirect.setOnClickListener{
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
    }

    fun rests(view: View) {
        val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
    }

    fun map(view: View) {
        val intent = Intent(this, DeliveryAgent::class.java)
        startActivity(intent)
        Toast.makeText(this, "Searching for available order services", Toast.LENGTH_LONG).show()
    }


}