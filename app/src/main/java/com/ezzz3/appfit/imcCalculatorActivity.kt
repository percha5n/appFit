package com.ezzz3.appfit

import android.content.Intent
import android.health.connect.datatypes.WeightRecord
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class imcCalculatorActivity : AppCompatActivity() {
    //declaracion de las variables globales
    private var isMaleSelected: Boolean = true
    private var isFamaleSelected: Boolean = false
    private var currentWeight: Int = 75
    private var currentAge: Int = 19
    private var currentHeight: Int = 120

    //declaracion de los procedimientos globales
    private lateinit var viewMale: CardView //inicializacion global para que se usen en todos los metodos
    private lateinit var viewFemale: CardView // el lateinit es para que se inicie cuando yo lo decida
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeightRecord: TextView
    private lateinit var btnSubAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button
    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
        initUI()

    }

    //declaracion de las funciones
    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeightRecord = findViewById(R.id.tvWeight)
        btnSubAge = findViewById(R.id.btnSubAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    //lo que tienen que hacer cada funcion
    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt() //formatea el decimal
            tvHeight.text = "$currentHeight cm"
        }
        btnPlusWeight.setOnClickListener {
            currentWeight +=1
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            currentWeight -=1
            setWeight()
        }
        btnPlusAge.setOnClickListener{
            currentAge +=1
            setAge()
        }
        btnSubAge.setOnClickListener{
            currentAge -=1
            setAge()
        }
        btnCalculate.setOnClickListener(){
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun calculateIMC():Double{
        var df = DecimalFormat("#.##")
        val imc = currentWeight/(currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()
        Log.i("IMC", "el imc es $imc")
    }


    private fun setAge(){
        tvAge.text = currentAge.toString()
    }

    private fun setWeight(){
        tvWeightRecord.text = currentWeight.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFamaleSelected = !isFamaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFamaleSelected))
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, resultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun getBackgroundColor(isSelectComponent: Boolean): Int {

        val colorReference = if (isSelectComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }

}