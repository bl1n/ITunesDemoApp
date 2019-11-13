package team.lf.itunesdemoapp.ui.lookup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import team.lf.itunesdemoapp.R
import team.lf.itunesdemoapp.databinding.FragmentLookupBinding

class AlbumDetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLookupBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_lookup, container, false)

        return binding.root
    }
}