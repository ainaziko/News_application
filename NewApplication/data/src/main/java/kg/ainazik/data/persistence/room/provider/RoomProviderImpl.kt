package kg.ainazik.data.persistence.room.provider

import android.content.Context
import androidx.room.Room
import kg.ainazik.data.persistence.room.ApplicationDatabase

class RoomProviderImpl(context: Context) : RoomProvider {

    private val applicationDatabase =
        Room.databaseBuilder(context, ApplicationDatabase::class.java, DATABASE_NAME).build()

    companion object {
       const val DATABASE_NAME = "news.application"
    }

    override fun provide(): ApplicationDatabase {
        return applicationDatabase
    }
}