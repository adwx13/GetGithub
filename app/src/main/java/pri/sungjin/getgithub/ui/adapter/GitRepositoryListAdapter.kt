package pri.sungjin.getgithub.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pri.sungjin.getgithub.R
import pri.sungjin.getgithub.api.repository.GitRepositoriesResult
import pri.sungjin.getgithub.database.FavoriteReposEntity
import pri.sungjin.getgithub.database.FavoriteRepository
import pri.sungjin.getgithub.databinding.GitRepositoryItemBinding
import pri.sungjin.getgithub.viewmodel.BaseBindingComponent

class GitRepositoryListAdapter(var items: GitRepositoriesResult = GitRepositoriesResult(), var favoriteHash: HashSet<Int> = hashSetOf(), val favoriteRepository: FavoriteRepository): RecyclerView.Adapter<GitRepositoryListAdapter.GitRepositoryListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepositoryListHolder {
        val binding : GitRepositoryItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.context), R.layout.git_repository_item, parent, false, BaseBindingComponent())
        return GitRepositoryListHolder(binding)
    }



    override fun onBindViewHolder(holder: GitRepositoryListHolder, position: Int) {
        val item = items[position]

        holder.binding.name.text = item.name
        holder.binding.language.text = item.language
        holder.binding.description.text = item.description
        holder.binding.htmlUrl.text = item.html_url
        holder.binding.favoriteBtn.isSelected = favoriteHash.contains(item.id)
        holder.binding.favoriteBtn.setOnClickListener {
            if (it.isSelected) {
                CoroutineScope(Dispatchers.IO).launch {
                    favoriteRepository.deleteByRepositoryId(item.id)
                }
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    favoriteRepository.insert(item.getFavoriteReposEntity())
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class GitRepositoryListHolder(val binding: GitRepositoryItemBinding): RecyclerView.ViewHolder(binding.root)
}