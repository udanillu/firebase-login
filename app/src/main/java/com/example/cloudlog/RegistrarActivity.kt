package com.example.cloudlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrarActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val txtLogarUsuario: TextView = findViewById(R.id.txtLogar)
        txtLogarUsuario.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnRegistrar: Button = findViewById(R.id.btnRegister)
        btnRegistrar.setOnClickListener {
            performedRegistrar()
        }
    }

    private fun performedRegistrar() {
        val edtEmail: EditText = findViewById(R.id.edtEmail)
        val edtSenha: EditText = findViewById(R.id.edtPassword)

        val inputEmail = edtEmail.text.toString()
        val inputPassword = edtSenha.text.toString()

        if ( inputEmail.isEmpty() || inputPassword.isEmpty() ){
            Toast.makeText(this, "Login ou Senha está em Branco!", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Registrado!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {

                }
            }
    }
}