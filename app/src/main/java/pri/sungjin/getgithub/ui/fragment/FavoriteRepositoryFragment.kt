package pri.sungjin.getgithub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pri.sungjin.getgithub.R
import pri.sungjin.getgithub.database.FavoriteReposEntity
import pri.sungjin.getgithub.database.FavoriteRepository
import pri.sungjin.getgithub.databinding.FavoriteRepositoryFragmentBinding
import pri.sungjin.getgithub.ui.adapter.FavoriteRepositoryListAdapter
import pri.sungjin.getgithub.viewmodel.BaseBindingComponent
import pri.sungjin.getgithub.viewmodel.FavoriteRepositoryViewModel
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteRepositoryFragment: Fragment() {

    val TAG = "FAVORITE_REPOS"
    lateinit var binding: FavoriteRepositoryFragmentBinding
    val viewModel by viewModels<FavoriteRepositoryViewModel>()

    @Inject
    lateinit var favoriteRepositoryAdapter: FavoriteRepositoryListAdapter

    @Inject
    lateinit var favoriteRepository: FavoriteRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.favorite_repository_fragment, container, false, BaseBindingComponent())
        binding.viewModel = viewModel
        binding.executePendingBindings()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initAdapter()
    }

    fun initAdapter() {
        binding.favoriteList.adapter = favoriteRepositoryAdapter
        favoriteRepository.getAll().observe(viewLifecycleOwner, { repositories ->
            viewModel.setFavoriteRepositories(repositories)
            favoriteRepositoryAdapter.items = repositories
            favoriteRepositoryAdapter.notifyDataSetChanged()
        })
    }

}