package com.example.proyectokotlin

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var tvPantallaInicio: TextView
    lateinit var constraintLayout: ConstraintLayout
    lateinit var constraintPequenyo: ConstraintLayout
    lateinit var botonCambiar: Button

    val random = Random.Default
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v("MainActivity", "Hola como estamos")
        tvPantallaInicio = this.findViewById(R.id.textView)
        constraintLayout = this.findViewById(R.id.constraintLayout)
        constraintPequenyo = this.findViewById(R.id.constraintPequenyo)
        botonCambiar = this.findViewById(R.id.botonCambiar)

        botonCambiar.setOnClickListener({
            tvPantallaInicio.text = "Has cambiado el color de fondo"
            constraintPequenyo.setBackgroundColor(cambiarColor())
        })

    }
    fun cambiarColor(): Int{
        return Color.rgb(random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256))
    }
}