package com.example.mid

import android.content.Intent
import android.opengl.ETC1.isValid
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lab3.utils.PreferenceUtils
import com.example.mid.db.AppDatabase
import com.example.mid.db.entities.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initUI()
    }

    private fun initUI() {
        signUpBtn.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        loginBtn.setOnClickListener {
            validateAndSignIn(inputLogin.text.toString(), inputPass.text.toString())
        }
    }

    private fun validateAndSignIn(email: String, password: String) {
        AsyncTask.execute {
            val user = AppDatabase.getDatabase(applicationContext)
                ?.getUserDao()
                ?.getUserWithEmailAndPassword(email, password)

            if (user == null) {
                runOnUiThread {
                    Toast.makeText(baseContext, "Email or password incorrect! ", Toast.LENGTH_LONG).show()
                }
            } else {
                PreferenceUtils.saveLogged(this, true)
                PreferenceUtils.saveCurrentEmail(this, email)
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}