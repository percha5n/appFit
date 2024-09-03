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
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class Registrarse : AppCompatActivity() {

    private lateinit var regNombreLayout: TextInputLayout
    private lateinit var regNombreEditText: TextInputEditText
    private lateinit var regApellidoEditText: TextInputEditText
    private lateinit var nomEmailEditText: TextInputEditText
    private lateinit var contraseniaTextInputEditText: TextInputEditText
    private lateinit var confirmarContraseniaTextInputEditText: TextInputEditText
    private lateinit var btnReg: MaterialButton
    private lateinit var btnAtras: MaterialButton

    private lateinit var firebaseAuth: FirebaseAuth

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
        cambiarColorDelTexto()

        btnReg.setOnClickListener(){
            var pass1 = contraseniaTextInputEditText.text.toString()
            var pass2 = confirmarContraseniaTextInputEditText.text.toString()

            if(pass1.equals(pass2)){
                 crearCuenta(nomEmailEditText.text.toString(),contraseniaTextInputEditText.text.toString())
            } else{
                Toast.makeText(baseContext, "Error: las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                contraseniaTextInputEditText.requestFocus()
            }

            btnAtras.setOnClickListener{
                atras()
            }

        }
    }
    //meter el nombre y apellido para crear la cuenta y registrarlo en la base de datos
    private fun crearCuenta(email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
            task ->
            if(task.isSuccessful){ //validar que el email y pass no sea null
                Toast.makeText(baseContext, "Cuenta creada", Toast.LENGTH_SHORT).show()
                //poner para que salga del activiti y se pueda logear
            }else{
                Toast.makeText(baseContext, "algo salio mal,Error" + task.exception, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun cambiarColorDelTexto(){
        regNombreEditText.setTextColor(ContextCompat.getColor(this, R.color.white))
        regApellidoEditText.setTextColor(ContextCompat.getColor(this,R.color.white))
        nomEmailEditText.setTextColor(ContextCompat.getColor(this,R.color.white))
        contraseniaTextInputEditText.setTextColor(ContextCompat.getColor(this,R.color.white))
        confirmarContraseniaTextInputEditText.setTextColor(ContextCompat.getColor(this,R.color.white))
    }

    private fun iniciarComponentes(){
        regNombreLayout = findViewById(R.id.regNombreLayout)
        regNombreEditText = findViewById(R.id.regNombreEditText)
        regApellidoEditText = findViewById(R.id.regApellidoEditText)
        nomEmailEditText = findViewById(R.id.nomEmailEditText)
        contraseniaTextInputEditText = findViewById(R.id.contraseniaTextInputEditText)
        confirmarContraseniaTextInputEditText = findViewById(R.id.confirmarContraseniaTextInputEditText)
        btnReg = findViewById(R.id.btnReg)
        btnAtras = findViewById(R.id.btnAtras)

        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun atras() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
    }

}