package com.example.chatappfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val register_button = findViewById<Button>(R.id.register_button)
        val already_have_an_account = findViewById<TextView>(R.id.already_have_an_account_textView)
        register_button.setOnClickListener {
            val email = findViewById<TextView>(R.id.email_editText_register)
            val password = findViewById<TextView>(R.id.password_editText_register)

            Log.d("MainActivity", "Email is ${email.text.toString()}")
            Log.d("MainActivity", "Password is ${password.text.toString()}")

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(),password.text.toString())
                .addOnCompleteListener{
                    if (!it.isSuccessful) return@addOnCompleteListener
                    Log.d("MainActivity", "Account successfully created with uid : ${it.result?.user?.uid}")
                }
        }
        already_have_an_account.setOnClickListener {
            Log.d("MainActivity", "Try to show log activity")
            val intent = Intent(this, LoginActivity :: class.java)
            startActivity(intent)
        }
    }
}