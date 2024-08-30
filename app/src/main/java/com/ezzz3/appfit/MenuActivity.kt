package com.ezzz3.appfit

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*var botonSaludarApp=findViewById<AppCompatButton>(R.id.botonSaludarApp)*/
        var botonIMCApp=findViewById<AppCompatButton>(R.id.botonIMCApp)
        botonIMCApp.setTextColor(ContextCompat.getColor(this, R.color.white))
        //botonSaludarApp.setOnClickListener { navegacionDelBotonSaludar() }
        botonIMCApp.setOnClickListener { navegacionDelImcApp() }

    }

    private fun navegacionDelImcApp(){
        val intent = Intent(this, imcCalculatorActivity::class.java)
        startActivity(intent)
    }

    private fun navegacionDelBotonSaludar(){
        //var intent = Intent(this, primeraAppActivity::class.java)
        //startActivity(intent)
    }

}