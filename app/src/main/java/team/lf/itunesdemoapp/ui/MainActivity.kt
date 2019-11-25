package team.lf.itunesdemoapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import team.lf.itunesdemoapp.R
import team.lf.itunesdemoapp.service.MusicService

/*
* TODO
*  1) format date in lookupFragment
*  2) move mediaPlayer logic from lookupFragment (try to use foregroundService+notification+eventBus+VM)
*  3) change design of lookupFragment for using appBar
*  4) hide keyboard
*/

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val musicServiceIntent = Intent(this, MusicService::class.java)
        startService(musicServiceIntent)
    }
}
