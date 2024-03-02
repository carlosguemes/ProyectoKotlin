package com.example.proyectokotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.proyectokotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment(), OnClickListener {

    lateinit var textoEmailRegistro: EditText
    lateinit var textoPasswordRegistro: EditText
    lateinit var textoRepetirPasswordRegistro: EditText
    lateinit var botonAceptarRegistro: Button
    lateinit var botonCancelarRegistro: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        navController = findNavController()
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textoEmailRegistro = view.findViewById(R.id.textoEmailRegistro)
        textoPasswordRegistro = view.findViewById(R.id.textoPasswordRegistro)
        textoRepetirPasswordRegistro = view.findViewById(R.id.textoRepetirContrasenya)
        botonAceptarRegistro = view.findViewById(R.id.botonAceptarRegistro)
        botonCancelarRegistro = view.findViewById(R.id.botonCancelarRegistro)

        botonAceptarRegistro.setOnClickListener(this)
        botonCancelarRegistro.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}