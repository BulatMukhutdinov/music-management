package tat.mukhutdinov.musicmanagement

import android.app.Application
import android.os.Build
import android.os.StrictMode
import com.facebook.stetho.Stetho
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tat.mukhutdinov.musicmanagement.infrastructure.di.InjectionModules
import timber.log.Timber

class DebugApp : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()

        setupTimber()

        setupStetho()

        setupPicasso()

        setupUncaughtExceptions()

        setupStrictMode()
    }

    private fun setupUncaughtExceptions() {
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            Timber.e(throwable)
        }
    }

    private fun setupPicasso() {
        val picasso = Picasso.Builder(this)
            .memoryCache(LruCache(this))
            .build()

        Picasso.setSingletonInstance(picasso)
    }

    private fun setupStrictMode() {
        Dispatchers.Main // https://github.com/googlecodelabs/kotlin-coroutines/issues/23

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build()
        )

        val builder = StrictMode.VmPolicy.Builder()
            .detectActivityLeaks()
            .detectFileUriExposure()
            .detectLeakedClosableObjects()
            .detectLeakedRegistrationObjects()
            .detectLeakedSqlLiteObjects()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.detectContentUriWithoutPermission()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            builder.detectCleartextNetwork()
        }

        StrictMode.setVmPolicy(
            builder
                .penaltyLog()
                .penaltyDeath()
                .build()
        )
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@DebugApp)

            modules(InjectionModules.modules)
        }
    }

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }
}
