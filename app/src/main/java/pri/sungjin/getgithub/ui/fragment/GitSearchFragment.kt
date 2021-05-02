package pri.sungjin.getgithub.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import pri.sungjin.getgithub.R
import pri.sungjin.getgithub.api.GitHubAPI
import pri.sungjin.getgithub.api.repository.GitRepositoriesResult
import pri.sungjin.getgithub.api.user.GitUserResult
import pri.sungjin.getgithub.database.FavoriteRepository
import pri.sungjin.getgithub.databinding.GitSearchFragmentBinding
import pri.sungjin.getgithub.ui.adapter.GitRepositoryListAdapter
import pri.sungjin.getgithub.viewmodel.BaseBindingComponent
import pri.sungjin.getgithub.viewmodel.BaseViewModel
import pri.sungjin.getgithub.viewmodel.GitSearchViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class GitSearchFragment: Fragment() {

    val TAG = "GIT_SEARCH"

    lateinit var binding: GitSearchFragmentBinding
    val viewModel by viewModels<GitSearchViewModel>()

    @Inject
    lateinit var repositoryAdapter: GitRepositoryListAdapter

    @Inject
    lateinit var favoriteRepository: FavoriteRepository

    @Inject
    lateinit var gitHubAPI: GitHubAPI

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.git_search_fragment, container, false, BaseBindingComponent())
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.searchBtn.setOnClickListener(clickListener)
        binding.reposLoadBtn.setOnClickListener(clickListener)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initAdapter()
        initDebounce()
        viewModel.snackbarMsg.observe(viewLifecycleOwner, BaseViewModel.EventObserver {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })
    }

    fun initAdapter() {
        binding.repositoryList.adapter = repositoryAdapter
        viewModel.gitRepositories.observe(viewLifecycleOwner, { repositories ->
            repositoryAdapter.items = repositories
            repositoryAdapter.notifyDataSetChanged()
        })

        favoriteRepository.getAllFavoriteReposId().observe(viewLifecycleOwner, { favoriteRepos ->
            repositoryAdapter.favoriteHash = favoriteRepos.toHashSet()
            repositoryAdapter.notifyDataSetChanged()
        })
    }

    val gitSearchDebounce: PublishSubject<String> = PublishSubject.create()
    val repositoryReloadDebounce: PublishSubject<String> = PublishSubject.create()
    fun initDebounce() {
        gitSearchDebounce.debounce(400,TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).doOnNext {
            Log.i(TAG, "gitSearchDebounce - it == $it")
            gitHubAPI.getUserData(it).enqueue(object: Callback<GitUserResult> {
                override fun onResponse(call: Call<GitUserResult>, response: Response<GitUserResult>) {
                    viewModel.setGitUser(response.body())
                    viewModel.setGitRepositories(GitRepositoriesResult())
                    viewModel.showSnackBar(getString(R.string.succeed_request_to_server))
                }

                override fun onFailure(call: Call<GitUserResult>, t: Throwable) {
                    viewModel.showSnackBar(getString(R.string.failed_request_to_server))
                }
            })
        }.subscribe()

        repositoryReloadDebounce.debounce(400, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).doOnNext {
            Log.i(TAG, "repositoryReloadDebounce - it == $it")
            gitHubAPI.getUserRepositories(it).enqueue(object: Callback<GitRepositoriesResult> {
                override fun onResponse(call: Call<GitRepositoriesResult>, response: Response<GitRepositoriesResult>) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        viewModel.setGitRepositories(responseBody)
                    } else {
                        viewModel.setGitRepositories(GitRepositoriesResult())
                    }
                    viewModel.showSnackBar(getString(R.string.succeed_request_to_server))
                }

                override fun onFailure(call: Call<GitRepositoriesResult>, t: Throwable) {
                    viewModel.showSnackBar(getString(R.string.failed_request_to_server))
                }
            })
        }.subscribe()
    }

    val clickListener = View.OnClickListener { v ->
        when(v?.id) {
            R.id.search_btn -> {
                gitSearchDebounce.onNext(viewModel.searchText)
            }
            R.id.repos_load_btn -> {
                val gitUser = viewModel.gitUser.value
                if (gitUser?.login != null) {
                    repositoryReloadDebounce.onNext(gitUser.login)
                }
            }
        }
    }
}