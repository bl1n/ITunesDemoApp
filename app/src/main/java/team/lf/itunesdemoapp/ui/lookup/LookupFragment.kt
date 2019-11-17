package team.lf.itunesdemoapp.ui.lookup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import team.lf.itunesdemoapp.R
import team.lf.itunesdemoapp.databinding.FragmentLookupBinding
import team.lf.itunesdemoapp.ui.search.SeachViewModel

class LookupFragment : Fragment() {

    private val viewModel: LookupViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }

        val args = LookupFragmentArgs.fromBundle(arguments!!)

        ViewModelProviders.of(
            this,
            LookupViewModel.Factory(activity.application, args.wrapperType, args.id)
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

        viewModel.lookupList.observe(this, Observer {
            binding.textView.text = it.size.toString()
        })
        return binding.root
    }
}