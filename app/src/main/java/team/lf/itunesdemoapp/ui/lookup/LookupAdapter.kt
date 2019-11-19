package team.lf.itunesdemoapp.ui.lookup

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import team.lf.itunesdemoapp.databinding.TrackListItemBinding
import team.lf.itunesdemoapp.domain.DomainModel


class LookupAdapter() :
    ListAdapter<DomainModel.Track, LookupAdapter.TrackViewHolder>(SearchDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun submitInCoroutine(list: List<DomainModel.Track>?) {
        adapterScope.launch {
            withContext(Dispatchers.Main) {
                submitList(list)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TrackViewHolder private constructor(private val binding: TrackListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DomainModel.Track) {
            binding.track = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TrackViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TrackListItemBinding.inflate(layoutInflater, parent, false)
                return TrackViewHolder(binding)
            }
        }
    }
}

class SearchDiffCallback : DiffUtil.ItemCallback<DomainModel.Track>() {
    override fun areItemsTheSame(oldItem: DomainModel.Track, newItem: DomainModel.Track): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: DomainModel.Track,
        newItem: DomainModel.Track
    ): Boolean {
        return oldItem == newItem
    }
}

