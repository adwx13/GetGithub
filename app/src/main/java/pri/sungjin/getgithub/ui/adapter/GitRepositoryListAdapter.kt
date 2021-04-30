package pri.sungjin.getgithub.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pri.sungjin.getgithub.api.repository.GitRepositoriesResult
import pri.sungjin.getgithub.databinding.GitRepositoryItemBinding

class GitRepositoryListAdapter(var items: GitRepositoriesResult = GitRepositoriesResult()): RecyclerView.Adapter<GitRepositoryListAdapter.GitRepositoryListHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepositoryListHolder {
        val binding = GitRepositoryItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return GitRepositoryListHolder(binding)
    }

    override fun onBindViewHolder(holder: GitRepositoryListHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class GitRepositoryListHolder(val binding: GitRepositoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        init {

        }
    }
}