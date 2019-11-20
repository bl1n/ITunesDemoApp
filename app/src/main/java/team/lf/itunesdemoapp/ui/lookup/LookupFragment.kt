package team.lf.itunesdemoapp.ui.lookup

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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        lookupAdapter = LookupAdapter(OnTrackClickListener {
            viewModel.onTrackPlayPressed(it)
        })
        binding.root.findViewById<RecyclerView>(R.id.track_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = lookupAdapter
        }


        binding.collection = LookupFragmentArgs.fromBundle(arguments!!).collection



        viewModel.trackList.observe(this, Observer {
            lookupAdapter.submitInCoroutine(it)
        })
        return binding.root
    }
}