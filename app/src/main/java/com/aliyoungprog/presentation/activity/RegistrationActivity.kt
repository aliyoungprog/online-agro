package com.aliyoungprog.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aliyoungprog.R
import com.aliyoungprog.data.database.MyFirebaseAuth
import com.aliyoungprog.domain.entity.User
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity(){

    private lateinit var mUserPassword: TextInputEditText
    private lateinit var mUserEmail: TextInputEditText

    private lateinit var btnSubmit: Button
    private val firebaseAuth = MyFirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }

    override fun onStart() {
        super.onStart()
        setVars()
        register()
    }

    private fun register(){
        btnSubmit.setOnClickListener{
            if (!haveInputsFilled()) return@setOnClickListener
            auth()
        }
    }

    private fun setVars(){
        mUserPassword = findViewById(R.id.user_password)
        mUserEmail = findViewById(R.id.user_mail)
        btnSubmit = findViewById(R.id.btn_submit)
    }

    private fun auth(){
        firebaseAuth.db_auth.createUserWithEmailAndPassword(mUserEmail.text.toString(), mUserPassword.text.toString()).addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(this, "User is created successfully", Toast.LENGTH_SHORT).show()
                val email = mUserEmail.text.toString().toLowerCase()
                val user = User(
                    email = email,
                )
                Firebase.firestore.collection("users").document(email).set(user)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Error, ${it.exception}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun haveInputsFilled(): Boolean {
        if (mUserEmail.text.isNullOrEmpty() || mUserPassword.text.isNullOrEmpty())
            return false
        return true
    }
}
