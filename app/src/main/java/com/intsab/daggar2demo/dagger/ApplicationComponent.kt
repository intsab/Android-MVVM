package com.intsab.daggar2demo.dagger

import com.intsab.daggar2demo.MainActivity
import com.intsab.daggar2demo.data.network.CommentsModule
import dagger.Component

@Component(modules = [CommentsModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}