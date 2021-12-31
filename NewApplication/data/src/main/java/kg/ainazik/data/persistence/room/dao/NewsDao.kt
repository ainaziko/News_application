package kg.ainazik.data.persistence.room.dao

import androidx.room.*
import kg.ainazik.data.persistence.room.entity.ArticleRoomEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArticle(article: ArticleRoomEntity)

    @Query("SELECT * FROM articles")
    fun getArticles() : Array<ArticleRoomEntity>

    @Delete
    fun deleteArticle(article: ArticleRoomEntity)
}