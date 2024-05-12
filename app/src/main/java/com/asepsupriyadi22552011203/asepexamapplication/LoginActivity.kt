package com.asepsupriyadi22552011203.asepexamapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var registrationUrl : TextView
    private lateinit var btnLogin : Button

    private lateinit var txtNim : EditText
    private lateinit var txtPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val receiptStatus : String? = intent.getStringExtra("register_status")

        if(receiptStatus != null && receiptStatus == "register_success"){
            Toast.makeText(applicationContext,
                "Akun berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
        }


        registrationUrl = findViewById(R.id.registrationURl)
        txtNim = findViewById(R.id.login_nim)
        txtPassword = findViewById(R.id.login_password)
        btnLogin = findViewById(R.id.btn_login)

        btnLogin.setOnClickListener{
            val txtNimReq =  txtNim.text.toString();
            val txtPasswordReq =  txtPassword.text.toString();

            val containsEmpty = mapLoginData(
                txtNimReq,
                txtPasswordReq
            )

            if(!containsEmpty){

                Log.v("Jumlah Data","JUMLAH DATA ${GlobalVariable.getTotalRows()}" )

                val isAuthenticated = GlobalVariable.authenticatedAccount(
                    txtNimReq,
                    txtPasswordReq
                )

                if(!isAuthenticated) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext,
                        "NIM / Password salah", Toast.LENGTH_SHORT).show()
                }


            } else {
                Toast.makeText(applicationContext,
                    "Harap isi semua field yang tersedia!", Toast.LENGTH_SHORT).show()
            }

            Log.v("Activity", "LOGIN")
        }

        registrationUrl.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }

    private fun mapLoginData(reqTxtNim : String, reqTxtPassword : String) : Boolean {
        return (reqTxtNim == "" || reqTxtPassword == "");
    }

    private  fun openDashboard(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}