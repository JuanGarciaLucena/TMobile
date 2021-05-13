package com.juanlucena.tmobile

import android.app.Application
import com.juanlucena.tmobile.viewModels.MarvelViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MarvelApplication : Application() {

    private val appModule = module {
        viewModel { MarvelViewModel() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MarvelApplication)
            modules(appModule)
        }
    }
}