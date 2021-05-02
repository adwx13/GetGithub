package pri.sungjin.getgithub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import pri.sungjin.getgithub.database.FavoriteReposEntity
import javax.inject.Inject

@HiltViewModel
class FavoriteRepositoryViewModel @Inject constructor() : BaseViewModel() {

    val _favoriteRepositories = MutableLiveData<List<FavoriteReposEntity>>()
    val favoriteRepositories : LiveData<List<FavoriteReposEntity>> = _favoriteRepositories

    fun setFavoriteRepositories(repositories : List<FavoriteReposEntity>) {
        _favoriteRepositories.postValue(repositories)
    }




}