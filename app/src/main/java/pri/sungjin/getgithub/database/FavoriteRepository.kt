package pri.sungjin.getgithub.database

import androidx.lifecycle.LiveData

interface FavoriteRepository {

    fun getAll(): LiveData<List<FavoriteReposEntity>>
    fun getAllFavoriteReposId(): LiveData<List<Int>>
    fun getFavoriteReposByReposId(repositoryId: Int): FavoriteReposEntity
    fun getFavoriteReposByUserIdAndName(userid: String, repository: String): FavoriteReposEntity
    fun insert(favoriteReposEntity: FavoriteReposEntity)
    fun update(favoriteReposEntity: FavoriteReposEntity)
    fun delete(favoriteReposEntity: FavoriteReposEntity)
    fun deleteByRepositoryId(repositoryId: Int)
    fun nukeTable()
}