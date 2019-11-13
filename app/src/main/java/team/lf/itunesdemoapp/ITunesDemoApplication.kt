package team.lf.itunesdemoapp

import android.app.Application
import timber.log.Timber

class ITunesDemoApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}