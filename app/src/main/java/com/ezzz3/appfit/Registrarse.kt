package com.ezzz3.appfit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Registrarse : AppCompatActivity() {

    private lateinit var regNombreLayout: TextInputLayout
    private lateinit var regNombreEditText: TextInputEditText
    private lateinit var regApellidoEditText: TextInputEditText
    private lateinit var nomEmailEditText: TextInputEditText
    private lateinit var contraseniaTextInputEditText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrarse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        iniciarComponentes()

        //cambia el color deafault del hint
        //regNombreLayout.defaultHintTextColor = ContextCompat.getColorStateList(this,R.color.white)

        regNombreEditText.setTextColor(ContextCompat.getColor(this, R.color.white))
        regApellidoEditText.setTextColor(ContextCompat.getColor(this,R.color.white))
        nomEmailEditText.setTextColor(ContextCompat.getColor(this,R.color.white))
        contraseniaTextInputEditText.setTextColor(ContextCompat.getColor(this,R.color.white))



    }

    private fun iniciarComponentes(){
        regNombreLayout = findViewById(R.id.regNombreLayout)
        regNombreEditText = findViewById(R.id.regNombreEditText)
        regApellidoEditText = findViewById(R.id.regApellidoEditText)
        nomEmailEditText = findViewById(R.id.nomEmailEditText)
        contraseniaTextInputEditText = findViewById(R.id.contraseniaTextInputEditText)

    }

}