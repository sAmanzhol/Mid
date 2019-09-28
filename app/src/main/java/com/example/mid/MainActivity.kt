package com.example.mid

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.lab3.utils.PreferenceUtils
import com.example.mid.adapter.TodoAdapter
import com.example.mid.db.entities.Todo
import com.example.mid.db.repositroy.TodoRepository
import com.example.mid.db.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProviders.of(this,
            TodoViewModel.Factory(TodoRepository()))[TodoViewModel::class.java]
    }

    private var todos: List<Todo>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        todo_list.layoutManager = LinearLayoutManager(this)
        viewModel.liveData.observe(this, Observer { data ->
            todos = data
            val adapter = TodoAdapter(todos!!)
            todo_list.adapter = adapter
        })
        viewModel.loadSomeData(applicationContext)

        if (!PreferenceUtils.getLogged(this)) startActivity(Intent(this, LoginActivity::class.java))

        logoutBtn.setOnClickListener {
            PreferenceUtils.saveLogged(this, false)
            startActivity(Intent(this, LoginActivity::class.java))
        }

        addBtn.setOnClickListener {
            startActivity(Intent(this, AddTodoItemActivity::class.java))
        }

    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}
