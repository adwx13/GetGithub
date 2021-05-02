package pri.sungjin.getgithub.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pri.sungjin.getgithub.R
import pri.sungjin.getgithub.database.FavoriteReposEntity
import pri.sungjin.getgithub.database.FavoriteRepository
import pri.sungjin.getgithub.databinding.FavoriteRepositoryItemBinding
import pri.sungjin.getgithub.viewmodel.BaseBindingComponent

class FavoriteRepositoryListAdapter(var items: List<FavoriteReposEntity> = listOf(), val favoriteRepository: FavoriteRepository): RecyclerView.Adapter<FavoriteRepositoryListAdapter.FavoriteRepositoryListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteRepositoryListHolder {
        val binding: FavoriteRepositoryItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.favorite_repository_item, parent, false, BaseBindingComponent())
        return FavoriteRepositoryListHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteRepositoryListHolder, position: Int) {
        val item = items[position]
        val beforeItemUserId = if (position == 0) {
            null
        } else {
            items[position-1].userid
        }
        if (item.userid.equals(beforeItemUserId)) {
            holder.binding.userId.visibility = View.GONE
        } else {
            holder.binding.userId.visibility = View.VISIBLE
            holder.binding.userId.text = item.userid
        }
        holder.binding.name.text = item.repository
        holder.binding.language.text = item.language
        holder.binding.description.text = item.description
        holder.binding.htmlUrl.text = item.html_url
        holder.binding.favoriteBtn.setOnClickListener {
            Snackbar.make(it, it.resources.getString(R.string.remove_favorite_item, item.repository),Snackbar.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.IO).launch {
                favoriteRepository.deleteByRepositoryId(item.repositoryId)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class FavoriteRepositoryListHolder(val binding: FavoriteRepositoryItemBinding): RecyclerView.ViewHolder(binding.root)
}