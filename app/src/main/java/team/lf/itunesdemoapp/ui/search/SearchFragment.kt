package team.lf.itunesdemoapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import team.lf.itunesdemoapp.R
import team.lf.itunesdemoapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(this, SearchViewModel.Factory(activity.application))
            .get(SearchViewModel::class.java)
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

        searchAdapter = SearchAdapter(OnCollectionClickListener {
            this.findNavController().navigate(
                SearchFragmentDirections.actionSearchFragmentToLookupFragment(it)
            )
        })

        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) =  when (position) {
                0 -> 3
                else -> 1
            }
        }
        binding.root.findViewById<RecyclerView>(R.id.search_list).apply {
            layoutManager = manager
            adapter = searchAdapter
        }


        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer {
            if (it) onNetworkError()
        })
        binding.testSearchButton.setOnClickListener {
            val toSearch = binding.testEditText.text.toString()
            if (!toSearch.isBlank())
                viewModel.refreshSearchListFromRepository(toSearch)
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