package com.example.mid

import android.arch.lifecycle.Observer
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.example.kinokotlin.api.RetrofitClient
import com.example.lab3.utils.PreferenceUtils
import com.example.mid.adapter.DragonAdapter
import com.example.mid.api.JsonPlaceHolderApi
import com.example.mid.model.Dragon
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    internal var service = RetrofitClient.retrofitInstance.create(JsonPlaceHolderApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val call = service.getDragons()
        requestDragonList(call)
        initUI()
    }

    private fun initUI() {
//        todo_list.layoutManager = LinearLayoutManager(this)
//        viewModel.liveData.observe(this, Observer { data ->
//            todos = data
//            val adapter = TodoAdapter(todos!!)
//            todo_list.adapter = adapter
//        })
//        viewModel.loadSomeData()

        if (!PreferenceUtils.getLogged(this)) startActivity(Intent(this, LoginActivity::class.java))

        logoutBtn.setOnClickListener {
            PreferenceUtils.saveLogged(this, false)
            startActivity(Intent(this, LoginActivity::class.java))
        }

        addBtn.setOnClickListener {
            startActivity(Intent(this, AddTodoItemActivity::class.java))
        }

    }

    private fun requestDragonList(call: Call<List<Dragon>>) {
        call.enqueue(object : Callback<List<Dragon>> {
            override fun onResponse(call: Call<List<Dragon>>, response: Response<List<Dragon>>) {
                generateDragonList(response.body()!!)
            }

            override fun onFailure(call: Call<List<Dragon>>, t: Throwable) {
                Toast.makeText(
                    baseContext, "Unable to load dragons",
                    Toast.LENGTH_SHORT
                ).show()
                t.printStackTrace()
            }
        })

    }

    private fun generateDragonList(results: List<Dragon>) {
        val adapter = DragonAdapter(results)
        todo_list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        todo_list.adapter = adapter
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}
