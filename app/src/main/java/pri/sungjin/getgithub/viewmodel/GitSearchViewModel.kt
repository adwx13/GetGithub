package pri.sungjin.getgithub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pri.sungjin.getgithub.GetGithub
import pri.sungjin.getgithub.R
import pri.sungjin.getgithub.api.repository.GitRepositoriesResult
import pri.sungjin.getgithub.api.user.GitUserResult
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GitSearchViewModel @Inject constructor() : BaseViewModel() {

    var searchText = "adwx13"

    val _gitUser = MutableLiveData<GitUserResult?>()
    val gitUser : LiveData<GitUserResult?> = _gitUser

    val _gitRepositories = MutableLiveData<GitRepositoriesResult>()
    val gitRepositories : LiveData<GitRepositoriesResult> = _gitRepositories

    fun setGitUser(value: GitUserResult?) {
        _gitUser.postValue(value)
    }

    fun setGitRepositories(value: GitRepositoriesResult) {
        _gitRepositories.postValue(value)
    }

    fun getDateTextByIsoFormat(dateStr: String): String {
        val splitStr = dateStr.split("T")
        var returnStr = ""
        if (splitStr.isNotEmpty()) {
            returnStr = GetGithub.instance.resources.getString(R.string.created_date,splitStr[0])
        }
        return returnStr
    }

}