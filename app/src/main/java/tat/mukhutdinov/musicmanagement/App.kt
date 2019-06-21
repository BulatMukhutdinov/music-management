package tat.mukhutdinov.musicmanagement

import android.app.Application
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tat.mukhutdinov.musicmanagement.infrastructure.di.InjectionModules

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()

        setupPicasso()

        setupUncaughtExceptions()
    }

    private fun setupUncaughtExceptions() {
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            // send logs to a server or something
        }
    }

    private fun setupPicasso() {
        val picasso = Picasso.Builder(this)
            .memoryCache(LruCache(this))
            .build()

        Picasso.setSingletonInstance(picasso)
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)

            modules(InjectionModules.modules)
        }
    }

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}