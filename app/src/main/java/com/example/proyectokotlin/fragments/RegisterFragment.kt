package com.example.proyectokotlin.fragments

import android.content.Intent
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
import com.example.proyectokotlin.MainActivity
import com.example.proyectokotlin.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
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
        when (v?.id) {
            R.id.botonAceptarRegistro -> {
                if (checkFields()) {
                    Snackbar.make(requireView(), "Por favor, completa todos los campos.", Snackbar.LENGTH_SHORT).show()
                }

                else if(!checkPassword()){
                    Snackbar.make(requireView(), "Las contraseñas deben ser iguales", Snackbar.LENGTH_SHORT).show()
                }

                else {
                    auth.createUserWithEmailAndPassword(textoEmailRegistro.text.toString(), textoPasswordRegistro.text.toString())
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {
                                val activity1: Intent = Intent(requireActivity(), MainActivity::class.java)
                                startActivity(activity1)
                                requireActivity().finish()
                            } else {
                                val exception = task.exception
                                if (exception is FirebaseAuthException) {
                                    val errorMessage = when (exception.errorCode) {
                                        "ERROR_WEAK_PASSWORD" -> "La contraseña es débil. Debe tener al menos 8 caracteres."
                                        "ERROR_INVALID_CREDENTIAL" -> "Credenciales inválidas. Revisa el formato del correo electrónico."
                                        "ERROR_EMAIL_ALREADY_IN_USE" -> "Esta cuenta ya está en uso."
                                        else -> "Error al crear la cuenta. Por favor, inténtalo nuevamente."
                                    }
                                    Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_SHORT).show()
                                } else {
                                    Snackbar.make(requireView(), "Error al crear la cuenta. Por favor, inténtalo nuevamente.", Snackbar.LENGTH_SHORT).show()
                                }
                            }
                        }
                }
            }
            R.id.botonCancelarRegistro -> navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun checkFields(): Boolean {
        return textoEmailRegistro.text.toString().isEmpty() || textoPasswordRegistro.text.toString().isEmpty() ||
                textoRepetirPasswordRegistro.text.toString().isEmpty()
    }
    private fun checkPassword(): Boolean {
        return textoPasswordRegistro.text.toString().equals(textoRepetirPasswordRegistro.text.toString())
    }
}