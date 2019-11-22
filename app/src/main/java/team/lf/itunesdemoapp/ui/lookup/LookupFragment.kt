package team.lf.itunesdemoapp.ui.lookup

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import team.lf.itunesdemoapp.R
import team.lf.itunesdemoapp.databinding.FragmentLookupBinding

class LookupFragment : Fragment() {

    private lateinit var lookupAdapter: LookupAdapter
    private lateinit var mediaPlayer: MediaPlayer

    private val viewModel: LookupViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        val args = LookupFragmentArgs.fromBundle(arguments!!)

        ViewModelProviders.of(
            this,
            LookupViewModel.Factory(activity.application, args.collection)
        )
            .get(LookupViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLookupBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_lookup, container, false
        )


        mediaPlayer = MediaPlayer()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        lookupAdapter = LookupAdapter(OnTrackClickListener {
            viewModel.onTrackPlayPressed(it)

            //todo move mediaPlayer logic to VM
            mediaPlayer.release()
            mediaPlayer = MediaPlayer()
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer.isLooping = false
            mediaPlayer.setDataSource(it.previewUrl)
            mediaPlayer.prepareAsync()
            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
            }
            mediaPlayer.setOnCompletionListener {
                viewModel.onTrackPlayingComplete()
            }
        })
        binding.root.findViewById<RecyclerView>(R.id.track_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = lookupAdapter
        }


        viewModel.trackList.observe(this, Observer {
            lookupAdapter.submitInCoroutine(it)
        })
        return binding.root
    }



}