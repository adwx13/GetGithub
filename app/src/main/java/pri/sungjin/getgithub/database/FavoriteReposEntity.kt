package pri.sungjin.getgithub.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "favorite_repository",
        indices = arrayOf(Index(value = ["repositoryId"], unique = true)))
data class FavoriteReposEntity(@ColumnInfo(name = "repositoryId") var repositoryId: Int,
                               @ColumnInfo(name = "userid") var userid: String?,
                               @ColumnInfo(name = "repository") var repository: String?,
                               @ColumnInfo(name = "language") var language: String?,
                               @ColumnInfo(name = "description") var description: String?,
                               @ColumnInfo(name = "html_url") var html_url: String?,
                               @ColumnInfo(name = "reg_date") var regDate: Calendar = Calendar.getInstance()) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
