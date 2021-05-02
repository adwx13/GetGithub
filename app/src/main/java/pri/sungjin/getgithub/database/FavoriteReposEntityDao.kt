package pri.sungjin.getgithub.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteReposEntityDao : FavoriteRepository {

    @Query("SELECT * FROM favorite_repository ORDER BY userid DESC")
    override fun getAll(): LiveData<List<FavoriteReposEntity>>

    @Query("SELECT * FROM favorite_repository WHERE repositoryId = :repositoryId")
    override fun getFavoriteReposByReposId(repositoryId: Int): FavoriteReposEntity

    @Query("SELECT * FROM favorite_repository WHERE userid = :userid AND repository = :repository")
    override fun getFavoriteReposByUserIdAndName(userid: String, repository: String): FavoriteReposEntity

    @Query("SELECT repositoryId FROM favorite_repository")
    override fun getAllFavoriteReposId(): LiveData<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(favoriteReposEntity: FavoriteReposEntity)

    @Update
    override fun update(favoriteReposEntity: FavoriteReposEntity)

    @Delete
    override fun delete(favoriteReposEntity: FavoriteReposEntity)

    @Query("DELETE FROM favorite_repository WHERE repositoryId = :repositoryId")
    override fun deleteByRepositoryId(repositoryId: Int)

    @Query("DELETE FROM favorite_repository")
    override fun nukeTable()
}