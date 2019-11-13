package team.lf.itunesdemoapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import team.lf.itunesdemoapp.R
import team.lf.itunesdemoapp.databinding.FragmentSearchBinding

class SearchListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSearchBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search, container, false)
        val application = requireNotNull(this.activity).application

        return  binding.root
    }


}