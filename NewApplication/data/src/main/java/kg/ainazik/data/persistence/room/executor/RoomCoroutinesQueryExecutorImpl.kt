package kg.ainazik.data.persistence.room.executor

import kg.ainazik.data.exeption.RoomObjectNotFound
import kg.ainazik.data.persistence.room.ApplicationDatabase
import kg.ainazik.domain.interactor.result.Result
import javax.inject.Inject

class RoomCoroutinesQueryExecutorImpl @Inject constructor(
    private val database: ApplicationDatabase
) : RoomCoroutinesQueryExecutor {

    override fun <T> executeQuery(performQuery: (ApplicationDatabase) -> T?): Result<T> {
        val result = performQuery(database)

        return if (result == null) {
            Result.Exception(RoomObjectNotFound())
        } else {
            Result.Success(result)
        }
    }
}