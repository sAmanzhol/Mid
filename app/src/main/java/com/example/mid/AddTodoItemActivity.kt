package com.example.mid

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lab3.utils.PreferenceUtils
import com.example.mid.db.AppDatabase
import com.example.mid.db.entities.Todo
import com.example.mid.db.entities.User
import kotlinx.android.synthetic.main.activity_add_todo_item.*

class AddTodoItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_todo_item)
        initUI()
    }

    private fun initUI(){
        saveBtn.setOnClickListener {
            validateAndAddTodo(inputTitle.text.toString(), inputContent.text.toString(),
                          PreferenceUtils.getCurrentEmail(this), spinnerPriority.selectedItem.toString())
        }
    }

    private fun validateAndAddTodo(title: String, content: String, email: String, priority: String){
        if (isValid(title, content)) {
            AsyncTask.execute {
                AppDatabase.getDatabase(applicationContext)
                    ?.getTodoDao()
                    ?.insertTodo(
                        Todo(
                            title = title,
                            content = content,
                            priority = priority,
                            email = email
                        )
                    )
                startActivity(Intent(this, MainActivity::class.java))
            }
        } else {
            Toast.makeText(baseContext, "Incorrect input! ", Toast.LENGTH_LONG).show()
        }

    }

    private fun isValid(title: String, content: String): Boolean {
        if (title.isEmpty() || content.isEmpty()) return false
        return true
    }
}