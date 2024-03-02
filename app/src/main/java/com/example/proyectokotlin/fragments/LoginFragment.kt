package com.example.proyectokotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.proyectokotlin.R
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var textoEmail: EditText
    lateinit var textoPassword: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


}