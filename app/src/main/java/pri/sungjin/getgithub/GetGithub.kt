package pri.sungjin.getgithub

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GetGithub: Application() {

    companion object {
        lateinit var instance: GetGithub
        private set
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
    }
}