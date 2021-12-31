package kg.ainazik.data.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.ainazik.data.persistence.room.dao.NewsDao
import kg.ainazik.data.persistence.room.entity.ArticleRoomEntity


@Database(entities = [ArticleRoomEntity::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase(){
    abstract fun newsDao(): NewsDao
}