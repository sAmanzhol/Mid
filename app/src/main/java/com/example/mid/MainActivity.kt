package com.example.mid

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.example.kinokotlin.api.RetrofitClient
import com.example.lab3.utils.PreferenceUtils
import com.example.mid.adapter.DragonAdapter
import com.example.mid.api.JsonPlaceHolderApi
import com.example.mid.db.entities.Fact
import com.example.mid.db.repositroy.FactRepositoryDB
import com.example.mid.db.viewModel.FactViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {
    internal var service = RetrofitClient.retrofitInstance.create(JsonPlaceHolderApi::class.java)

    private var todos: List<Fact>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
    }

    private fun initUI() {
        val type = if (verifyAvailableNetwork(this))
            "remote"
        else
            "local"

        val viewModel: FactViewModel by viewModel(named(type))

        todo_list.layoutManager = LinearLayoutManager(this)
        viewModel.liveData.observe(this, Observer { data ->
            todos = data
            val adapter = DragonAdapter(todos!!)
            todo_list.adapter = adapter
        })
        viewModel.loadSomeData()

        if (!PreferenceUtils.getLogged(this)) startActivity(Intent(this, LoginActivity::class.java))

        logoutBtn.setOnClickListener {
            PreferenceUtils.saveLogged(this, false)
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun verifyAvailableNetwork(activity:AppCompatActivity):Boolean{
        val connectivityManager=activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetworkInfo
        return  networkInfo!=null && networkInfo.isConnected
    }
}
