package com.example.proyectokotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.proyectokotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var textoEmail: EditText
    lateinit var textoPassword: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController
    lateinit var botonAceptar: Button
    lateinit var botonRegistro: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        navController = findNavController()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textoEmail = view.findViewById(R.id.textEmail)
        textoPassword = view.findViewById(R.id.textPassword)
        botonAceptar = view.findViewById(R.id.botonAceptarLogin)
        botonRegistro = view.findViewById(R.id.botonRegistroLogin)

    }

    fun botonAceptarLogin(){

    }

    fun botonRegistroLogin(){

    }


}