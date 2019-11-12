package team.lf.itunesdemoapp.ui.albumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import team.lf.itunesdemoapp.R
import team.lf.itunesdemoapp.databinding.FragmentAlbumListBinding

class AlbumListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAlbumListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_album_list, container, false)
        val application = requireNotNull(this.activity).application

        return  binding.root
    }


}