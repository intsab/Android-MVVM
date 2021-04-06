package com.intsab.mvvm.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.intsab.mvvm.R

class MainActivity : AppCompatActivity() {
    lateinit var navigationController:NavController;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationController= Navigation.findNavController(this,R.id.fragment )

    }
}