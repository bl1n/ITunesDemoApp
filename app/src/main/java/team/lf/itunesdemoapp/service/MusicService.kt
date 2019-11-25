package team.lf.itunesdemoapp.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.IBinder
import android.widget.Toast
import team.lf.itunesdemoapp.repository.ITunesRepository
import timber.log.Timber

class MusicService : Service() {


    override fun onCreate() {
        super.onCreate()
        Timber.d("in service")
    }



    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
//        player.release()
        super.onDestroy()
    }
}