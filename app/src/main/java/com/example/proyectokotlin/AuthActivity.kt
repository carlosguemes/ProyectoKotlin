package com.example.proyectokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var botonAceptar: Button
    lateinit var botonRegistro: Button
    lateinit var textoEmail: EditText
    lateinit var textoPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth
        botonAceptar = this.findViewById(R.id.botonAceptar)
        botonRegistro = this.findViewById(R.id.botonRegistro)
        textoEmail = this.findViewById(R.id.textEmail)
        textoPassword = this.findViewById(R.id.textPassword)
    }

    fun botonAceptar(){
        auth.signInWithEmailAndPassword(textoEmail.text.toString(), textoPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("LoginActivity", "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("LoginActivity", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
    }
}