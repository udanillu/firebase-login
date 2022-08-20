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

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val btnLogar: Button = findViewById(R.id.btnLogar)
        btnLogar.setOnClickListener {
            performedLogar()
        }

        val txtCadastrarUsuario: TextView = findViewById(R.id.txtRegister)
        txtCadastrarUsuario.setOnClickListener{
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }


    }

    private fun performedLogar() {
        val edtEmail: EditText = findViewById(R.id.edtEmail)
        val edtPassword: EditText = findViewById(R.id.edtPassword)

        val inputEmail = edtEmail.text.toString()
        val inputPassword = edtPassword.text.toString()

        if ( edtEmail.text.isEmpty() || edtPassword.text.isEmpty() ){
            Toast.makeText(this, "Login ou Senha Errados!", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Logado!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, RespostaActivity::class.java)
                    startActivity(intent)
                } else {

                }
            }

    }
}