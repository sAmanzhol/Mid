package com.example.mid

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mid.db.AppDatabase
import com.example.mid.db.entities.User
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        initUI()
    }

    private fun initUI() {
        regLoginBtn.setOnClickListener {
            validateAndSignUp(regEmail.text.toString(), regPass.text.toString(),
                              regName.text.toString(), regSurname.text.toString())
        }

        regBackBtn.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
    }

    private fun validateAndSignUp(email: String, password: String, name: String, surname: String){
       AsyncTask.execute {
           val user = AppDatabase.getDatabase(applicationContext)
                                ?.getUserDao()
                                ?.getUserWithEmail(email)

           if (user != null) {
               runOnUiThread {
                   Toast.makeText(baseContext, "Email already exist! ", Toast.LENGTH_LONG).show()
               }
           } else {
               if (isValid(email, password, name, surname)) {
                    AppDatabase.getDatabase(applicationContext)
                              ?.getUserDao()
                              ?.insertUser(User(
                                  email = email,
                                  password = password,
                                  name = name,
                                  surname = surname
                              ))
                   startActivity(Intent(this, LoginActivity::class.java))
               }
           }
       }
    }

    private fun isValid(email: String, password: String, name: String, surname: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty()) return false
        return true
    }


}
