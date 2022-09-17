package com.example.zeldaapp

import android.app.Application
import com.example.zeldaapp.category.di.CategoryDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ZeldaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ZeldaApplication)
            modules(CategoryDi.module)
        }
    }

}