package com.example.mid

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mid.db.AppDatabase
import com.example.mid.db.entities.Todo
import com.example.mid.db.entities.User
import com.example.mid.db.repositroy.LoginRepository
import com.example.mid.db.repositroy.TodoRepository
import com.example.mid.db.viewModel.LoginViewModel
import com.example.mid.db.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this,
            LoginViewModel.Factory(LoginRepository(AppDatabase.getDatabase(applicationContext)!!.getUserDao())))[LoginViewModel::class.java]
    }

    private var isExist: Boolean? = false

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

            viewModel.loadUserWithEmail(applicationContext, email)
            viewModel.isExistLiveData.observe(this, Observer { data ->
               isExist = data
                if (isExist!!) {
                    runOnUiThread {
                        Toast.makeText(baseContext, "Email already exist! ", Toast.LENGTH_LONG).show()
                    }
                } else {
                    if (isValid(email, password, name, surname)) {
                        viewModel.signUp(applicationContext,
                            User(
                                email = email,
                                password = password,
                                name = name,
                                surname = surname
                            )
                        )

                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                }
           })


    }

    private fun isValid(email: String, password: String, name: String, surname: String): Boolean {
        if (email.isEmpty() || password.isEmpty() || name.isEmpty() || surname.isEmpty()) return false
        return true
    }


}
