package team.lf.itunesdemoapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import team.lf.itunesdemoapp.R
import team.lf.itunesdemoapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val viewModel:SeachViewModel by lazy {
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, SeachViewModel.Factory(activity.application))
            .get(SeachViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSearchBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.eventNetworkError.observe(this, Observer {
            if(it) onNetworkError()
        })

        viewModel.searchList.observe(this, Observer {
            it?.let {
                binding.results.text = it.size.toString()
            }
        })

        viewModel.refreshSearchListFromRepository("between")

        return  binding.root
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }


}