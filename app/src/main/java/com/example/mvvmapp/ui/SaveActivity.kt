package com.example.mvvmapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapp.R
import com.example.mvvmapp.data_class.UserInfo
import com.example.mvvmapp.view_model.SaveVM
import kotlinx.android.synthetic.main.activity_record.*


class SaveActivity : AppCompatActivity() {

    lateinit var SaveViewModel: SaveVM
    lateinit var Savebutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        SaveViewModel = ViewModelProvider(this).get(SaveVM::class.java)
        observeLiveData()
        saveButton()
        //melisaa



    }
    private fun saveButton(){
        Savebutton = findViewById(R.id.button)
        Savebutton.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val nameText = nameText.text.toString()
        val emailText = emailText.text.toString()
        val passwordText = passText.text.toString()
        val surnameText = surnameText.text.toString()

        val user = UserInfo(id = null, nameText, surnameText, emailText, passwordText)
        SaveViewModel.createNewUser(user)
    }


    private fun observeLiveData() {
        SaveViewModel.createNewUserLiveData.observe(this) {
                onBackPressed()
                Toast.makeText(this, "Successfully created User", Toast.LENGTH_LONG).show()
        }
        SaveViewModel.failure.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

}