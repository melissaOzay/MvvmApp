package com.example.mvvmapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmapp.R
import com.example.mvvmapp.adapter.AdapterUtils
import com.example.mvvmapp.view_model.UserVM
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.log


class UserActivity : AppCompatActivity() {

    private lateinit var activityViewModel: UserVM
    private var recyclerViewAdapter: AdapterUtils? = null
    lateinit var recyclerView: RecyclerView
    lateinit var floatingButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.user_rv)
        activityViewModel = ViewModelProvider(this).get(UserVM::class.java)
        recyclerView.adapter = recyclerViewAdapter
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //Bu kod sayesinde search kısmına kelime yazdığımda listemin yukarı kaymasını engelliyor.
        initialiseAdapter()
        floatingButton()
        loadData()
        createData()


    }

    override fun onStart() {
        super.onStart()
        activityViewModel.loadData()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.loadData()
    }

    private fun initialiseAdapter() {
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun floatingButton() {
        floatingButton = findViewById(R.id.fab)
        floatingButton.setOnClickListener {
            val intent = Intent(this, SaveActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadData() {
        activityViewModel.userList.observe(this) {
            recyclerView.adapter = recyclerViewAdapter
            recyclerViewAdapter?.setData(it)
            recyclerViewAdapter = AdapterUtils(it, this)
            activityViewModel.failure.observe(this) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }


    }


    fun createData() {

        activityViewModel.searchData.observe(this) {
            recyclerView.adapter = recyclerViewAdapter
            recyclerViewAdapter?.setData(it)
            recyclerViewAdapter = AdapterUtils(it, this)
            activityViewModel.failure.observe(this) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }

        }


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        Log.e("userActivity", "tasarım1")
        val menuItem = menu.findItem(R.id.action_search)
        Log.e("userActivity", "tasarım2")
        val searchView = menuItem.actionView as SearchView
        searchView.imeOptions = (EditorInfo.IME_ACTION_DONE)
        Log.e("userActivity", "tasarım")


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                activityViewModel.searchAfter(newText.toString())
                return false
            }


        })
        return true
    }


}
