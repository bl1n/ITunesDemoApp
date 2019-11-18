package team.lf.itunesdemoapp.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import team.lf.itunesdemoapp.R
import team.lf.itunesdemoapp.databinding.GridCollectionItemBinding
import team.lf.itunesdemoapp.domain.DomainModel


private const val VIEW_TYPE_HEADER = 0
private const val VIEW_TYPE_COLLECTION = 1
private const val VIEW_TYPE_TRACK = 2
private const val VIEW_TYPE_ARTIST = 3

class SearchAdapter(private val clickListener: DomainModelClickListener) :
    ListAdapter<DomainModel, RecyclerView.ViewHolder>(SearchDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun submitDomainModelListInCoroutine(list: List<DomainModel>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DomainModel.Header)
                else -> listOf(DomainModel.Header) + list
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DomainModel.Collection -> VIEW_TYPE_COLLECTION
            is DomainModel.Track -> VIEW_TYPE_TRACK
            is DomainModel.Artist -> VIEW_TYPE_ARTIST
            else -> VIEW_TYPE_HEADER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_COLLECTION -> CollectionViewHolder.from(parent)
            VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")//todo
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CollectionViewHolder -> {
                val item = getItem(position) as DomainModel.Collection
                holder.bind(item, clickListener)
            }

        }
    }

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header, parent, false)
                return TextViewHolder(view)
            }
        }
    }


    class CollectionViewHolder private constructor(private val binding: GridCollectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DomainModel.Collection, clickListener: DomainModelClickListener) {
            binding.collection = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CollectionViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GridCollectionItemBinding.inflate(layoutInflater, parent, false)
                return CollectionViewHolder(binding)
            }
        }
    }
}

class SearchDiffCallback : DiffUtil.ItemCallback<DomainModel>() {
    override fun areItemsTheSame(oldItem: DomainModel, newItem: DomainModel): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DomainModel, newItem: DomainModel): Boolean {
        return oldItem == newItem
    }
}

class DomainModelClickListener(val clickListener: (list: List<String>) -> Unit) {
    fun onClick(domainModel: DomainModel) =
        clickListener(listOf(domainModel.wrapperType, domainModel.id))
}