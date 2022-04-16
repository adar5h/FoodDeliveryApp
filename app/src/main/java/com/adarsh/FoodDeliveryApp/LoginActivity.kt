package com.adarsh.FoodDeliveryApp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adarsh.FoodDeliveryApp.session.LoginPref
import com.adarsh.FoodDeliveryApp.MainActivity
import com.demo.foodorderanddeliveryappkotlin.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginBtn: Button

    lateinit var session: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        session = LoginPref(this)

        if(session.isLoggedIn()){
            var i : Intent = Intent(applicationContext, MainActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()
        }

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        loginBtn = findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener{
            var email = email.text.toString().trim()
            var password = password.text.toString().trim()

            if(email.isEmpty() && password.isEmpty()){

//                session.createLoginSession(email, password)
//                var i : Intent = Intent(applicationContext, MainActivity::class.java)
//                startActivity(i)
//                finish()
                Toast.makeText(this, "Login failed! Please try again.", Toast.LENGTH_SHORT).show()
            }else{
                session.createLoginSession(email, password)
                var i : Intent = Intent(applicationContext, LoggedIn::class.java)
                startActivity(i)
                finish()
//                Toast.makeText(this, "Login failed! Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signup(view: View) {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }


}