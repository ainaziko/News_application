package kg.ainazik.data.persistence.room.executor

import kg.ainazik.data.persistence.room.ApplicationDatabase
import kg.ainazik.domain.interactor.result.Result

interface RoomCoroutinesQueryExecutor {
    fun <T> executeQuery(performQuery: (ApplicationDatabase) -> T?): Result<T>
}