package com.example.kedi.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kedi.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    lateinit var loadingDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar!!.hide()
        configureLoadingDialog()

        setContentView(R.layout.activity_login)
        setListeners()
    }

    private fun setListeners(){
        //todo: check data login
        buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login(){
        val email = input_email.text.toString()
        val password = input_password.text.toString()

        if(checkLogin(email, password)){
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            input_layout_email.setError("")
            input_layout_password.setError("Usuario o contraseña incorrectas")
        }
    }
    private fun checkLogin(email: String, password: String): Boolean{
        //todo: check login on backend
        showLoadingDialog()
        hideLoadingDialog()
        return true
    }

    private fun configureLoadingDialog(){
        loadingDialog = ProgressDialog(this)
        loadingDialog.setMessage("Loading..")
        loadingDialog.setTitle("Comprobando datos..")
        loadingDialog.isIndeterminate = false
        loadingDialog.setCancelable(true)
    }

    private fun showLoadingDialog(){
        loadingDialog.show()
    }

    private fun hideLoadingDialog(){
        loadingDialog.hide()
    }
}