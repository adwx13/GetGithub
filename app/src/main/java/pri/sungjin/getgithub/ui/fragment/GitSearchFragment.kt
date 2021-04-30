package pri.sungjin.getgithub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import pri.sungjin.getgithub.R
import pri.sungjin.getgithub.api.GitHubAPI
import pri.sungjin.getgithub.api.repository.GitRepositoriesResult
import pri.sungjin.getgithub.api.user.GitUserResult
import pri.sungjin.getgithub.databinding.GitSearchFragmentBinding
import pri.sungjin.getgithub.ui.adapter.GitRepositoryListAdapter
import pri.sungjin.getgithub.viewmodel.BaseViewModel
import pri.sungjin.getgithub.viewmodel.GitSearchViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class GitSearchFragment(val gitHubAPI: GitHubAPI): Fragment() {

    lateinit var binding: GitSearchFragmentBinding
    val viewModel by viewModels<GitSearchViewModel>()

    @Inject
    lateinit var repositoryAdapter: GitRepositoryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = GitSearchFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()


        binding.searchBtn.setOnClickListener(clickListener)


        initAdapter()
        callApiByUserId(viewModel.searchText)
        viewModel.snackbarMsg.observe(viewLifecycleOwner, BaseViewModel.EventObserver {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })
        return binding.root
    }

    fun initAdapter() {
        binding.repositoryList.adapter = repositoryAdapter
        viewModel.gitRepositories.observe(viewLifecycleOwner, { repositories ->
            repositoryAdapter.items = repositories
        })
    }

    var plus = 0
    fun callApiByUserId(userId: String) {
        gitHubAPI.getUserData(userId).enqueue(object: Callback<GitUserResult> {
            override fun onResponse(call: Call<GitUserResult>, response: Response<GitUserResult>) {
                viewModel.setGitUser(response.body())
                viewModel.showSnackBar("data == "+plus++)
            }

            override fun onFailure(call: Call<GitUserResult>, t: Throwable) {
                viewModel.showSnackBar("서버 호출에 실패했습니다.")
            }
        })

        gitHubAPI.getUserRepositories(userId).enqueue(object: Callback<GitRepositoriesResult> {
            override fun onResponse(call: Call<GitRepositoriesResult>, response: Response<GitRepositoriesResult>) {
                val result = response.body()

                if (result == null) {
                    viewModel.setGitRepositories(GitRepositoriesResult())
                } else {
                    viewModel.setGitRepositories(result)
                }

            }

            override fun onFailure(call: Call<GitRepositoriesResult>, t: Throwable) {
                viewModel.showSnackBar("서버 호출에 실패했습니다.")
            }
        })
    }

    val clickListener = View.OnClickListener { v ->
        when(v?.id) {
            R.id.search_btn -> {
                gitHubAPI.getUserData(viewModel.searchText).enqueue(object: Callback<GitUserResult> {
                    override fun onResponse(call: Call<GitUserResult>, response: Response<GitUserResult>) {
                        viewModel.setGitUser(response.body())
                        viewModel.showSnackBar("data == "+plus++)
                    }

                    override fun onFailure(call: Call<GitUserResult>, t: Throwable) {
                        viewModel.showSnackBar("서버 호출에 실패했습니다.")
                    }
                })
            }
        }
    }
}