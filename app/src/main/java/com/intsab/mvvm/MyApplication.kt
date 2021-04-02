package com.intsab.mvvm

import android.app.Application
import com.intsab.mvvm.dagger.DaggerApplicationComponent

class MyApplication: Application() {
    val appComponent = DaggerApplicationComponent.create()
}