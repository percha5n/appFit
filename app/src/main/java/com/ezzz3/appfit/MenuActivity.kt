package com.ezzz3.appfit

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MenuActivity : AppCompatActivity() {

    private lateinit var btnlogin: MaterialButton
    private lateinit var emailEditText: TextInputEditText
    private lateinit var errorTextView: MaterialTextView
    private lateinit var contraseniaEditText: TextInputEditText
    private lateinit var btnRegistrarse: MaterialButton

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        iniciarComponentes()
        cambiarColorDelTexto()

        btnlogin.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = contraseniaEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signIn(email, password)
            } else {
                errorTextView.text = "Por favor, completa todos los campos."
            }
        }

        btnRegistrarse.setOnClickListener{
            navegacionDelRegistro()
        }

    }

    private fun iniciarComponentes() {
        btnlogin = findViewById(R.id.btnLogin)
        emailEditText = findViewById(R.id.emailEditText)
        contraseniaEditText = findViewById(R.id.contraseniaEditText)
        errorTextView = findViewById(R.id.errorTextView)
        btnRegistrarse = findViewById(R.id.btnRegistrarse)
        firebaseAuth = Firebase.auth
        authStateListener = FirebaseAuth.AuthStateListener{auth ->
            if(firebaseAuth.currentUser != null){
                navegacionDelImcApp()
            }
        }
    }

    private fun signIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    Toast.makeText(baseContext, "Authentication Exitosa", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, imcCalculatorActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun cambiarColorDelTexto(){
        btnlogin.setTextColor(ContextCompat.getColor(this, R.color.white))
        emailEditText.setTextColor(ContextCompat.getColor(this, R.color.white))
        contraseniaEditText.setTextColor(ContextCompat.getColor(this,R.color.white))
    }


    private fun navegacionDelImcApp() {
        val intent = Intent(this, imcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navegacionDelRegistro() {
        val intent= Intent(this, Registrarse::class.java)
        startActivity(intent)
    }


}