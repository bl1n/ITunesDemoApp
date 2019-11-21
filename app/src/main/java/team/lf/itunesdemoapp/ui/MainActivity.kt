package team.lf.itunesdemoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import team.lf.itunesdemoapp.R

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
    }
}
