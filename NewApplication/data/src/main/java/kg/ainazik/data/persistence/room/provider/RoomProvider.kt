package kg.ainazik.data.persistence.room.provider

import kg.ainazik.data.persistence.room.ApplicationDatabase

interface RoomProvider {
    fun provide(): ApplicationDatabase
}
