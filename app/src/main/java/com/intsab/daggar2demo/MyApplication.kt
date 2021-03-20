package com.intsab.daggar2demo

import android.app.Application
import com.intsab.daggar2demo.dagger.DaggerApplicationComponent

class MyApplication: Application() {
    val appComponent = DaggerApplicationComponent.create()
}