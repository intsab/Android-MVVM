package com.intsab.mvvm.dagger

import com.intsab.mvvm.activities.MainActivity
import com.intsab.mvvm.data.network.CommentsModule
import com.intsab.mvvm.fragments.MainActivityFragment
import dagger.Component

@Component(modules = [CommentsModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivityFragment)
}