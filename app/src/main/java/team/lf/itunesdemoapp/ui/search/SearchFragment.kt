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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import team.lf.itunesdemoapp.R
import team.lf.itunesdemoapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val viewModel: SeachViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, SeachViewModel.Factory(activity.application))
            .get(SeachViewModel::class.java)
    }

    private lateinit var searchAdapter: SearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSearchBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search, container, false
        )
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        searchAdapter = SearchAdapter(DomainModelClickListener {
            Toast.makeText(this.context, "$it", Toast.LENGTH_SHORT).show()
            this.findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToLookupFragment("collection", it)
            )
        })

        binding.root.findViewById<RecyclerView>(R.id.search_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }


        viewModel.eventNetworkError.observe(this, Observer {
            if (it) onNetworkError()
        })
        binding.testSearchButton.setOnClickListener {
            viewModel.refreshSearchListFromRepository("jack")
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.searchList.observe(viewLifecycleOwner, Observer {
            searchAdapter.submitDomainModelListInCoroutine(it)
        })
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }


}