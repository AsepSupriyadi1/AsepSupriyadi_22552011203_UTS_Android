package com.asepsupriyadi22552011203.asepexamapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegistrationActivity : AppCompatActivity() {

    private lateinit var loginUrl : TextView
    private lateinit var btnRegistration : Button

    private lateinit var txtNim : EditText
    private lateinit var txtName : EditText

    private lateinit var radioGenderGroup : RadioGroup
    private lateinit var selectedRadioButton: RadioButton

    private lateinit var txtPassword : EditText
    private lateinit var txtCPassword : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        loginUrl = findViewById(R.id.login_url)
        btnRegistration = findViewById(R.id.btn_registration)

        txtNim = findViewById(R.id.signup_nim)
        txtName = findViewById(R.id.signup_name)

        radioGenderGroup = findViewById(R.id.signup_gender)

        txtPassword = findViewById(R.id.signup_password)
        txtCPassword = findViewById(R.id.signup_cpassword)

        loginUrl.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Log.v("Global", GlobalVariable.globalText)
        }

        btnRegistration.setOnClickListener {
            val txtNimReq =  txtNim.text.toString();
            val txtNameReq =  txtName.text.toString();

            val selectedOption: Int = radioGenderGroup.checkedRadioButtonId
            selectedRadioButton = findViewById(selectedOption)
            val txtGenderReq = selectedRadioButton.text.toString()

            val txtPasswordReq =  txtPassword.text.toString()
            val txtCPasswordReq =  txtCPassword.text.toString()

            Log.v("txtNimReq", txtNimReq)
            Log.v("txtNameReq", txtNameReq)
            Log.v("txtGenderReq", txtGenderReq)
            Log.v("txtPasswordReq", txtPasswordReq)
            Log.v("txtCPasswordReq", txtCPasswordReq)

            val isContainEmpty = containsEmpty(
                txtNimReq,
                txtNameReq,
                txtPasswordReq,
                txtCPasswordReq
            )

            val isPasswordMatched = checkConfirmPassword(
                txtPasswordReq,
                txtCPasswordReq
            )

            if(isContainEmpty) {
                Toast.makeText(applicationContext,
                    "Harap isi semua field yang tersedia!", Toast.LENGTH_SHORT).show()
            } else if (!isPasswordMatched) {
                Toast.makeText(applicationContext,
                    "Confirm password tidak sesuai!", Toast.LENGTH_SHORT).show()
            } else {
                GlobalVariable.addNewData(
                    txtNimReq,
                    txtNameReq,
                    txtGenderReq,
                    txtPasswordReq,
                );

                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("register_status", "register_success")
                startActivity(intent)
            }


        }


    }


    private fun containsEmpty (
        reqTxtNim : String,
        reqTxtName: String,
        reqTxtPassword : String,
        reqTxtCPassword : String
    ) : Boolean {
        return (reqTxtNim == "" || reqTxtName == "" || reqTxtPassword == "" || reqTxtCPassword == "")
    }

    private fun checkConfirmPassword (
        reqTxtPassword : String,
        reqTxtCPassword : String
    ) : Boolean {
        return (reqTxtPassword == reqTxtCPassword)
    }
}