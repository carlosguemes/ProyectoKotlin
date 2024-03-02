package com.example.proyectokotlin.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
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

class LoginFragment : Fragment(), View.OnClickListener {
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

        textoEmail = view.findViewById(R.id.textoEmail)
        textoPassword = view.findViewById(R.id.textoPassword)
        botonAceptar = view.findViewById(R.id.botonAceptarLogin)
        botonRegistro = view.findViewById(R.id.botonRegistroLogin)

        botonAceptar.setOnClickListener(this)
        botonRegistro.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.botonAceptarLogin -> {
                if (checkFields()) {
                    Snackbar.make(requireView(), "Por favor, completa todos los campos.", Snackbar.LENGTH_SHORT).show()
                } else {
                    auth.signInWithEmailAndPassword(textoEmail.text.toString(), textoPassword.text.toString())
                        .addOnCompleteListener(requireActivity()) { task ->
                            if (task.isSuccessful) {
                                val activity1: Intent = Intent(requireActivity(), MainActivity::class.java)
                                startActivity(activity1)
                                requireActivity().finish()
                            } else {
                                val exception = task.exception
                                if (exception is FirebaseAuthException) {
                                    val errorMessage = when (exception.errorCode) {
                                        "ERROR_INVALID_EMAIL" -> "Correo electrónico inválido."
                                        "ERROR_WRONG_PASSWORD" -> "Contraseña incorrecta."
                                        "ERROR_USER_NOT_FOUND" -> "No hay ninguna cuenta asociada a este correo electrónico."
                                        "ERROR_USER_DISABLED" -> "Esta cuenta ha sido deshabilitada."
                                        else -> "Error al iniciar sesión. Por favor, inténtalo nuevamente."
                                    }
                                    Snackbar.make(requireView(), errorMessage, Snackbar.LENGTH_SHORT).show()
                                } else {
                                    Snackbar.make(requireView(), "Error al iniciar sesión. Por favor, inténtalo nuevamente.", Snackbar.LENGTH_SHORT).show()
                                }
                            }
                        }

                    /*auth.createUserWithEmailAndPassword(textoEmail.text.toString(), textoPassword.text.toString())
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
                        }*/
                }
            }
            R.id.botonRegistroLogin -> navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
    private fun checkFields(): Boolean {
        return textoEmail.text.toString().isEmpty() || textoPassword.text.toString().isEmpty()
    }

}