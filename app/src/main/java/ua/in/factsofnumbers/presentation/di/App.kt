package ua.`in`.factsofnumbers.presentation.di

import android.app.Application
import ua.`in`.factsofnumbers.BuildConfig

class App: Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().netModule(NetModule(BuildConfig.BASE_URL)).appModule(AppModule(applicationContext)).build()
    }

    fun getAppComponent(): AppComponent{
        return appComponent
    }
}